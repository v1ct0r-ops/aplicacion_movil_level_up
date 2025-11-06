package com.example.levelupgamerpanel_app.ui.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

// Definir transiciones personalizadas para la navegación
@OptIn(ExperimentalAnimationApi::class)
object AppTransitions {
    
    // Transición de deslizamiento horizontal (pantallas principales)
    val slideHorizontal: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ContentTransform = {
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(300, easing = FastOutSlowInEasing)
        ) + fadeIn(
            animationSpec = tween(300)
        ) togetherWith slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(300, easing = FastOutSlowInEasing)
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }
    
    // Transición de escala (para diálogos y modales)
    val scaleIn: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ContentTransform = {
        scaleIn(
            initialScale = 0.8f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        ) + fadeIn(
            animationSpec = tween(300)
        ) togetherWith scaleOut(
            targetScale = 1.1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        ) + fadeOut(
            animationSpec = tween(300)
        )
    }
    
    // Transición de deslizamiento vertical (formularios)
    val slideVertical: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ContentTransform = {
        slideInVertically(
            initialOffsetY = { it },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy
            )
        ) + fadeIn() togetherWith slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy
            )
        ) + fadeOut()
    }
    
    // Transición de fade simple (para cambios sutiles)
    val fade: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ContentTransform = {
        fadeIn(
            animationSpec = tween(400, easing = LinearEasing)
        ) togetherWith fadeOut(
            animationSpec = tween(400, easing = LinearEasing)
        )
    }
    
    // Transición de slide desde abajo (para pantallas emergentes)
    val slideFromBottom: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ContentTransform = {
        slideInVertically(
            initialOffsetY = { it },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessMedium
            )
        ) + fadeIn() togetherWith slideOutVertically(
            targetOffsetY = { it },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy
            )
        ) + fadeOut()
    }
}

// Extensiones para facilitar el uso en NavHost
@Composable
fun getEnterTransition(route: String?): EnterTransition {
    return when (route) {
        "login", "registro" -> slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ) + fadeIn()
        
        "home" -> scaleIn(
            initialScale = 0.9f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ) + fadeIn()
        
        else -> slideInHorizontally(
            initialOffsetX = { it / 2 },
            animationSpec = tween(300, easing = FastOutSlowInEasing)
        ) + fadeIn(animationSpec = tween(300))
    }
}

@Composable
fun getExitTransition(route: String?): ExitTransition {
    return when (route) {
        "login", "registro" -> slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ) + fadeOut()
        
        "home" -> scaleOut(
            targetScale = 1.1f,
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ) + fadeOut()
        
        else -> slideOutHorizontally(
            targetOffsetX = { -it / 2 },
            animationSpec = tween(300, easing = FastOutSlowInEasing)
        ) + fadeOut(animationSpec = tween(300))
    }
}

@Composable
fun getPopEnterTransition(route: String?): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    ) + fadeIn()
}

@Composable
fun getPopExitTransition(route: String?): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    ) + fadeOut()
}