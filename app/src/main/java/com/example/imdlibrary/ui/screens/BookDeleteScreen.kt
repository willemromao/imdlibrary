package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.imdlibrary.viewmodel.BookViewModel
import kotlinx.coroutines.launch

@Composable
fun BookDeleteScreen(viewModel: BookViewModel, navController: NavController) {
    var isbn by remember { mutableStateOf("") }
    var deletionMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Excluir Livro", style = MaterialTheme.typography.titleLarge)

        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN do Livro") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                viewModel.viewModelScope.launch {
                    try {
                        viewModel.deleteBookByIsbn(isbn)
                        deletionMessage = "Livro com ISBN $isbn excluído com sucesso."
                    } catch (e: Exception) {
                        deletionMessage = "Erro ao excluir livro: ${e.message}"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Excluir")
        }

        deletionMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}
