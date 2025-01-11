FROM maven:latest
VOLUME /tmp
RUN mkdir -p /home/app
WORKDIR /home/app
COPY ./ .
ADD target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["mvn", "spring-boot:run"]
