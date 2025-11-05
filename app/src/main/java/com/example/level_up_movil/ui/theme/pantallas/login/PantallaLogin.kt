package com.example.level_up_movil.ui.pantallas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.level_up_movil.ui.theme.componentes.*
import com.example.level_up_movil.ui.theme.*
import com.example.level_up_movil.ui.theme.pantallas.login.LoginViewModel
import com.example.level_up_movil.R


@Composable
fun PantallaLogin(navController: NavController, vm: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 5.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth(0.95f)         // ocupa solo el 90% del ancho total
                    .padding(horizontal = 30.dp) // deja aire a los costados
                    .offset( y = (-40).dp)
            ) {
                Column(
                    modifier = Modifier.padding(40.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally))

                    TituloLogin( modifier = Modifier.align(Alignment.CenterHorizontally))
                    SubtituloLogin()
                    CampoUsuario(vm.usuario.value,
                                onChange = { vm.usuario.value = (it) })

                    Spacer(modifier = Modifier.height(8.dp))

                    CampoContrasena(vm.contraseña.value,
                                    onChange = { vm.contraseña.value = (it) })

                    Spacer(modifier = Modifier.height(18.dp))

                    BotonPrincipal("Ingresar",
                                    onClick = {
                                        if (vm.validarLogin()) navController.navigate("dashboard")
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(45.dp)) // puedo centrar el boton de manera coherente

                    if (vm.intentoLogin.value && !vm.error.value.isNullOrEmpty()) {
                        AlertaErrorLogin(
                            mensaje = vm.error.value ?: "",
                            onDismiss = { vm.error.value = null }, // Limpia el error al presionar OK
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
                            .height(45.dp))
                }

            }
        }
    }

}
