FROM openjdk:11-jre-slim
ADD target/chat-service-0.0.1-SNAPSHOT.jar chat-service-0.0.1-SNAPSHOT.jar
EXPOSE 8095
ENTRYPOINT ["java","-jar","chat-service-0.0.1-SNAPSHOT.jar"]