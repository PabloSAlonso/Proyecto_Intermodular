# Build stage: compila el WAR desde el código fuente actual
FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY apirest/pom.xml apirest/pom.xml
COPY apirest/src apirest/src
RUN mvn -f apirest/pom.xml -DskipTests package

# Runtime stage: despliega el WAR en Tomcat
FROM tomcat:10.1-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=builder /build/apirest/target/apirest.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
