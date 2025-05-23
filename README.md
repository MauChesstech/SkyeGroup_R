Descripción de la aplicación...

Esta aplicación permite gestionar una lista de tareas (To-Dos) obtenidas desde una API externa (JSONPlaceholder) con capacidad de funcionamiento offline. Los usuarios pueden:
* Ver la lista de tareas en tarjetas
* Marcar tareas como completadas
* Eliminar tareas
* Trabajar sin conexión a internet


ORGANIZACIÓN DEL CODIGO POR CAPAS (MVVM):

├── app/

├── adapter                # RecyclerView adapters (e.g., TodoAdapter)

├── core                   # Dependency injection and core modules (Retrofit, Room, Network)

│   ├── NetworkModule

│   ├── RetrofitHelper

│   └── RoomModule

├── data.model             # Data layer

│   ├── databaseEntities   # Room entities and DAO interfaces

│   │   ├── TodoDao

│   │   ├── TodoDatabase

│   │   └── TodoEntities.kt

│   └── network            # Network models

│       └── TodoModel

├── domain                 # Domain layer (Use Cases and Business Models)

│   ├── model

│   │   ├── Todo.kt

│   │   ├── DeleteTodoUseCase

│   │   ├── GetRandomTodosUseCase

│   │   ├── GetTodosUseCase

│   │   └── UpdateTodoUseCase

├── ui                     # Presentation layer (MVVM)

│   ├── view               # Activities and Fragments

│   │   ├── MainActivity

│   │   └── TodoList

│   └── viewModel          # ViewModels

│       └── TodoViewModel

└── SkyeGroupApp           # Application class for Hilt initialization



* DECISIONES TÉCNICAS Y PROBLEMAS ENFRENTADOS
  
1. Organización del código
   
Decidí implementar una estructura limpia con:
  Repositorio único: Para manejar automáticamente el caché (primero local, luego remoto)
  Corrutinas con Flow: Para manejo asíncrono y actualización automática de la UI
  Inyección de dependencias con Hilt: Para facilitar el testing y mantenimiento


3. Problemas técnicos y soluciones

Problema 1: Sincronización entre datos locales y remotos
Solución: Implementé un sistema de caché que primero muestra datos locales (si existen) y luego actualiza desde la API en segundo plano

Problema 2: Manejo de estados sin conexión
Solución: Usé un interceptor de OkHttp para detectar conexión y mostrar mensajes adecuados

Problema 3: Actualización de CheckBox en RecyclerView
Solución: Implementé un callback en el ViewHolder para evitar problemas de reciclaje de vistas


REQUERIMIENTOS IMPLEMENTADOS

✅ Consumo de API con Retrofit + OkHttp

✅ Persistencia local con Room

✅ MVVM con Repository Pattern

✅ Inyección de dependencias con Hilt

✅ Manejo de corrutinas con Flow

✅ Modo offline funcional

✅ Diseño con Material Components

✅ ViewBinding en todas las vistas


* CAPTURAS DE PANTALLA

![image](https://github.com/user-attachments/assets/6d73990d-db40-4a46-a72f-318da8446766), ![image](https://github.com/user-attachments/assets/926aad27-1368-40f9-9d59-df5bc69bb241), ![image](https://github.com/user-attachments/assets/47d56875-9068-4fec-927d-df696a0902fd), ![image](https://github.com/user-attachments/assets/c56b5327-099d-49c5-a31c-3d6a90b4255f)




* UBICACIÓN DEL APK FIRMADO
  
El APK firmado se encuentra en: /app/release/apkSkyeGroup_v1.apk

QUÉ HARÍA DIFERENTE CON MÁS TIEMPO

- Testing: Implementaría pruebas unitarias para ViewModels y repositorio
- Sincronización bidireccional: Permitiría actualizar el servidor cuando hay conexión
- Mejor manejo de errores: Pantallas específicas para diferentes tipos de errores
- Soporte para tablets: Diseño responsive para pantallas más grandes
- Sistema de sincronización: Usaría WorkManager para sincronizaciones periódicas
- Implementación de autenticación para usuarios y roles.
