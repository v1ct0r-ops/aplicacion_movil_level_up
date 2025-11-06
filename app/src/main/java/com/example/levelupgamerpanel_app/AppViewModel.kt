package com.example.levelupgamerpanel_app

// Importo herramientas de Android para crear ViewModels que sobreviven a rotaciones de pantalla
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

// Importo mis modelos de datos (las "estructuras" de mi información)
import com.example.levelupgamerpanel_app.data.models.*
import com.example.levelupgamerpanel_app.data.models.Producto
import com.example.levelupgamerpanel_app.data.models.Usuario

// Importo mi sistema de almacenamiento local
import com.example.levelupgamerpanel_app.data.store.AppStore

// Importo herramientas para trabajar con flujos de datos reactivos
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Este es el ViewModel principal de toda mi app, como el "cerebro central"
class AppViewModel(app: Application): AndroidViewModel(app){
    
    // Creo mi "base de datos" local
    private val store = AppStore(app)

    // Estos son mis "observadores" de datos que se actualizan automáticamente
    // Convierto los flows en StateFlows para que Compose los pueda observar fácilmente
    val usuarios   = store.usuariosFlow .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val productos  = store.productosFlow.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val pedidos    = store.pedidosFlow  .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
    val sesionCorreo = store.sesionFlow .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // Este es súper inteligente: combino la lista de usuarios con el correo de sesión
    // para obtener automáticamente el usuario que está logueado actualmente
    val usuarioActual: StateFlow<Usuario?> =
        combine(usuarios, sesionCorreo) { list, correo ->
            // Busco en la lista el usuario cuyo correo coincida con el de la sesión
            list.firstOrNull { it.correo.equals(correo, true) }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    // Función para hacer login (verificar credenciales)
    fun login(correo: String, pass: String, onError:(String)->Unit, onOk:()->Unit) {
        viewModelScope.launch {
            // Obtengo la lista actual de usuarios
            val list = store.usuariosFlow.first()
            
            // Busco un usuario que tenga ese correo y contraseña
            val u = list.firstOrNull { it.correo.equals(correo, true) && it.pass == pass }
            
            if (u == null) {
                onError("Credenciales inválidas")  // Si no lo encuentro, error
            } else { 
                store.saveSesion(u.correo)  // Si lo encuentro, guardo la sesión
                onOk()                      // Y aviso que todo salió bien
            }
        }
    }

    // Función para cerrar sesión (limpiar la sesión guardada)
    fun logout() { 
        viewModelScope.launch { 
            store.saveSesion(null)  // Borro la sesión actual
        } 
    }

    // Funciones para manejar productos
    fun addProducto(p: Producto){ 
        viewModelScope.launch { 
            // Agrego el nuevo producto a la lista existente
            store.saveProductos(productos.value + p) 
        } 
    }
    
    fun updateProducto(p: Producto){ 
        viewModelScope.launch { 
            // Reemplazo el producto con el mismo ID por la versión actualizada
            store.saveProductos(productos.value.map{ if (it.id==p.id) p else it }) 
        } 
    }
    
    fun removeProducto(id:String){ 
        viewModelScope.launch { 
            // Filtro la lista para quitar el producto con ese ID
            store.saveProductos(productos.value.filterNot{ it.id==id }) 
        } 
    }

    // Función para cambiar el estado de un pedido (pendiente, despachado, etc.)
    fun setEstadoPedido(id:String, estado:String){
        viewModelScope.launch { 
            // Busco el pedido y le cambio el estado
            store.savePedidos(pedidos.value.map{ if (it.id==id) it.copy(estado=estado) else it }) 
        }
    }

    // Funciones para manejar usuarios
    fun addUsuario(u: Usuario){ 
        viewModelScope.launch { 
            // Agrego el nuevo usuario a la lista
            store.saveUsuarios(usuarios.value + u) 
        } 
    }
    
    fun removeUsuario(correo:String){ 
        viewModelScope.launch { 
            // Elimino el usuario que tenga ese correo
            store.saveUsuarios(usuarios.value.filterNot{ it.correo.equals(correo,true) }) 
        } 
    }
}
