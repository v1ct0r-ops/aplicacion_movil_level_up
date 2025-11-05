package com.example.level_up_movil.ui.theme.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.level_up_movil.ui.pantallas.login.PantallaLogin
import com.example.level_up_movil.ui.theme.pantallas.login.dashboard.DashboardPantalla
import com.example.level_up_movil.ui.theme.pantallas.login.dashboard.registro.PantallaRegistro


@Composable
fun AppNavegacion(navController : NavHostController) {
            NavHost(navController = navController,
                    startDestination = "login"
            ) {
            composable("login"){ PantallaLogin(navController) }
            composable("dashboard"){ DashboardPantalla() }
                composable ("registro"){ PantallaRegistro(navController) }
        }
    }
