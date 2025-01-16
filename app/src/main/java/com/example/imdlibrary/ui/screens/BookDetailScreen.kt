package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.imdlibrary.R
import com.example.imdlibrary.viewmodel.BookViewModel

@Composable
fun StyledTextRow(label: String, value: String) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = value,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun BookDetailScreen(viewModel: BookViewModel, isbn: String, navController: NavController) {
    val books by viewModel.books.collectAsState()
    val book = books.find { it.isbn == isbn }

    book?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = "Capa do livro",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 24.dp),
                error = painterResource(id = R.drawable.placeholder_image), // Corrigido
                placeholder = painterResource(id = R.drawable.placeholder_image) // Corrigido
            )

            StyledTextRow(label = "Título", value = book.title)
            StyledTextRow(label = "Autor", value = book.author)
            StyledTextRow(label = "Editora", value = book.publisher)
            StyledTextRow(label = "Ano", value = book.year.toString())
            StyledTextRow(label = "ISBN", value = book.isbn)
            StyledTextRow(label = "Descrição", value = book.description)
        }
    } ?: Text(
        text = "Livro não encontrado.",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(16.dp)
    )
}
