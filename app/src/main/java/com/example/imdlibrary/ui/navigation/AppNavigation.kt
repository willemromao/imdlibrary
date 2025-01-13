package com.example.imdlibrary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdlibrary.ui.screens.*
import com.example.imdlibrary.viewmodel.BookViewModel

@Composable
fun AppNavigation(viewModel: BookViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "book_management") {
        composable("book_management") {
            BookManagementScreen(navController)
        }
        composable("book_create") {
            BookCreateScreen(viewModel, navController)
        }
        composable("book_list") {
            BookListScreen(viewModel, navController)
        }
        composable("book_detail/{isbn}") { backStackEntry ->
            val isbn = backStackEntry.arguments?.getString("isbn")
            isbn?.let {
                BookDetailScreen(viewModel, it, navController)
            }
        }
    }
}
