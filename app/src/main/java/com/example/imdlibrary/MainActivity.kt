package com.example.imdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.imdlibrary.data.database.BookDatabase
import com.example.imdlibrary.data.repository.BookRepository
import com.example.imdlibrary.ui.navigation.AppNavigation
import com.example.imdlibrary.viewmodel.BookViewModel
import com.example.imdlibrary.viewmodel.BookViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java, "book_database"
        ).fallbackToDestructiveMigration().build()

        val repository = BookRepository(db.bookDao())
        val viewModel = ViewModelProvider(this, BookViewModelFactory(repository))
            .get(BookViewModel::class.java)

        setContent {
            AppNavigation(viewModel)
        }
    }
}