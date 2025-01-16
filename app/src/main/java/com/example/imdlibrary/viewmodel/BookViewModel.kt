package com.example.imdlibrary.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdlibrary.data.model.Book
import com.example.imdlibrary.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookViewModel(private val repository: BookRepository) : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books = _books.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            _books.value = repository.getAllBooks()
        }
    }

    suspend fun addBook(book: Book) {
        repository.insertBook(book)
        loadBooks()
    }

    suspend fun deleteBookByIsbn(isbn: String) {
        val book = repository.getBookByIsbn(isbn)
        if (book != null) {
            repository.deleteBook(book)
            loadBooks()
        }
    }

    suspend fun updateBook(book: Book) {
        repository.updateBook(book)
        loadBooks()
    }
}



