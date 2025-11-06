package com.example.levelupgamerpanel_app.ui.screens.registro



import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.compareTo
import kotlin.run

data class RegistroUiState(
    val run: String = "",
    val nombres: String = "",
    val apellidos: String = "",
    val correo: String = "",
    val fechaNacimiento: String = "",
    val tipoUsuario: String = "Administrador",
    val region: String = "",
    val comuna: String = "",
    val direccion: String = "",
    val password: String = "",
    val confirmar: String = "",
    val referido: String = "",
    val error: String? = null,
    val isCreating: Boolean = false,
    val runError: String? = null,
    val nombresError: String? = null,
    val apellidosError: String? = null,
    val correoError: String? = null,
    val fechaError: String? = null,
    val regionError: String? = null,
    val comunaError: String? = null,
    val direccionError: String? = null,
    val passwordError: String? = null,
    val confirmarError: String? = null,

    )

class RegistroViewModel : ViewModel() {
    val ui = mutableStateOf(RegistroUiState())
    val isCreating = mutableStateOf(false)

    private fun actualizar(transform: (RegistroUiState) -> RegistroUiState) {
        ui.value = transform(ui.value).copy(error = null)  // limpia error al editar
    }

    fun onRun(v: String)          = actualizar { it.copy(run = v.uppercase()) }
    fun onNombres(v: String)      = actualizar { it.copy(nombres = v) }
    fun onApellidos(v: String)    = actualizar { it.copy(apellidos = v) }
    fun onCorreo(v: String)       = actualizar { it.copy(correo = v.lowercase()) }
    fun onFecha(v: String)        = actualizar { it.copy(fechaNacimiento = v) }
    fun onTipo(v: String)         = actualizar { it.copy(tipoUsuario = v) }
    fun onRegion(v: String)       = actualizar { it.copy(region = v) }
    fun onComuna(v: String)       = actualizar { it.copy(comuna = v) }
    fun onDireccion(v: String)    = actualizar { it.copy(direccion = v) }
    fun onPassword(v: String)     = actualizar { it.copy(password = v) }
    fun onConfirmar(v: String)    = actualizar { it.copy(confirmar = v) }
    fun onReferido(v: String)     = actualizar { it.copy(referido = v) }

    // Estas son mis validaciones súper estrictas para asegurarme que los datos están bien
    
    // Verifico que el correo tenga formato válido y sea de dominios permitidos
    private fun correoValido(c: String): Boolean {
        if (c.isBlank()) return false  // Si está vacío, obviamente no sirve
        
        // Uso una expresión regular (regex) para verificar que tenga formato de email
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        
        // Debe tener formato correcto Y ser de dominios que acepto
        return regex.matches(c) && (c.endsWith("@duoc.cl") || c.endsWith("@profesor.duoc.cl") || c.endsWith("@gmail.com"))
    }

    // Verifico que el RUN chileno sea válido (con su dígito verificador correcto)
    private fun runValido(r: String): Boolean {
        if (r.isBlank()) return false  // Si está vacío, no sirve
        
        // Verifico que tenga el formato correcto (7-8 números + un dígito/K) Y que el dígito verificador esté bien
        return r.matches(Regex("""\d{7,8}[0-9K]""", RegexOption.IGNORE_CASE)) && validarDigitoVerificadorRun(r)
    }

    // Esta función verifica que el dígito verificador del RUN esté correcto (algoritmo oficial chileno)
    private fun validarDigitoVerificadorRun(run: String): Boolean {
        try {
            // Separo el número del dígito verificador
            val numero = run.dropLast(1)        // Todo menos el último carácter
            val dv = run.last().toString().uppercase()  // Solo el último carácter
            
            // Aplico el algoritmo oficial chileno para calcular el dígito verificador
            var suma = 0
            var multiplicador = 2  // Empiezo multiplicando por 2
            
            // Recorro los números de derecha a izquierda
            for (i in numero.length - 1 downTo 0) {
                suma += numero[i].digitToInt() * multiplicador
                // El multiplicador va de 2 a 7, luego vuelve a 2
                multiplicador = if (multiplicador == 7) 2 else multiplicador + 1
            }
            
            // Calculo el dígito verificador según el algoritmo
            val resto = suma % 11
            val dvCalculado = when (resto) {
                0 -> "0"                    // Si resto es 0, el dígito es 0
                1 -> "K"                    // Si resto es 1, el dígito es K
                else -> (11 - resto).toString()  // En otros casos, es 11 menos el resto
            }
            
            // Comparo el dígito calculado con el que me dieron
            return dv == dvCalculado
        } catch (e: Exception) {
            // Si algo sale mal (caracteres raros, etc.), considero que no es válido
            return false
        }
    }

