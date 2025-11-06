package com.example.levelupgamerpanel_app.ui.navigation

// Importo las animaciones para que las transiciones se vean súper suaves
import androidx.compose.animation.*
import androidx.compose.runtime.Composable

// Traigo las herramientas de navegación de Jetpack Compose
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// Importo todas mis pantallas para poder navegar entre ellas
import com.example.levelupgamerpanel_app.ui.screens.HomeScreen
import com.example.levelupgamerpanel_app.ui.screens.LoginScreen
import com.example.levelupgamerpanel_app.ui.screens.pedidos.DetallePedidoScreen
import com.example.levelupgamerpanel_app.ui.screens.pedidos.PedidosScreen
import com.example.levelupgamerpanel_app.ui.screens.productos.EditarProductoScreen
import com.example.levelupgamerpanel_app.ui.screens.productos.NuevoProductoScreen
import com.example.levelupgamerpanel_app.ui.screens.productos.ProductosScreen
import com.example.levelupgamerpanel_app.ui.screens.usuarios.NuevoUsuarioScreen
import com.example.levelupgamerpanel_app.ui.screens.usuarios.UsuariosScreen

// Aquí defino todas las rutas (direcciones) de mi app, como si fueran URLs
object Routes {
    const val Splash = "splash"                        // Pantalla de bienvenida con logo
    const val Login = "login"                          // Pantalla para iniciar sesión
    const val Registro = "registro"                    // Pantalla para crear cuenta nueva
    const val Home = "home"                           // Pantalla principal después del login
    const val Productos = "productos"                  // Lista de todos los productos
    const val NuevoProducto = "nuevo_producto"         // Formulario para agregar producto
    const val EditarProducto = "editar_producto/{id}"  // Formulario para editar (con ID del producto)
    const val Pedidos = "pedidos"                     // Lista de pedidos
    const val DetallePedido = "detalle_pedido/{id}"   // Ver detalles de un pedido específico
    const val Usuarios = "usuarios"                   // Lista de usuarios registrados
    const val NuevoUsuario = "nuevo_usuario"          // Formulario para crear usuario
}

// Esta función arma todo el sistema de navegación de mi app
@Composable
fun AppNav(nav: NavHostController){
    // Creo el contenedor principal donde van a vivir todas mis pantallas
    NavHost(
        navController = nav,              // Le paso el controlador para manejar la navegación
        startDestination = Routes.Splash  // Defino que la primera pantalla sea el splash
    ){
        // Aquí registro cada pantalla con su ruta correspondiente
        
        // Pantalla de bienvenida con animaciones bonitas
        composable(Routes.Splash){
            com.example.levelupgamerpanel_app.ui.screens.SplashScreen(navController = nav)
        }
        
        // Pantalla donde el usuario ingresa sus credenciales
        composable(Routes.Login){
            com.example.levelupgamerpanel_app.ui.screens.login.PantallaLogin(navController = nav)
        }
        
        // Pantalla para que nuevos usuarios se registren
        composable(Routes.Registro){
            com.example.levelupgamerpanel_app.ui.screens.registro.PantallaRegistro(navController = nav)
        }
        
        // Pantalla principal después de hacer login exitoso
        composable(Routes.Home){ HomeScreen(nav) }
        
        // Pantalla que muestra todos los productos disponibles
        composable(Routes.Productos){ ProductosScreen(nav) }
        
        // Formulario para que el admin pueda agregar productos nuevos
        composable(Routes.NuevoProducto){ NuevoProductoScreen(nav) }
        
        // Formulario para editar un producto existente (recibe el ID por parámetro)
        composable(Routes.EditarProducto){ EditarProductoScreen(nav) }
        
        // Lista de todos los pedidos realizados
        composable(Routes.Pedidos){ PedidosScreen(nav) }
        
        // Pantalla detallada de un pedido específico
        composable(Routes.DetallePedido){ DetallePedidoScreen(nav) }
        
        // Lista de usuarios registrados en el sistema
        composable(Routes.Usuarios){ UsuariosScreen(nav) }
        
        // Formulario para que el admin cree nuevos usuarios
        composable(Routes.NuevoUsuario){ NuevoUsuarioScreen(nav) }
    }
}