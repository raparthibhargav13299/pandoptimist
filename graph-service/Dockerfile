FROM openjdk:11-jre-slim
EXPOSE 8086
ADD target/graph-service-0.0.1-SNAPSHOT.jar graph-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/graph-service-0.0.1-SNAPSHOT.jar"]