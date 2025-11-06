# MANUAL DE USUARIO - LevelUpGamerPanelApp

**Guía completa para usar la aplicación de gestión administrativa**

## Introducción

LevelUpGamerPanelApp es una aplicación móvil diseñada para la gestión administrativa de una tienda de gaming. Esta aplicación permite manejar usuarios, productos y pedidos de manera eficiente desde dispositivos Android.

### Características Principales
- Sistema de autenticación seguro
- Registro de usuarios con validación avanzada
- Gestión completa de inventario
- Seguimiento de pedidos
- Interfaz moderna y fácil de usar
- Funciona completamente offline

## Requisitos del Sistema

### Dispositivo Compatible
- **Sistema Operativo**: Android 7.0 (API 24) o superior
- **RAM**: Mínimo 2GB recomendado
- **Almacenamiento**: 50MB de espacio libre
- **Sensores**: GPS (para registro de usuarios)

### Permisos Necesarios
La aplicación solicitará los siguientes permisos:
- **Ubicación**: Para capturar automáticamente ubicación durante el registro
- **Vibración**: Para proporcionar feedback táctil
- **Internet**: Para futuras funcionalidades online (opcional)

## Primeros Pasos

### Instalación
1. Descarga el archivo APK desde el repositorio oficial
2. Habilita "Fuentes desconocidas" en configuración de Android
3. Instala la aplicación
4. Abre LevelUpGamerPanelApp desde el menú de aplicaciones

### Primera Ejecución
Al abrir la aplicación por primera vez:
1. Aparece una pantalla de bienvenida animada con el logo
2. Se direcciona automáticamente al sistema de login
3. La aplicación solicita permisos necesarios

## Sistema de Autenticación

### Iniciar Sesión

#### Credenciales de Acceso
Para acceder a la aplicación, utiliza las siguientes credenciales:

```
Usuario: admin
Contraseña: 1234
```

#### Pasos para Iniciar Sesión
1. Ingresa "admin" en el campo Usuario
2. Escribe "1234" en el campo Contraseña  
3. Presiona "Iniciar Sesión"

#### Indicadores Visuales
- **Campo verde**: Datos correctos
- **Campo rojo**: Datos incorrectos o vacíos
- **Botón con spinner**: Procesando inicio de sesión
- **Botón habilitado**: Listo para enviar
- **Botón deshabilitado**: Faltan datos o son incorrectos

#### Mensajes de Error Comunes
- *"Usuario y contraseña son requeridos"*: Completa ambos campos
- *"Credenciales incorrectas"*: Verifica usuario y contraseña
- *"Error de conexión"*: Problema técnico temporal

## Registro de Nuevos Usuarios

### Acceder al Registro
1. En la pantalla de login, presiona "Registrarse"
2. Se abre el formulario de registro completo

### Completar el Formulario

#### Campos Obligatorios

**Información Personal**
- **Nombre**: Nombre completo del usuario
- **Email**: Dirección de correo electrónico válida
- **Teléfono**: Número de contacto

**Identificación**
- **RUN**: Número de identificación chileno (con guión y dígito verificador)
  - Ejemplo correcto: `12345678-5`
  - Ejemplo incorrecto: `12345678-9`

**Ubicación**
- **Dirección**: Dirección completa
- **Ciudad**: Ciudad de residencia
- **Región**: Región del país

#### Validaciones Automáticas

**RUN Chileno**
La aplicación valida automáticamente que el RUN sea correcto usando el algoritmo oficial chileno:
- **RUN válido**: Campo verde, puede continuar
- **RUN inválido**: Campo rojo con mensaje de error

**Email**
- Verifica formato válido de email
- Ejemplo correcto: `usuario@email.com`
- Ejemplo incorrecto: `usuario@email`

### Obtener Ubicación con GPS

#### Activar GPS
1. Presiona el botón "Obtener Ubicación"
2. La aplicación solicita permisos de ubicación
3. Acepta los permisos cuando aparezca el diálogo
4. Espera mientras se obtiene la ubicación

#### Proceso de Geolocalización
- **Buscando**: La aplicación busca ubicación
- **Vibración**: El teléfono vibra cuando encuentra la ubicación  
- **Éxito**: Los campos de ubicación se llenan automáticamente
- **Error**: Mensaje explicativo si no se puede obtener ubicación

#### Permisos de Ubicación
Si se denegaron los permisos anteriormente:
1. Ve a Configuración → Aplicaciones → LevelUpGamerPanel
2. Selecciona Permisos
3. Habilita Ubicación
4. Regresa a la aplicación y vuelve a intentar

### Finalizar Registro
1. Completa todos los campos obligatorios
2. Verifica que las validaciones sean correctas (campos verdes)
3. Presiona "Registrar Usuario"
4. Espera confirmación de registro exitoso
5. Serás dirigido al dashboard automáticamente

## Dashboard Principal

### Pantalla de Inicio

Al iniciar sesión exitosamente, se accede al dashboard principal que contiene:

