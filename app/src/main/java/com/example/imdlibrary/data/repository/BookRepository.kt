package com.example.imdlibrary.data.repository

import com.example.imdlibrary.data.database.BookDao
import com.example.imdlibrary.data.model.Book

class BookRepository(private val bookDao: BookDao) {
    suspend fun insertBook(book: Book) = bookDao.insertBook(book)
    suspend fun getAllBooks() = bookDao.getAllBooks()
    suspend fun getBookByIsbn(isbn: String) = bookDao.getBookByIsbn(isbn)
    suspend fun deleteBook(book: Book) = bookDao.deleteBook(book)
}
