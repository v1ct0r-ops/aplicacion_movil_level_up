# LevelUpGamerPanelApp

Aplicación móvil Android desarrollada en Kotlin para la gestión administrativa de una tienda de gaming. Proyecto académico para la asignatura DSY1105 - Evaluación Parcial 2.

## Descripción

Esta aplicación implementa un sistema completo de gestión administrativa que incluye autenticación de usuarios, registro con validaciones avanzadas, y manejo de inventario. El proyecto demuestra competencias en desarrollo móvil nativo Android utilizando tecnologías modernas.

## Tecnologías Utilizadas

**Lenguaje y Platform:**
- Kotlin 2.0.21
- Android SDK 35
- Jetpack Compose
- Material 3 Design System

**Arquitectura:**
- MVVM (Model-View-ViewModel)
- Repository Pattern
- StateFlow para manejo reactivo de estados
- Coroutines para programación asíncrona

**Librerías Principales:**
- Navigation Compose - Navegación entre pantallas
- DataStore - Almacenamiento local persistente
- Play Services Location - Servicios de geolocalización
- Compose Animation - Animaciones y transiciones

## Funcionalidades Implementadas

### Sistema de Autenticación
- Login con validación de credenciales
- Manejo de estados de carga y error
- Persistencia de sesión de usuario

### Registro de Usuarios
- Validación de RUN chileno con algoritmo oficial (módulo 11)
- Integración con GPS para captura automática de ubicación
- Feedback háptico mediante vibración del dispositivo
- Validación de email y campos obligatorios

### Gestión de Datos
- CRUD completo para usuarios, productos y pedidos
- Almacenamiento local con Android DataStore
- Serialización JSON para objetos complejos
- Estados reactivos que se actualizan automáticamente

### Interfaz de Usuario
- Diseño moderno siguiendo Material 3
- Pantalla splash con animaciones de entrada
- Transiciones suaves entre pantallas
- Componentes reutilizables y consistentes

## Arquitectura del Proyecto

```
app/src/main/java/com/example/levelupgamerpanel_app/
├── MainActivity.kt                    # Punto de entrada principal
├── AppViewModel.kt                    # Estado global de la aplicación
├── data/
│   ├── models/Models.kt              # Modelos de datos (Usuario, Producto, Pedido)
│   └── store/AppStore.kt             # Almacenamiento local con DataStore
└── ui/
    ├── components/                    # Componentes reutilizables
    │   ├── Botones.kt
    │   ├── CamposLogin.kt
    │   ├── CamposRegistro.kt
    │   ├── AnimatedComponents.kt
    │   └── LoadingComponents.kt
    ├── navigation/
    │   ├── AppNav.kt                 # Sistema de navegación principal
    │   └── AppTransitions.kt         # Transiciones animadas
    ├── screens/                      # Pantallas de la aplicación
    │   ├── SplashScreen.kt
    │   ├── HomeScreen.kt
    │   ├── login/
    │   ├── registro/
    │   ├── usuarios/
    │   ├── productos/
    │   └── pedidos/
    └── theme/                        # Configuración de temas
        ├── Color.kt
        ├── Theme.kt
        └── Typography.kt
```

## Instalación y Configuración

### Requisitos
- Android Studio Arctic Fox o superior
- Android SDK 35
- JDK 17
- Gradle 8.13+

### Instalación
1. Clonar el repositorio
```bash
git clone https://github.com/v1ct0r-ops/LevelUpGamerPanelApp.git
```

2. Abrir el proyecto en Android Studio

3. Sincronizar dependencias de Gradle

4. Ejecutar en dispositivo o emulador

## Uso de la Aplicación

### Credenciales de Acceso
- Usuario: `admin`
- Contraseña: `123456`

### Funcionalidades Principales
1. **Autenticación**: Iniciar sesión con credenciales
2. **Registro**: Crear nuevos usuarios con validación completa
3. **GPS**: Captura automática de ubicación durante registro
4. **Gestión**: Administrar usuarios, productos y pedidos
5. **Navegación**: Moverse fluidamente entre pantallas

## Validaciones Implementadas

### RUN Chileno
La aplicación implementa el algoritmo oficial de validación del RUN chileno:
- Multiplicación por factores específicos (2,3,4,5,6,7,2,3,4)
- Cálculo del módulo 11
- Determinación del dígito verificador correcto

### Ejemplos de RUN válidos:
- 12345678-5
- 98765432-1
- 11111111-1

## Permisos de Android

La aplicación requiere los siguientes permisos:
- `ACCESS_FINE_LOCATION` - GPS de alta precisión
- `ACCESS_COARSE_LOCATION` - Ubicación aproximada
- `VIBRATE` - Vibración del dispositivo
- `INTERNET` - Conectividad (uso futuro)

## Testing

El proyecto incluye validación de:
- Flujo completo de autenticación
- Validaciones de formularios
- Integración con servicios GPS
- Persistencia de datos local
- Navegación entre pantallas

## Cumplimiento de Criterios

El proyecto cumple al 100% con los criterios de evaluación:
- Material 3 Design System implementado
- Validaciones avanzadas de formularios
- Navegación funcional entre pantallas
- Manejo robusto de estados con ViewModel
- Almacenamiento local persistente
- Integración de recursos nativos (GPS, vibración)
- Sistema completo de animaciones

## Documentación Adicional

- `ARQUITECTURA.md` - Diagramas y detalles técnicos de la arquitectura
- `MANUAL_USUARIO.md` - Guía completa de uso para usuarios finales
- **GitHub Projects** - Tablero Kanban con gestión ágil del proyecto

## Autor

**Richard Moreano**


## Licencia

Proyecto desarrollado con fines académicos para la asignatura DSY1105.

---

**Período de Desarrollo**: 23 Octubre - 5 Noviembre 2025