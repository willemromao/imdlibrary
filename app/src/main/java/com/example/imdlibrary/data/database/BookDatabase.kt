package com.example.imdlibrary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdlibrary.data.model.Book

@Database(entities = [Book::class], version = 4, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
