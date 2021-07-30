FROM openjdk:11-jre-slim
EXPOSE 8085
ADD target/patient-service-0.0.1-SNAPSHOT.jar patient-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/patient-service-0.0.1-SNAPSHOT.jar"]

