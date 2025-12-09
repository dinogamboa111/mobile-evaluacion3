# mobile-evaluacion3



#  [Galaxy_Task]

Aplicación móvil desarrollada en **Android (Kotlin)** que consume servicios desde un **backend en Spring Boot**, permitiendo la gestión de registro de misiones, ademas de un compendio de planetas para su exploracion y conocimiento.

---

##  Integrantes

- Camila Erices
- Dino Gamboa


---

##  Funcionalidades


-  Estadisticas de misiones en curso 
-  Consumo de API REST desde backend  
-  Listado de [misiones , planetas]  
-  Crear, editar y eliminar registros  
-  Sincronización con base de datos PostgreSQL  
-  Interfaz moderna con arquitectura MVVM  

---

### 🔹 Microservicio (Spring Boot)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/misiones | Listar misiones |
| GET | /api/misiones/{id} | Obtener misión por ID |
| POST | /api/misiones | Crear misión |
| PUT | /api/misiones/{id} | Actualizar misión |
| DELETE | /api/misiones/{id} | Eliminar misión |


| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /api/planetas/ | Crear planetas |
| GET | /api/planetas/ | Listar planetas |

---

##  Pasos para Ejecutar el Proyecto

### 🔹 1. Backend (Spring Boot)

1. Clonar el repositorio:

   - puedes descargar repositorio, cuenta con el backend , ademas del app prebuild comprimido(debido a problemas de compatibilidad desde desktop con app kotlin)
   - manualmente puedes generar un build para poder asociarlo a la api backend, recordar asociar bbdd en prosgress, uso de retrofit para manejo de comunicacion.

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/tu_bd
spring.datasource.username=postgres
spring.datasource.password=tu_password

text
Copiar código
http://localhost:8080

2. Aplicación Android (APK)

Descomprime ZIP contenedora
Ejecuta en andriod Studios
Configurar la URL del backend en:

kotlin
Copiar código
BASE_URL = "http://[IP_DEL_BACKEND]:8080/" // puede revisar ApiCliente.kt con enlace, en cuanto a emulador se suele usar
private const val BASE_URL = "http://10.0.2.2:8080/" como url asociada, indicando comunicacion con puerto 8080.
Ejecutar en emulador o dispositivo físico:

Captura del APK Firmado y Archivo .JKS
APK Generado


<img width="718" height="504" alt="image" src="https://github.com/user-attachments/assets/11553474-d11f-4d9f-a1d3-e1e76fedc5e7" />

Archivo .JKS


<img width="908" height="240" alt="image" src="https://github.com/user-attachments/assets/47a6a74c-c1e5-48d8-91ec-e07b474d8d2f" />


Tecnologías Utilizadas
Kotlin

Android Studio

Retrofit

MVVM

Spring Boot

PostgreSQL

Git & GitHub

Gradle

Axios
---








