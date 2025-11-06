package com.example.levelupgamerpanel_app.ui.screens.login

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.levelupgamerpanel_app.R
import com.example.levelupgamerpanel_app.ui.components.AlertaErrorLogin
import com.example.levelupgamerpanel_app.ui.components.BotonPrincipal
import com.example.levelupgamerpanel_app.ui.components.BotonSecundario
import com.example.levelupgamerpanel_app.ui.components.CampoContrasena
import com.example.levelupgamerpanel_app.ui.components.CampoUsuario
import com.example.levelupgamerpanel_app.ui.components.SubtituloLogin
import com.example.levelupgamerpanel_app.ui.components.TituloLogin
import com.example.levelupgamerpanel_app.ui.navigation.Routes

@Composable
fun PantallaLogin(
    navController: NavController,
    vm: LoginViewModel = viewModel()
) {
    var visible by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        visible = true
    }
    
    val slideOffset by animateDpAsState(
        targetValue = if (visible) 0.dp else 300.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "slide_offset"
    )
    
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(800),
        label = "alpha"
    )

    Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 5.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(horizontal = 30.dp)
                    .offset(y = (-40).dp + slideOffset)
                    .graphicsLayer { 
                        this.alpha = alpha 
                    }
            ) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    // Logo con delay
                    var logoVisible by remember { mutableStateOf(false) }
                    
                    LaunchedEffect(visible) {
                        if (visible) {
                            delay(200)
                            logoVisible = true
                        }
                    }
                    
                    AnimatedVisibility(
                        visible = logoVisible,
                        enter = scaleIn(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy
                            )
                        ) + fadeIn(
                            animationSpec = tween(600)
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    TituloLogin(modifier = Modifier.align(Alignment.CenterHorizontally))
                    SubtituloLogin()

                    // Usa parámetros con nombre para que no importe el orden del composable
                    CampoUsuario(
                        valor = vm.usuario.value,
                        onChange = vm::onUsuarioChange,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    CampoContrasena(
                        valor = vm.contraseña.value,
                        onChange = vm::onContraseñaChange,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    BotonPrincipal(
                        texto = "Ingresar",
                        onClick = {
                            if (vm.validarLogin()) {
                                navController.navigate(Routes.Home)
                            }
                        },
                        isLoading = vm.isLoading.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                    )

                    if (vm.intentoLogin.value && !vm.error.value.isNullOrEmpty()) {
                        AlertaErrorLogin(
                            mensaje = vm.error.value ?: "",
                            onDismiss = { vm.error.value = null },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    BotonSecundario(
                        texto = "Registrarse",
                        onClick = { navController.navigate("registro") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                    )
                }
            }
        }
    }
}
