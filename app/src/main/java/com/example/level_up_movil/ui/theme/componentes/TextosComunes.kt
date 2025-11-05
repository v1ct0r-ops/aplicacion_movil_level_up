package com.example.level_up_movil.ui.theme.componentes



import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.level_up_movil.ui.theme.*

@Composable
fun TituloLogin(modifier: Modifier=Modifier) {
    Text(
        text = "Iniciar sesión",
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = modifier
    )
}

@Composable
fun SubtituloLogin() {
    Text(
        text = "Accedé a la gestión según tu rol.",
        style = MaterialTheme.typography.bodyMedium,
        color = TextSecondary
    )
}
