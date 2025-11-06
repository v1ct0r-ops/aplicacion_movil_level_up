// Configuración de plugins (las "herramientas" que usa Gradle para compilar)
pluginManagement {
    // Aquí defino dónde Gradle busca los plugins
    repositories {
        google()            // Repositorio de Google (para plugins de Android)
        mavenCentral()      // Repositorio central de Maven (la mayoría de librerías)
        gradlePluginPortal() // Repositorio oficial de plugins de Gradle
    }
    
    // Defino qué versiones específicas de plugins voy a usar
    plugins {
        id("com.android.application") version "8.13.0"                // Plugin para crear apps Android
        id("org.jetbrains.kotlin.android") version "2.0.21"           // Plugin de Kotlin para Android
        id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"  // Para convertir objetos a JSON
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"    // Para usar Jetpack Compose
    }
}

// Configuración de cómo resolver las dependencias (librerías externas)
dependencyResolutionManagement {
    // Falla si defino repositorios en otros lugares (mantiene el orden)
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    
    // Aquí defino dónde buscar las librerías que necesito
    repositories {
        google()        // Para librerías de Google/Android
        mavenCentral()  // Para librerías de la comunidad
    }
}

// El nombre de mi proyecto (aparece en Android Studio)
rootProject.name = "LevelUpGamerPanel_App"

// Incluyo el módulo ":app" (mi aplicación principal)
include(":app")
