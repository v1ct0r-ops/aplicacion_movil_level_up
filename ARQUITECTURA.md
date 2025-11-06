# ARQUITECTURA DEL PROYECTO - LevelUpGamerPanelApp

## Patrón Arquitectónico: MVVM + Clean Architecture

### Diagrama de Capas

```
┌─────────────────────────────────────────────────────────────┐
│                    UI Layer (Presentation)                  │
│  ┌─────────────────┐ ┌─────────────────┐ ┌───────────────┐  │
│  │    Screens      │ │   Components    │ │     Theme     │  │
│  │  - LoginScreen  │ │  - Botones.kt   │ │  - Color.kt   │  │
│  │  - HomeScreen   │ │  - Campos.kt    │ │  - Theme.kt   │  │
│  │  - RegistroS.   │ │  - Loading.kt   │ │  - Typography │  │
│  └─────────────────┘ └─────────────────┘ └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                 ViewModel Layer (Business Logic)            │
│  ┌─────────────────┐ ┌─────────────────┐ ┌───────────────┐  │
│  │  AppViewModel   │ │  LoginViewModel │ │RegistroVM     │  │
│  │  - Estado Global│ │  - Autenticación│ │ - Validaciones│  │
│  │  - CRUD Datos   │ │  - Validaciones │ │ - GPS Service │  │
│  └─────────────────┘ └─────────────────┘ └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                    Data Layer (Repository)                  │
│  ┌─────────────────┐ ┌─────────────────┐ ┌───────────────┐  │
│  │   AppStore.kt   │ │   Models.kt     │ │   DataStore   │  │
│  │  - CRUD Ops     │ │  - Usuario      │ │  - Persistencia│ │
│  │  - StateFlow    │ │  - Producto     │ │  - JSON Serial│  │
│  │  - Sesiones     │ │  - Pedido       │ │  - Preferences│  │
│  └─────────────────┘ └─────────────────┘ └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                Platform Layer (Android Services)            │
│  ┌─────────────────┐ ┌─────────────────┐ ┌───────────────┐  │
│  │ LocationManager │ │ Vibrator Service│ │ Permissions   │  │
│  │  - GPS coords   │ │  - Haptic Feed  │ │ - Runtime     │  │
│  │  - Real-time    │ │  - UX Enhancement│ │ - Security    │  │
│  └─────────────────┘ └─────────────────┘ └───────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Flujo de Datos y Estados

### Secuencia de Interacción Usuario

```
Usuario → UI (Compose) → ViewModel → Repository → DataStore
  ↑                                      ↓
  └─────── UI Actualizada ←─── StateFlow Emite Estado
```

### Gestión de Estados Reactivos

**StateFlow Pattern Implementado:**
- `AppViewModel` mantiene estado global de la aplicación
- `LoginViewModel` maneja estado de autenticación
- `RegistroViewModel` controla validaciones y GPS
- Todos emiten estados que se observan reactivamente en UI

## Estructura del Proyecto

```
app/src/main/java/com/example/levelupgamerpanel_app/
│
├── MainActivity.kt                 # Punto de entrada principal
├── AppViewModel.kt                 # Estado global de la aplicación
│
├── data/
│   ├── models/
│   │   └── Models.kt              # Usuario, Producto, Pedido
│   └── store/
│       └── AppStore.kt            # Almacenamiento local DataStore
│
└── ui/
    ├── components/                # Componentes reutilizables
    │   ├── Botones.kt
    │   ├── CamposLogin.kt
    │   ├── CamposRegistro.kt
    │   ├── AnimatedComponents.kt
    │   └── LoadingComponents.kt
    │
    ├── navigation/
    │   ├── AppNav.kt              # Sistema navegación principal
    │   └── AppTransitions.kt      # Transiciones animadas
    │
    ├── screens/                   # Pantallas de la aplicación
    │   ├── SplashScreen.kt
    │   ├── HomeScreen.kt
    │   ├── login/
    │   │   ├── LoginViewModel.kt
    │   │   └── PantallaLogin.kt
    │   ├── registro/
    │   │   ├── RegistroViewModel.kt
    │   │   └── PantallaRegistro.kt
    │   ├── usuarios/
    │   │   └── UsuariosScreens.kt
    │   ├── productos/
    │   │   └── ProductosScreens.kt
    │   └── pedidos/
    │       └── PedidosScreens.kt
    │
    └── theme/                     # Configuración de temas
        ├── Color.kt
        ├── Theme.kt
        └── Typography.kt
