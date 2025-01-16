package com.example.imdlibrary.ui.navigation

import BookDeleteScreen
import ForgotPasswordScreen
import RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.imdlibrary.ui.screens.*
import com.example.imdlibrary.viewmodel.BookViewModel
import com.example.imdlibrary.viewmodel.UserViewModel

@Composable
fun AppNavigation(bookViewModel: BookViewModel, userViewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(userViewModel, navController)
        }
        composable("register") {
            RegisterScreen(userViewModel, navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(userViewModel, navController)
        }
        composable("book_management") {
            BookManagementScreen(navController)
        }
        composable("book_create") {
            BookCreateScreen(bookViewModel, navController)
        }
        composable("book_list") {
            BookListScreen(bookViewModel, navController)
        }
        composable("book_edit") {
            BookEditScreen(bookViewModel, navController)
        }
        composable("book_detail/{isbn}") { backStackEntry ->
            val isbn = backStackEntry.arguments?.getString("isbn")
            isbn?.let {
                BookDetailScreen(bookViewModel, it, navController)
            }
        }
        composable("book_delete") {
            BookDeleteScreen(bookViewModel, navController)
        }
    }
}

