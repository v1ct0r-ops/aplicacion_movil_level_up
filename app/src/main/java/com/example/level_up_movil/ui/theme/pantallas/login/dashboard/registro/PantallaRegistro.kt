package com.example.level_up_movil.ui.theme.pantallas.login.dashboard.registro

import android.R.attr.enabled
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.level_up_movil.ui.theme.componentes.*
import com.example.level_up_movil.ui.theme.componentes.AlertaErrorLogin

@Composable
fun PantallaRegistro(
    navController: NavController,
    vm: RegistroViewModel = viewModel()
) {
    val ui = vm.ui.value
    val mostrarDialogoExito = rememberSaveable { mutableStateOf(false) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 5.dp,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .heightIn(max = 550.dp)
                    .padding(horizontal = 11.dp)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 13.dp, vertical = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 🔹 TÍTULO
                    Text(
                        "Crear cuenta",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    // 🔹 RUN con error visual
                    CampoTexto(
                        label = "RUN (sin puntos ni guión)",
                        valor = ui.run,
                        onChange = vm::onRun,
                        supportingText = "Ej: 19011022K",
                        isError = ui.runError != null,   //  muestra borde rojo si hay error
                        errorText = ui.runError,         //  mensaje debajo
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 NOMBRES
                    CampoTexto(
                        label = "Nombres",
                        valor = ui.nombres,
                        onChange = vm::onNombres,
                        isError = ui.nombresError != null,
                        errorText = ui.nombresError,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 APELLIDOS
                    CampoTexto(
                        label = "Apellidos",
                        valor = ui.apellidos,
                        onChange = vm::onApellidos,
                        isError = ui.apellidosError != null,
                        errorText = ui.apellidosError,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 CORREO
                    CampoTexto(
                        label = "Correo",
                        valor = ui.correo,
                        onChange = vm::onCorreo,
                        supportingText = "Permitidos: @duoc.cl, @profesor.duoc.cl, @gmail.com",
                        isError = ui.correoError != null,
                        errorText = ui.correoError,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 FECHA
                    CampoTexto(
                        label = "Fecha de Nacimiento (dd-mm-aaaa)",
                        valor = ui.fechaNacimiento,
                        onChange = vm::onFecha,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 TIPO DE USUARIO
                    SelectorSimple(
                        label = "Tipo de Usuario",
                        opciones = listOf("Administrador", "Cliente"),
                        seleccionado = ui.tipoUsuario,
                        onSelect = vm::onTipo,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 REGIÓN / COMUNA
                    CampoTexto(
                        label = "Región",
                        valor = ui.region,
                        onChange = vm::onRegion,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )
                    CampoTexto(
                        label = "Comuna",
                        valor = ui.comuna,
                        onChange = vm::onComuna,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 DIRECCIÓN
                    CampoTexto(
                        label = "Dirección",
                        valor = ui.direccion,
                        onChange = vm::onDireccion,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 PASSWORD
                    CampoPassword(
                        label = "Contraseña",
                        valor = ui.password,
                        onChange = vm::onPassword,
                        isError = ui.passwordError != null,
                        errorText = ui.passwordError,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )

                    // 🔹 CONFIRMAR PASSWORD
                    CampoPassword(
                        label = "Confirmar",
                        valor = ui.confirmar,
                        onChange = vm::onConfirmar,
                        isError = ui.confirmarError != null,
                        errorText = ui.confirmarError,
                        modifier = Modifier.fillMaxWidth(0.95f)
                    )



                    // 🔹 MENSAJE GENERAL DE ERROR
                    ui.error?.let {
                        AlertaErrorLogin(
                            mensaje = it,
                            onDismiss = { vm.ui.value = ui.copy(error = null) },
                            modifier = Modifier.fillMaxWidth(0.9f)
                        )
                    }

                    //  BOTÓN CREAR CUENTA
                    TercerBoton(
                        texto = "Crear cuenta",
                        onClick = {
                            vm.crearCuenta(
                                onOk = {
                                    //  Mostrar el diálogo de éxito
                                    mostrarDialogoExito.value = true
                                },
                                onError = { msg -> vm.ui.value = ui.copy(error = msg) }
                            )
                        },
                        enabled = true, //  siempre habilitado
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    if (mostrarDialogoExito.value) {
                        DialogExitoRegistro(
                            onOk = {
                                mostrarDialogoExito.value = false
                                navController.navigate("login")
                            }
                        )
                    }

                    //  TEXTO DE REDIRECCIÓN
                    Text(
                        "¿Ya tienes cuenta?",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    TextButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Ingresar")
                    }
                }
            }
        }
    }
}
