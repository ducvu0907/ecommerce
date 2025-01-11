FROM openjdk:21-jdk-slim
LABEL authors="zxd"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]