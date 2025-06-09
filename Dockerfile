FROM maven:3.9.8 AS builder

WORKDIR /SpringWebApp_lab8_bootstrap

COPY pom.xml .

COPY src /SpringWebApp_lab8_bootstrap/src

RUN ["mvn", "clean", "package", "-DskipTests=true"]

FROM eclipse-temurin:21-jdk

WORKDIR /SpringWebApp_lab8_bootstrap

COPY --from=builder /SpringWebApp_lab8_bootstrap/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]