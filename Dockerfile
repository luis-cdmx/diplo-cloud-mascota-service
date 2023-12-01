# Usa la imagen oficial de Maven como imagen base
FROM docker.io/amazoncorretto:17.0.8-alpine3.18

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR generado en la etapa anterior
COPY target/mascota*.jar /app/mascota-rest.jar

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8084

# Comando para ejecutar la aplicación al iniciar el contenedor
CMD ["java", "-jar", "/app/mascota-rest.jar"]

