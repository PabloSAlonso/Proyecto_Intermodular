# Usamos Tomcat para servir la aplicación
FROM tomcat:10.1-jdk17

# Eliminamos la aplicación default de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiamos el .war existente al directorio webapps de Tomcat
COPY apirest/target/apirest.war /usr/local/tomcat/webapps/ROOT.war

# Exponemos el puerto 8080
EXPOSE 8080

# Comando para iniciar Tomcat
CMD ["catalina.sh", "run"]
