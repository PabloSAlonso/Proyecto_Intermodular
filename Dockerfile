# Usamos imagen de Maven para compilar
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos pom.xml y src desde la carpeta apirest
COPY apirest/pom.xml .
COPY apirest/src ./src

# Ejecutamos la compilación de Maven y generamos el .war
RUN mvn clean package -DskipTests

# Usamos Tomcat para servir la aplicación
FROM tomcat:10.1-jdk17

# Eliminamos la aplicación default de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos el .war generado al directorio webapps de Tomcat
COPY --from=build /app/target/apirest.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]