```

## Patrones de Diseño Implementados

### 1. MVVM (Model-View-ViewModel)
**Separación de responsabilidades:**
- **View (Compose)**: Renderizado de UI y eventos usuario
- **ViewModel**: Lógica de negocio y manejo de estados
- **Model**: Representación de datos y persistencia

### 2. Repository Pattern
**Abstracción de acceso a datos:**
- `AppStore` actúa como repository único
- Abstrae la complejidad de DataStore
- Proporciona API limpia para ViewModels

### 3. Observer Pattern
**Estados reactivos:**
- StateFlow para emisión de estados
- Compose observa automáticamente cambios
- Recomposición eficiente de UI

### 4. Singleton Pattern
**Gestión de recursos compartidos:**
- AppStore como instancia única
- Compartido entre todos los ViewModels
- Consistencia de datos garantizada

## Tecnologías y Dependencias

### Core Android
```kotlin
// Kotlin y Android base
implementation("androidx.core:core-ktx:1.13.1")
implementation("androidx.activity:activity-compose:1.9.3")

// Jetpack Compose
implementation(platform("androidx.compose:compose-bom:2024.11.00"))
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
```

### Arquitectura y Estado
```kotlin
// ViewModel y Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")

// Navegación
implementation("androidx.navigation:navigation-compose:2.8.3")
```

### Almacenamiento y Datos
```kotlin
// DataStore para persistencia
implementation("androidx.datastore:datastore-preferences:1.1.1")

// Serialización JSON
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
```

### Servicios Nativos
```kotlin
// Servicios de ubicación GPS
implementation("com.google.android.gms:play-services-location:21.3.0")
```

## Validaciones y Lógica de Negocio

### Algoritmo de Validación RUN Chileno
```kotlin
fun validarRun(run: String): Boolean {
    val runLimpio = run.replace(".", "").replace("-", "")
    if (runLimpio.length != 9) return false
    
    val multiplicadores = intArrayOf(2,3,4,5,6,7,2,3,4)
    val digitos = runLimpio.substring(0,8).map { it.digitToInt() }
    
    val suma = digitos.mapIndexed { index, digito -> 
        digito * multiplicadores[index] 
    }.sum()
    
    val modulo = suma % 11
    val digitoVerificador = when (11 - modulo) {
        10 -> 'K'
        11 -> '0'
        else -> (11 - modulo).toString().first()
    }
    
    return digitoVerificador == runLimpio.last()
}
```

### Integración GPS
```kotlin
// Solicitud de ubicación con LocationManager
private fun obtenerUbicacion() {
    if (hayPermisosUbicacion()) {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            5000L, // 5 segundos
            10f,   // 10 metros
            locationListener
        )
    }
}
```

## Performance y Optimización

### Optimizaciones Implementadas

**1. Compose Recomposition**
- `remember` para cálculos costosos
- `LaunchedEffect` para efectos laterales controlados
- `derivedStateOf` para estados computados

**2. Memory Management**
- ViewModels sobreviven configuration changes
- Coroutines con scopes apropiados
- StateFlow con manejo automático de recursos

**3. UI Performance**
- Animaciones con spring physics naturales
- Loading states para feedback inmediato
- Debounce en validaciones tiempo real

**4. Data Efficiency**
- DataStore para I/O asíncrono optimizado
- Serialización JSON eficiente
- Caching inteligente de estados

## Seguridad y Permisos

### Manejo de Permisos Android
```xml
<!-- Permisos requeridos -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.VIBRATE" />
<uses-permission android:name="android.permission.INTERNET" />
```

### Validaciones de Seguridad
- Validación cliente-side robusta (RUN, Email)
- Sanitización de inputs de usuario
- Manejo seguro de estados sensibles
- Principio de menor privilegio en permisos

## Escalabilidad y Mantenibilidad

### Principios Aplicados
- **SOLID Principles**: Responsabilidad única por clase
- **Clean Architecture**: Separación clara de capas
- **Dependency Injection**: Manual pero efectivo
- **Testable Design**: Lógica separada de UI

### Futuras Expansiones Posibles
- Integración con APIs REST (Retrofit)
- Base de datos local (Room)
- Push notifications
- Autenticación OAuth
- Sincronización multi-dispositivo
- Analytics y métricas

## Testing Strategy

### Tipos de Testing Implementados
- **Unit Testing**: Lógica de ViewModels
- **Integration Testing**: Flujos completos
- **UI Testing**: Compose UI components
- **Manual Testing**: Dispositivo real

### Casos de Prueba Validados
- Flujo completo de autenticación
- Validación algoritmo RUN chileno
- Integración servicios GPS
- Persistencia datos local
- Navegación entre pantallas
- Estados de carga y error

---

**Conclusión**: La arquitectura implementada sigue las mejores prácticas de desarrollo Android moderno, proporcionando una base sólida, mantenible y escalable para el proyecto LevelUpGamerPanelApp.