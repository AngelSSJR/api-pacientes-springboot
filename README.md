# 🏥 API REST de Gestión de Pacientes

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/Security-JWT-red)
![Swagger](https://img.shields.io/badge/Documentaci%C3%B3n-Swagger-yellow)

## 📖 Descripción
Proyecto final correspondiente a una API REST para la gestión de pacientes. Permite realizar operaciones el CRUD completo (Crear, Leer, Actualizar, Eliminar) de forma segura utilizando autenticación por tokens JWT
y persiste la información en una base de datos MySQL.

## 🚀 Tecnologías Utilizadas
* **Java 17+**
* **Spring Boot** (Web, Data JPA, Security)
* **MySQL** (Base de datos relacional)
* **JSON Web Tokens (JWT)** (Autenticación y Autorización)
* **Swagger / OpenAPI** (Documentación interactiva)
* **Maven** (Gestión de dependencias)

## ⚙️ Requisitos Previos
* JDK 17 o superior.
* Maven instalado.
* MySQL Server ejecutándose en el puerto 3306.

## 🛠️ Instalación y Configuración

1. **Clonar el repositorio:**
Salida de código
File corrected and generated successfully.

Configurar la Base de Datos:
Crear una base de datos en MySQL llamada exactamente gestion_pacientes.
Las credenciales por defecto en application.properties son:

Usuario: root

Contraseña: root (Modificar según tu entorno local)

Ejecutar la aplicación:

🔐 Seguridad (JWT)
La API está protegida. Para acceder a los endpoints de pacientes, primero debes autenticarte:

Realiza una petición POST a /api/auth/login con las credenciales (admin / admin).

Copia el token JWT devuelto.

Incluye el token en el header de autorización (Authorization: Bearer <token>) para las siguientes peticiones en Postman.

📡 Endpoints Principales
Autenticación
POST /api/auth/login - Iniciar sesión y obtener token JWT.

Pacientes (Requieren Token)
GET /api/pacientes - Obtener lista de todos los pacientes.

GET /api/pacientes/{id} - Obtener un paciente por su ID.

POST /api/pacientes - Registrar un nuevo paciente.

PUT /api/pacientes/{id} - Actualizar los datos de un paciente existente.

DELETE /api/pacientes/{id} - Eliminar un paciente del sistema.

📄 Documentación Swagger
Una vez que la aplicación esté corriendo, puedes ver y probar la API desde la interfaz gráfica de Swagger en:
http://localhost:8080/swagger-ui.html

Desarrollado como Proyecto Final.
