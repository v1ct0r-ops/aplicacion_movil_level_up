package com.example.level_up_movil.ui.theme.pantallas.login.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardPantalla(){
    Surface(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ){
        Column{
            Text(text = "Bienvenido al panel principal", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))
            Text("Aqui podras acceder a tus modulos y reportes.")
        }
    }
}