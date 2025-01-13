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
        loadBooks()  // Carregar livros ao iniciar o ViewModel
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

    suspend fun deleteBook(book: Book) {
        repository.deleteBook(book)
        loadBooks()
    }
}


