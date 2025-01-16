package com.example.imdlibrary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdlibrary.data.model.Book
import com.example.imdlibrary.data.model.User

@Database(entities = [Book::class, User::class], version = 5, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun userDao(): UserDao
}
