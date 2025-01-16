package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.imdlibrary.R
import com.example.imdlibrary.viewmodel.BookViewModel

@Composable
fun BookDetailScreen(viewModel: BookViewModel, isbn: String, navController: NavController) {
    val books by viewModel.books.collectAsState()
    val book = books.find { it.isbn == isbn }

    book?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = "Capa do livro",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                error = rememberAsyncImagePainter(R.drawable.placeholder_image), // Adicionado tratamento de erro
                placeholder = rememberAsyncImagePainter(R.drawable.placeholder_image)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(book.title, style = MaterialTheme.typography.titleLarge)
            Text("Autor: ${book.author}")
            Text("Editora: ${book.publisher}")
            Text("Ano: ${book.year}")
            Text("ISBN: ${book.isbn}")
            Text("Descrição: ${book.description}")
        }
    } ?: Text("Livro não encontrado.", style = MaterialTheme.typography.bodyLarge)
}