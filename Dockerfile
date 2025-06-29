FROM openjdk:21-jdk-slim
LABEL authors="Ray_1024"
WORKDIR /app
COPY target/company-server-manipulator-testtask-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]