    private fun fechaValida(fecha: String): Boolean {
        if (fecha.isBlank()) return false
        val regex = Regex("""^\d{2}-\d{2}-\d{4}$""")
        if (!regex.matches(fecha)) return false
        
        try {
            val partes = fecha.split("-")
            val dia = partes[0].toInt()
            val mes = partes[1].toInt()
            val año = partes[2].toInt()
            
            if (año < 1900 || año > 2010) return false
            if (mes < 1 || mes > 12) return false
            if (dia < 1 || dia > 31) return false
            
            // Validar días por mes
            val diasPorMes = when (mes) {
                2 -> if (esBisiesto(año)) 29 else 28
                4, 6, 9, 11 -> 30
                else -> 31
            }
            
            return dia <= diasPorMes
        } catch (e: Exception) {
            return false
        }
    }

    private fun esBisiesto(año: Int): Boolean {
        return (año % 4 == 0 && año % 100 != 0) || (año % 400 == 0)
    }

    private fun passwordSegura(password: String): Boolean {
        if (password.length < 6) return false
        val tieneNumero = password.any { it.isDigit() }
        val tieneLetra = password.any { it.isLetter() }
        return tieneNumero && tieneLetra
    }

    private fun nombreValido(nombre: String): Boolean {
        if (nombre.isBlank()) return false
        return nombre.length >= 2 && nombre.all { it.isLetter() || it.isWhitespace() }
    }

    fun puedeCrear(): Boolean = with(ui.value) {
        runValido(run) &&
                nombreValido(nombres) &&
                nombreValido(apellidos) &&
                correoValido(correo) &&
                fechaValida(fechaNacimiento) &&
                region.isNotBlank() &&
                comuna.isNotBlank() &&
                direccion.isNotBlank() &&
                passwordSegura(password) &&
                password == confirmar
    }

    fun validarCampos(): RegistroUiState {
        val s = ui.value
        return s.copy(
            runError = when {
                s.run.isBlank() -> "El RUN es obligatorio"
                !runValido(s.run) -> "RUN inválido (formato: 12345678K)"
                else -> null
            },
            nombresError = when {
                s.nombres.isBlank() -> "Los nombres son obligatorios"
                !nombreValido(s.nombres) -> "Solo letras, mínimo 2 caracteres"
                else -> null
            },
            apellidosError = when {
                s.apellidos.isBlank() -> "Los apellidos son obligatorios"
                !nombreValido(s.apellidos) -> "Solo letras, mínimo 2 caracteres"
                else -> null
            },
            correoError = when {
                s.correo.isBlank() -> "El correo es obligatorio"
                !correoValido(s.correo) -> "Correo inválido o dominio no permitido"
                else -> null
            },
            fechaError = when {
                s.fechaNacimiento.isBlank() -> "La fecha es obligatoria"
                !fechaValida(s.fechaNacimiento) -> "Formato inválido (dd-mm-aaaa) o fecha incorrecta"
                else -> null
            },
            regionError = if (s.region.isBlank()) "La región es obligatoria" else null,
            comunaError = if (s.comuna.isBlank()) "La comuna es obligatoria" else null,
            direccionError = if (s.direccion.isBlank()) "La dirección es obligatoria" else null,
            passwordError = when {
                s.password.isBlank() -> "La contraseña es obligatoria"
                !passwordSegura(s.password) -> "Mínimo 6 caracteres, debe incluir letras y números"
                else -> null
            },
            confirmarError = when {
                s.confirmar.isBlank() -> "Confirma tu contraseña"
                s.password != s.confirmar -> "Las contraseñas no coinciden"
                else -> null
            }
        )
    }

    fun crearCuenta(
        onOk: () -> Unit,
        onError: (String) -> Unit
    ) {
        val errores = validarCampos() //  llamamos a las validaciones campo a campo
        ui.value = errores

        // Si hay algún campo con error, mostramos mensaje general
        if (
            errores.runError != null ||
            errores.nombresError != null ||
            errores.apellidosError != null ||
            errores.correoError != null ||
            errores.fechaError != null ||
            errores.regionError != null ||
            errores.comunaError != null ||
            errores.direccionError != null ||
            errores.passwordError != null ||
            errores.confirmarError != null
        ) {
            onError("Por favor corrige los errores en el formulario")
            return
        }

        // Validación final
        if (!puedeCrear()) {
            onError("Completa todos los campos correctamente")
            return
        }

        // Simular creación de cuenta con loading
        isCreating.value = true
        viewModelScope.launch {
            delay(2000) // Simular proceso de registro
            isCreating.value = false
            onOk()
        }
    }




}




