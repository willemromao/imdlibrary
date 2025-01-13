package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BookManagementScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Gerenciamento de Livros", style = MaterialTheme.typography.titleLarge)

        Button(onClick = { navController.navigate("book_create") }) {
            Text("Cadastrar Livro")
        }
        Button(onClick = { navController.navigate("book_list") }) {
            Text("Listar Livros")
        }
    }
}
