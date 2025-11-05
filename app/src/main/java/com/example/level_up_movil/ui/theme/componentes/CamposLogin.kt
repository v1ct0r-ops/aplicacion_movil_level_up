package com.example.level_up_movil.ui.theme.componentes



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.level_up_movil.ui.theme.*
import com.example.level_up_movil.ui.theme.PrimaryBlue
import com.example.level_up_movil.ui.theme.SurfaceDark2
import com.example.level_up_movil.ui.theme.TextPrimary

@Composable
fun CampoUsuario(
    valor: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onChange,
        label = { Text("Usuario") },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceDark2,
            unfocusedContainerColor = SurfaceDark2,
            focusedIndicatorColor = PrimaryBlue,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = PrimaryBlue,
            cursorColor = PrimaryBlue,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun CampoContrasena(
    valor: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onChange,
        label = { Text("Contraseña") },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SurfaceDark2,
            unfocusedContainerColor = SurfaceDark2,
            focusedIndicatorColor = PrimaryBlue,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
            focusedLabelColor = PrimaryBlue,
            cursorColor = PrimaryBlue,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary
        ),
        modifier = modifier.fillMaxWidth()
    )
}



