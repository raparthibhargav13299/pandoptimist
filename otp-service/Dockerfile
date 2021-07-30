FROM openjdk:11-jre-slim
ADD target/otp-service-0.0.1-SNAPSHOT.jar otp-spring-boot.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","otp-spring-boot.jar"]
