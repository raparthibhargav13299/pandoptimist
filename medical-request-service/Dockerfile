FROM openjdk:11-jre-slim
ADD target/medical-request-service-0.0.1-SNAPSHOT.jar medical-spring-boot.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","medical-spring-boot.jar"]