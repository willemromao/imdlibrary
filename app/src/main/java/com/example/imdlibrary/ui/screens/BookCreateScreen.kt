package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.imdlibrary.data.model.Book
import com.example.imdlibrary.viewmodel.BookViewModel
import kotlinx.coroutines.launch

@Composable
fun BookCreateScreen(viewModel: BookViewModel, navController: NavController) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var isbn by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Cadastrar Livro", style = MaterialTheme.typography.titleLarge)

        TextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
        TextField(value = author, onValueChange = { author = it }, label = { Text("Autor") })
        TextField(value = publisher, onValueChange = { publisher = it }, label = { Text("Editora") })
        TextField(value = year, onValueChange = { year = it }, label = { Text("Ano") })
        TextField(value = isbn, onValueChange = { isbn = it }, label = { Text("ISBN") })
        TextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("URL da capa") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Descrição") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.viewModelScope.launch {
                try {
                    val newBook = Book(
                        isbn = isbn,
                        title = title,
                        author = author,
                        publisher = publisher,
                        year = year.toIntOrNull() ?: 0,
                        description = description,
                        imageUrl = imageUrl
                    )
                    viewModel.addBook(newBook)
                    navController.popBackStack()
                } catch (e: Exception) {
                    println("Erro ao salvar livro: ${e.message}")
                }
            }
        }) {
            Text("Cadastrar")
        }
    }
}

