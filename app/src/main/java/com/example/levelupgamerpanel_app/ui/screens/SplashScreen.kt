package com.example.levelupgamerpanel_app.ui.screens

// Importo todas las herramientas de animación para hacer el splash súper llamativo
import androidx.compose.animation.*
import androidx.compose.animation.core.*

// Importo componentes visuales como imagen, texto, fondos, etc.
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

// Importo herramientas para manejar el estado y efectos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Importo navegación y recursos
import androidx.navigation.NavController
import com.example.levelupgamerpanel_app.R
import com.example.levelupgamerpanel_app.ui.navigation.Routes
import kotlinx.coroutines.delay

// Esta es mi pantalla de bienvenida súper genial que se ve al abrir la app
@Composable
fun SplashScreen(
    navController: NavController  // Necesito esto para navegar al login después
) {
    // Variable para controlar cuándo empiezan las animaciones
    var startAnimation by remember { mutableStateOf(false) }
    
    // Animación de transparencia: empieza invisible y se hace visible
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,  // De invisible (0) a visible (1)
        animationSpec = tween(1000),  // Tarda 1 segundo
        label = "alpha"
    )
    
    // Animación de escala: empieza chiquito y crece al tamaño normal
    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,  // De 30% a 100% del tamaño
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,  // Con efecto rebote
            stiffness = Spring.StiffnessLow                  // Suave y lento
        ),
        label = "scale"
    )
    
    // Animación de rotación: gira 360 grados
    val rotationAnim by animateFloatAsState(
        targetValue = if (startAnimation) 360f else 0f,  // Gira una vuelta completa
        animationSpec = tween(1200, easing = FastOutSlowInEasing),  // 1.2 segundos, rápido al inicio
        label = "rotation"
    )
    
    // Este efecto se ejecuta una sola vez cuando se carga la pantalla
    LaunchedEffect(Unit) {
        startAnimation = true          // Activo las animaciones
        delay(3000)                   // Espero 3 segundos para que el usuario vea el splash
        
        // Navego al login y elimino el splash del historial (para que no pueda volver)
        navController.navigate(Routes.Login) {
            popUpTo("splash") { inclusive = true }
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.background
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo animado
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo LevelUp",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scaleAnim)
                    .graphicsLayer { 
                        rotationZ = rotationAnim
                        alpha = alphaAnim
                    }
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Título animado
            AnimatedVisibility(
                visible = startAnimation,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                ) + fadeIn(
                    animationSpec = tween(1000, 500)
                )
            ) {
                Text(
                    text = "LevelUp",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    style = MaterialTheme.typography.displayMedium
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Subtítulo animado
            AnimatedVisibility(
                visible = startAnimation,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                ) + fadeIn(
                    animationSpec = tween(1000, 800)
                )
            ) {
                Text(
                    text = "Gaming Panel",
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        
        // Loading indicator en la parte inferior
        AnimatedVisibility(
            visible = startAnimation,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = fadeIn(
                animationSpec = tween(1000, 1500)
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 60.dp)
            ) {
                PulsingDots()
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Cargando...",
                    color = Color.White.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun PulsingDots() {
    val infiniteTransition = rememberInfiniteTransition(label = "dots")
    
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(3) { index ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(600),
                    repeatMode = RepeatMode.Reverse,
                    initialStartOffset = StartOffset(index * 200)
                ),
                label = "dot_$index"
            )
            
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .scale(scale)
                    .background(Color.White, androidx.compose.foundation.shape.CircleShape)
            )
        }
    }
}