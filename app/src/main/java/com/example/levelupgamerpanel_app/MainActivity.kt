package com.example.levelupgamerpanel_app

// Importo las herramientas básicas de Android para crear la actividad principal
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

// Traigo los componentes de Material 3 para que se vea bonito
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

// Importo el navegador para poder ir entre pantallas
import androidx.navigation.compose.rememberNavController

// Traigo mi navegación personalizada y el tema de la app
import com.example.levelupgamerpanel_app.ui.navigation.AppNav
import com.example.levelupgamerpanel_app.ui.theme.LevelUpTheme

// Esta es la clase principal de mi app, como la puerta de entrada
class MainActivity : ComponentActivity() {
    
    // Creo un ViewModel que va a manejar todos los datos de la app
    private val vm: AppViewModel by viewModels()

    // Este método se ejecuta cuando la app se abre por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Aquí defino cómo se va a ver toda mi app
        setContent {
            // Aplico mi tema personalizado (colores, tipografías, etc.)
            LevelUpTheme {
                // Creo una superficie base con el color de fondo del tema
                Surface(color = MaterialTheme.colorScheme.background) {
                    
                    // Creo un controlador de navegación para moverme entre pantallas
                    val nav = rememberNavController()
                    
                    // Cargo mi sistema de navegación con todas las pantallas
                    AppNav(nav)
                }
            }
        }
    }
}
