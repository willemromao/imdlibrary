package com.example.imdlibrary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imdlibrary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookManagementScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "IMD Library",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("book_create") },
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text("Cadastrar")
                    }
                    Button(
                        onClick = { navController.navigate("book_list") },
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text("Listar")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("book_edit") },
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text("Alterar")
                    }
                    Button(
                        onClick = { navController.navigate("book_delete") },
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text("Excluir")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Espaçamento adicional entre os botões e a imagem

            Image(
                painter = painterResource(id = R.drawable.management),
                contentDescription = "Imagem decorativa",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp) // Aumenta a altura da imagem
            )
        }
    }
}
