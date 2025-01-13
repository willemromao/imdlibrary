package com.example.imdlibrary.data.database

import androidx.room.*
import com.example.imdlibrary.data.model.Book

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT * FROM books WHERE isbn = :isbn")
    suspend fun getBookByIsbn(isbn: String): Book?

    @Delete
    suspend fun deleteBook(book: Book)
}
