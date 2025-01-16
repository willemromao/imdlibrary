import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.imdlibrary.viewmodel.UserViewModel

@Composable
fun ForgotPasswordScreen(viewModel: UserViewModel, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Redefinir Senha", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nome de usuário") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nova senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.resetPassword(username, newPassword) { success ->
                if (success) {
                    successMessage = "Senha redefinida com sucesso!"
                    errorMessage = null
                    navController.navigate("login") // Navega para a tela de login
                } else {
                    errorMessage = "Usuário não encontrado!"
                    successMessage = null
                }
            }
        }) {
            Text("Redefinir senha")
        }

        successMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.primary)
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}
