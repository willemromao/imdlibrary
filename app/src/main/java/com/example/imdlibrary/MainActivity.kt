package com.example.imdlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.imdlibrary.data.database.BookDatabase
import com.example.imdlibrary.data.repository.BookRepository
import com.example.imdlibrary.data.repository.UserRepository
import com.example.imdlibrary.ui.navigation.AppNavigation
import com.example.imdlibrary.ui.theme.IMDLibraryTheme
import com.example.imdlibrary.viewmodel.BookViewModel
import com.example.imdlibrary.viewmodel.BookViewModelFactory
import com.example.imdlibrary.viewmodel.UserViewModel
import com.example.imdlibrary.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            BookDatabase::class.java, "book_database"
        ).fallbackToDestructiveMigration().build()

        // Inicialização dos repositórios
        val bookRepository = BookRepository(db.bookDao())
        val userRepository = UserRepository(db.userDao())

        // Inicialização dos ViewModels
        val bookViewModel = ViewModelProvider(
            this, BookViewModelFactory(bookRepository)
        )[BookViewModel::class.java]

        val userViewModel = ViewModelProvider(
            this, UserViewModelFactory(userRepository)
        )[UserViewModel::class.java]

        // Carregar os livros no início do app
        bookViewModel.loadBooks()

        setContent {
            IMDLibraryTheme {
                AppNavigation(
                    bookViewModel = bookViewModel,
                    userViewModel = userViewModel
                )
            }
        }

    }
}
