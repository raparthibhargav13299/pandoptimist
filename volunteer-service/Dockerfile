FROM openjdk:11-jre-slim
ADD target/volunteer-service-0.0.1-SNAPSHOT.jar volunteer-service.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","volunteer-service.jar"]
