package com.example.levelupgamerpanel_app.ui.components

// Importo las animaciones para que los botones se vean súper fluidos
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*

// Importo herramientas para detectar cuando el usuario toca los botones
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState

// Traigo componentes de diseño y Material 3
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*

// Importo modificadores para cambiar el tamaño, escala, etc.
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Importo mi tema personalizado con colores y estilos
import com.example.levelupgamerpanel_app.ui.theme.*

// Este es mi botón principal, el más importante de la app (para acciones como Login, Guardar, etc.)
@Composable
fun BotonPrincipal(
    texto: String,                    // El texto que va a mostrar el botón
    onClick: () -> Unit,              // La función que se ejecuta cuando lo presionan
    modifier: Modifier = Modifier,    // Para personalizar tamaño, posición, etc.
    enabled: Boolean = true,          // Si está habilitado o deshabilitado
    isLoading: Boolean = false        // Si está cargando (muestra un spinner)
) {
    // Creo un detector para saber si el usuario está presionando el botón
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    // Animo el tamaño del botón: se hace un poquito más chico cuando lo presionan
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,  // Si está presionado, escala al 95%
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), // Efecto rebote suave
        label = "button_scale"
    )
    
    // Animo el color del botón: se ve más opaco cuando está deshabilitado
    val containerColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        label = "button_color"
    )

    // Creo el botón real con todas las animaciones
    Button(
        onClick = { if (!isLoading) onClick() },  // Solo ejecuto la acción si no está cargando
        modifier = modifier.scale(scale),         // Aplico la animación de escala
        enabled = enabled && !isLoading,         // Deshabilitado si está cargando
        interactionSource = interactionSource,   // Conecto el detector de presión
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,                        // Color animado
            contentColor = MaterialTheme.colorScheme.onPrimary     // Color del texto
        )
    ) {
        // Si está cargando, muestro un spinner. Si no, muestro el texto
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),    // Spinner pequeñito
                strokeWidth = 2.dp,                 // Línea delgada
                color = MaterialTheme.colorScheme.onPrimary  // Color que contraste con el fondo
            )
        } else {
            Text(texto)  // Muestro el texto normal
        }
    }
}



@Composable
fun BotonSecundario(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "button_scale"
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
        label = "border_color"
    )

    OutlinedButton(
        onClick = onClick,
        modifier = modifier.scale(scale),
        enabled = enabled,
        interactionSource = interactionSource,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = borderColor
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor)
    ) {
        Text(texto)
    }
}


@Composable
fun TercerBoton(
    texto: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "button_scale"
    )
    
    val elevation by animateDpAsState(
        targetValue = if (isPressed) 2.dp else 4.dp,
        label = "button_elevation"
    )
    
    val containerColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
        label = "button_color"
    )

    Button(
        onClick = { if (!isLoading) onClick() },
        enabled = enabled && !isLoading,
        modifier = modifier.scale(scale),
        interactionSource = interactionSource,
        shape = MaterialTheme.shapes.medium,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = elevation),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        } else {
            Text(
                text = texto,
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 16.sp)
            )
        }
    }
}
