# Usa una imagen base de OpenJDK 17
FROM openjdk:17

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación al contenedor
COPY crud-api.jar app.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 9000

# Define el comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
