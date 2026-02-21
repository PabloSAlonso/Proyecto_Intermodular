# Usa la versión exacta de Tomcat que quieres
FROM tomcat:10.0.27-jdk17

# Elimina la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu WAR al directorio webapps y renómbralo a ROOT.war
COPY target/apirest.war /usr/local/tomcat/webapps/ROOT.war

# Expón el puerto 8080 (Render sobrescribirá con la variable PORT)
EXPOSE 8080

# Arranca Tomcat en modo foreground
CMD ["catalina.sh", "run"]