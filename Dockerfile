# Stage 1: construir el WAR con Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copiamos pom.xml primero para cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Construimos el WAR
RUN mvn clean package

# Stage 2: Tomcat
FROM tomcat:10.0.27-jdk17

# Limpiamos apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos el WAR generado desde el stage anterior
COPY --from=build /app/target/apirest.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos puerto 8080
EXPOSE 8080

# Arrancamos Tomcat
CMD ["catalina.sh", "run"]