#### Navegación Principal
- **Usuarios**: Gestionar clientes y administradores
- **Productos**: Administrar inventario de gaming
- **Pedidos**: Seguimiento de órdenes y ventas
- **Configuración**: Ajustes de la aplicación

#### Información Rápida
- Total de usuarios registrados
- Productos en inventario  
- Pedidos pendientes
- Estadísticas básicas

#### Acciones Rápidas
- Agregar nuevo producto
- Crear pedido
- Ver reportes
- Actualizar inventario

## Gestión de Usuarios

### Lista de Usuarios

#### Ver Todos los Usuarios
1. Desde el dashboard, presiona "Usuarios"
2. Se muestra la lista completa de usuarios registrados
3. Cada usuario muestra:
   - Nombre completo
   - Email de contacto
   - Fecha de registro
   - Estado (Activo/Inactivo)

#### Buscar Usuarios
- Utiliza la barra de búsqueda en la parte superior
- Busca por nombre, email o RUN
- Los resultados se filtran automáticamente

### Crear Nuevo Usuario
1. Presiona el botón "Agregar Usuario"
2. Completa el formulario (similar al registro)
3. Presiona "Guardar"
4. El usuario aparece inmediatamente en la lista

### Editar Usuario Existente
1. Toca sobre un usuario de la lista
2. Se abre el formulario de edición
3. Modifica los campos necesarios
4. Presiona "Actualizar" para guardar cambios

### Eliminar Usuario
1. Mantén presionado sobre un usuario
2. Aparece menú contextual
3. Selecciona "Eliminar"
4. Confirma la acción en el diálogo
5. El usuario se elimina permanentemente

## Gestión de Productos

### Catálogo de Productos

#### Ver Inventario
1. Navega a "Productos" desde el dashboard
2. Visualiza todos los productos disponibles:
   - Imagen del producto
   - Nombre y descripción
   - Precio actual
   - Stock disponible
   - Categoría (Consolas, Juegos, Accesorios)

#### Filtrar Productos
- **Por categoría**: Consolas, Juegos, Accesorios
- **Por disponibilidad**: En stock, Agotado
- **Por precio**: Rango de precios
- **Búsqueda de texto**: Nombre o descripción

### Agregar Nuevo Producto
1. Presiona "Nuevo Producto"
2. Completa la información:
   - **Nombre**: Título del producto
   - **Descripción**: Detalles del producto
   - **Precio**: Valor en pesos chilenos
   - **Stock**: Cantidad disponible
   - **Categoría**: Tipo de producto
   - **Código**: SKU único (opcional)

3. Presiona "Guardar Producto"

### Actualizar Inventario
1. Selecciona un producto de la lista
2. Modifica el stock según necesites
3. Actualiza el precio si es necesario
4. Guarda los cambios

### Gestión de Stock
- **Verde**: Stock suficiente (>10 unidades)
- **Amarillo**: Stock bajo (5-10 unidades)
- **Rojo**: Stock crítico (<5 unidades)
- **Gris**: Producto agotado

## Gestión de Pedidos

### Lista de Pedidos

#### Estados de Pedidos
- **Pendiente**: Recién creado, pendiente de procesar
- **Despachado**: Enviado al cliente
- **Cancelado**: Pedido cancelado

#### Información de Cada Pedido
- Número de pedido único
- Cliente que realizó el pedido
- Fecha y hora de creación
- Total del pedido
- Estado actual
- Productos incluidos

### Crear Nuevo Pedido
1. Presiona "Nuevo Pedido"
2. Selecciona el cliente de la lista
3. Agrega productos al pedido:
   - Busca productos por nombre
   - Selecciona cantidad deseada
   - Verifica disponibilidad en stock
4. Revisa el resumen del pedido
5. Confirma y crea el pedido

### Actualizar Estado de Pedido
1. Selecciona un pedido de la lista
2. Cambia el estado según corresponda:
   - Pendiente → Despachado
   - Pendiente → Cancelado
   - Despachado → Cancelado (si es necesario)
3. Agrega notas si es necesario
4. Guarda los cambios

### Cancelar Pedido
1. Selecciona el pedido a cancelar
2. Presiona "Cancelar Pedido"
3. Especifica el motivo de cancelación
4. Confirma la acción
5. El stock se restaura automáticamente

## Personalización y Configuración

### Temas de la Aplicación
La aplicación se adapta automáticamente al tema del dispositivo:
- **Tema Claro**: Para uso diurno
- **Tema Oscuro**: Para uso nocturno o ambientes con poca luz

### Animaciones
- Transiciones suaves entre pantallas
- Botones animados con efectos de presión
- Loading animations durante procesos
- Feedback visual instantáneo

### Accesibilidad
- Textos escalables según configuración del sistema
- Alto contraste disponible
- Navegación por teclado soportada
- Lectores de pantalla compatibles

## Almacenamiento y Datos

### Datos Locales
- Todos los datos se guardan en el dispositivo
- No requiere conexión a internet para funcionar
- Datos persistentes incluso al cerrar la aplicación
- Respaldo automático de información crítica

