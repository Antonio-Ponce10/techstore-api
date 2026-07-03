# Usamos una imagen oficial de Java (Ajusta el 17 si usas otra versión de Java)
FROM eclipse-temurin:17-jdk-alpine

# Directorio temporal
VOLUME /tmp

# Copiamos el archivo .jar compilado al contenedor
COPY target/*.jar app.jar

# Exponemos el puerto donde corre la API (usualmente 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]