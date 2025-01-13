package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.imdlibrary.viewmodel.BookViewModel
import com.example.imdlibrary.R

@Composable
fun BookListScreen(viewModel: BookViewModel, navController: NavController) {
    val books by viewModel.books.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Lista de Livros", style = MaterialTheme.typography.titleLarge)

        if (books.isEmpty()) {
            Text("Nenhum livro encontrado.", modifier = Modifier.padding(8.dp))
        } else {
            LazyColumn {
                items(books.size) { index ->
                    val book = books[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { navController.navigate("book_detail/${book.isbn}") },
                        shape = MaterialTheme.shapes.medium,
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = book.imageUrl,
                                contentDescription = "Capa do livro",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(8.dp),
                                error = rememberAsyncImagePainter(R.drawable.placeholder_image), // Imagem de fallback em caso de erro
                                placeholder = rememberAsyncImagePainter(R.drawable.placeholder_image) // Imagem de carregamento
                            )
                            Column {
                                Text(text = book.title, style = MaterialTheme.typography.titleMedium)
                                Text("Editora: ${book.publisher}")
                                Text("Ano: ${book.year}")
                            }
                        }
                    }
                }
            }
        }
    }
}
