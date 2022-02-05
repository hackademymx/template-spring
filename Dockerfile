FROM openjdk:17-jdk-alpine

RUN addgroup -S demo && adduser -S demo -G demo
USER demo

VOLUME /tmp
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
