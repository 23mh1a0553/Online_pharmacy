FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/online_pharmacy-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8099

ENTRYPOINT ["java","-jar","app.jar"]