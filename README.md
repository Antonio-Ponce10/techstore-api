# TechStore API

Esta es una API RESTful desarrollada con Spring Boot para la gestión de productos y categorías de una tienda de tecnología.

## Requisitos Previos
Para ejecutar este proyecto necesitas tener instalado:
* Java 17 o superior.
* PostgreSQL (Asegúrate de configurar tus credenciales en `src/main/resources/application.properties`).
* Git.

## Instrucciones de Ejecución

Sigue estos pasos para desplegar la aplicación localmente:

### 1. Clonar el repositorio
Abre tu terminal y ejecuta el siguiente comando:
`git clone https://github.com/Antonio-Ponce10/techstore-api.git`

### 2. Entrar a la carpeta del proyecto
`cd techstore-api`

### 3. Instalar dependencias y compilar
Utiliza el wrapper de Maven incluido en el proyecto para descargar las dependencias:
* En Windows: `.\mvnw.cmd clean install`
* En Mac/Linux: `./mvnw clean install`

### 4. Ejecutar la aplicación
Para levantar el servidor de Spring Boot, ejecuta:
* En Windows: `.\mvnw.cmd spring-boot:run`
* En Mac/Linux: `./mvnw spring-boot:run`

Una vez que la aplicación esté corriendo, puedes acceder a la documentación de la API (Swagger) ingresando a:
`http://localhost:8080/swagger-ui/index.html`