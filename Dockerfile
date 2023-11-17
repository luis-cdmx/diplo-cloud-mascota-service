# Usa la imagen oficial de Maven como imagen base
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Copia los archivos de configuración y el código fuente
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

# Establece el directorio de trabajo
WORKDIR /usr/src/app

# Compila la aplicación
RUN mvn clean install

# Cambia a una imagen más ligera de Java para la ejecución
FROM openjdk:17.0.2-slim

# Copia el archivo JAR generado en la etapa anterior
COPY --from=build /usr/src/app/target/mascota*.jar /app/mascota-rest.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "/app/mascota-rest.jar"]

# Se agrega este comentario para la practica 2 del modulo 5