### Sincronización
- Los datos se mantienen sincronizados entre sesiones
- Estados actualizados automáticamente
- Consistencia en toda la aplicación

### Privacidad
- Información local únicamente en el dispositivo
- No se envían datos a servidores externos
- Cumple con regulaciones de privacidad

## Solución de Problemas

### Problemas Comunes y Soluciones

#### No puedo iniciar sesión
**Solución:**
1. Verifica las credenciales: `admin` / `1234`
2. Asegúrate de que no haya espacios extra
3. Reinicia la aplicación si es necesario

#### GPS no funciona
**Solución:**
1. Verifica que GPS esté activado en el dispositivo
2. Otorga permisos de ubicación a la aplicación
3. Intenta desde un área con mejor señal GPS
4. Reinicia la aplicación y vuelve a intentar

#### RUN inválido
**Solución:**
1. Verifica el formato: `12345678-5`
2. Incluye el guión y dígito verificador
3. Usa un validador online si no estás seguro
4. Ejemplos válidos:
   - `12345678-5`
   - `98765432-1`
   - `11111111-1`

#### La aplicación se cierra inesperadamente
**Solución:**
1. Reinicia el dispositivo
2. Cierra otras aplicaciones para liberar memoria
3. Verifica que tengas suficiente espacio de almacenamiento
4. Actualiza Android si es posible

#### Datos no se guardan
**Solución:**
1. Verifica que tengas espacio de almacenamiento
2. No fuerces el cierre de la aplicación
3. Usa siempre los botones "Guardar" o "Actualizar"
4. Espera a que aparezca confirmación de guardado

### Información de Contacto Técnico
Si experimentas problemas persistentes:
- **Desarrollador**: Richard Moreano
- **Desarrollador**: Victor Vargas
- **Email**: soporte@levelupgamerpanel.com
- **GitHub**: github.com/v1ct0r-ops/LevelUpGamerPanelApp

## Preguntas Frecuentes (FAQ)

### ¿Necesito internet para usar la aplicación?
**R:** No, la aplicación funciona completamente offline. Internet solo será necesario para futuras funcionalidades de sincronización.

### ¿Puedo usar la aplicación en tablet?
**R:** Sí, la aplicación es responsive y se adapta a diferentes tamaños de pantalla.

### ¿Qué pasa si olvido las credenciales?
**R:** Las credenciales por defecto son `admin` / `1234`. Si las cambias, anótalas en un lugar seguro.

### ¿Puedo exportar los datos?
**R:** Actualmente no, pero es una funcionalidad planificada para futuras versiones.

### ¿La aplicación consume mucha batería?
**R:** No, está optimizada para consumo eficiente de batería. Solo usa GPS cuando es necesario.

### ¿Puedo usar la aplicación sin permisos de ubicación?
**R:** Sí, pero no podrás usar la función de "Obtener Ubicación" automática en el registro.

### ¿Los datos están seguros?
**R:** Sí, todos los datos se almacenan localmente en el dispositivo con encriptación básica.

## Consejos de Uso Avanzado

### Productividad
- Usa búsquedas para encontrar información rápidamente
- Aprovecha los filtros en listas largas
- Mantén actualizado el stock regularmente
- Revisa pedidos pendientes diariamente

### Mejores Prácticas
- Haz respaldo de datos importantes manualmente
- Mantén la aplicación actualizada
- Usa nombres descriptivos para productos
- Revisa validaciones antes de guardar

### Atajos de Productividad
- Mantén presionado para acciones rápidas
- Desliza para navegación fluida
- Toca dos veces para edición rápida
- Usa búsqueda global para encontrar cualquier cosa

## Glosario de Términos

- **APK**: Archivo de instalación de aplicación Android
- **Dashboard**: Pantalla principal con resumen de información
- **CRUD**: Crear, Leer, Actualizar, Eliminar (operaciones básicas)
- **GPS**: Sistema de Posicionamiento Global
- **RUN**: Rol Único Nacional (identificación chilena)
- **Stock**: Cantidad disponible de productos
- **UI/UX**: Interfaz de Usuario / Experiencia de Usuario

## Lista de Verificación para Nuevos Usuarios

### Configuración Inicial
- [ ] Aplicación instalada correctamente
- [ ] Permisos de ubicación otorgados
- [ ] Primera sesión iniciada con éxito
- [ ] Dashboard principal visible

### Funcionalidades Básicas
- [ ] Crear nuevo usuario probado
- [ ] Agregar producto al inventario
- [ ] Crear pedido de prueba
- [ ] Navegar entre todas las pantallas

### Características Avanzadas
- [ ] GPS funcionando correctamente
- [ ] Validación RUN probada
- [ ] Búsquedas y filtros funcionando
- [ ] Estados de pedidos actualizados

---

**Período de Desarrollo**: 23 Octubre - 5 Noviembre 2025
**Versión de la App**: 1.0.0