FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/microco-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java" , "-jar" , "app.jar"]


