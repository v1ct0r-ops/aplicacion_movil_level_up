package com.example.level_up_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.level_up_movil.ui.theme.AdminLevelUpTheme
import com.example.level_up_movil.ui.theme.navegacion.AppNavegacion

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdminLevelUpTheme {
                val navController = rememberNavController()
                AppNavegacion(navController)
                }
            }
        }
    }


