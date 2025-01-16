package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.imdlibrary.data.model.Book
import com.example.imdlibrary.viewmodel.BookViewModel
import kotlinx.coroutines.launch

@Composable
fun BookEditScreen(viewModel: BookViewModel, navController: NavController) {
    var isbn by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Alterar Livro", style = MaterialTheme.typography.titleLarge)

        TextField(value = isbn, onValueChange = { isbn = it }, label = { Text("ISBN") })
        Button(onClick = {
            viewModel.viewModelScope.launch {
                val book = viewModel.books.value.find { it.isbn == isbn }
                if (book != null) {
                    title = book.title
                    author = book.author
                    publisher = book.publisher
                    year = book.year.toString()
                    description = book.description
                    imageUrl = book.imageUrl
                } else {
                    println("Livro não encontrado!")
                }
            }
        }) {
            Text("Buscar")
        }

        if (title.isNotEmpty()) {
            TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
            TextField(value = author, onValueChange = { author = it }, label = { Text("Autor") })
            TextField(value = publisher, onValueChange = { publisher = it }, label = { Text("Editora") })
            TextField(value = year, onValueChange = { year = it }, label = { Text("Ano") })
            TextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") })
            TextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("URL da Imagem") })

            Button(onClick = {
                viewModel.viewModelScope.launch {
                    val updatedBook = Book(
                        isbn = isbn,
                        title = title,
                        author = author,
                        publisher = publisher,
                        year = year.toInt(),
                        description = description,
                        imageUrl = imageUrl
                    )
                    viewModel.updateBook(updatedBook)
                    navController.popBackStack() // Voltar para a tela anterior
                }
            }) {
                Text("Alterar")
            }
        }
    }
}
