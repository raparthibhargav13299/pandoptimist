FROM openjdk:11-jre-slim
ADD target/donor-service-0.0.1-SNAPSHOT.jar donor-spring-boot.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","donor-spring-boot.jar"]
