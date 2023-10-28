# FROM openjdk:17-jdk-alpine
FROM openjdk:17-oracle
MAINTAINER José Luis Rivera <elocasodelosidolos@gmail.com>
# a default value
ENV MONGO_HOSTNAME mongo_prac
ENV MONGO_PORT 27017
ENV MONGO_AUTHDB admin 
ENV MONGO_DB mascotadb
ENV MONGO_USER mascota_owner
ENV MONGO_PWD mascota_password
ENV TOMCAT_PORT 8084
EXPOSE 8084
ARG JAR_FILE=target/*.jar
COPY target/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]
