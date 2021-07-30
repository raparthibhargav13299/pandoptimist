FROM openjdk:11-jre-slim
ADD target/doctor-consultation-service-0.0.1-SNAPSHOT.jar doctor-spring-boot.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","doctor-spring-boot.jar"]