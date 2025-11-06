package com.example.levelupgamerpanel_app.ui.screens.login

// Importo herramientas para manejar el estado de la pantalla
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// Importo corrutinas para hacer tareas en segundo plano
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Este ViewModel maneja toda la lógica del login (como el cerebro de la pantalla)
class LoginViewModel : ViewModel(){
    
    // Aquí guardo lo que el usuario escribe en el campo de usuario
    val usuario = mutableStateOf("")
    
    // Aquí guardo lo que escribe en el campo de contraseña
    val contraseña = mutableStateOf("")
    
    // Si hay algún error (como credenciales incorrectas), lo guardo aquí
    val error = mutableStateOf<String?>(null)
    
    // Para saber si estoy verificando las credenciales (para mostrar loading)
    val isLoading = mutableStateOf(false)
    
    // Para saber si el usuario ya intentó hacer login (para mostrar errores)
    val intentoLogin = mutableStateOf(false)



    // Esta función se ejecuta cada vez que el usuario escribe en el campo de usuario
    fun onUsuarioChange(nuevoUsuario: String) {
        usuario.value = nuevoUsuario    // Actualizo lo que escribió
        error.value = null              // Limpio cualquier error anterior para que no se vea confuso
    }

    // Esta función se ejecuta cada vez que escribe en el campo de contraseña
    fun onContraseñaChange(nuevaContraseña: String) {
        contraseña.value = nuevaContraseña  // Actualizo la contraseña
        error.value = null                  // También limpio errores anteriores
    }

    // Esta función verifica si las credenciales son correctas
    fun validarLogin(): Boolean {
        intentoLogin.value = true   // Marco que ya intentó hacer login
        isLoading.value = true      // Activo el indicador de carga
        
        // Uso una corrutina para simular que estoy consultando un servidor
        viewModelScope.launch {
            delay(1000) // Simulo que tarda 1 segundo en verificar (como si fuera una API)
            
            // Verifico las credenciales
            if (usuario.value == "admin" && contraseña.value == "1234"){
                isLoading.value = false  // Quito el loading porque ya terminé
            } else {
                // Si están mal, muestro error y quito el loading
                error.value = "Usuario o contraseña incorrectas reintentelo."
                isLoading.value = false
            }
        }

        // Por ahora retorno el resultado inmediatamente (en una app real sería asíncrono)
        return if (usuario.value == "admin" && contraseña.value == "1234"){
            true   // Login exitoso
        } else {
            false  // Login falló
        }
    }
}