FROM maven:3.8.4-openjdk-17 as builder

ADD . .

RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-slim

RUN   groupadd -g 1000 appuser \
    && useradd --no-log-init --shell /bin/bash -u 1000 -g 1000 -o -c "" -m appuser \
    && cp -r /etc/skel/. /home/appuser \
    && chown -R 1000:1000 /home/appuser

WORKDIR /home/appuser

COPY --from=builder /target/*.jar /home/appuser/app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar" , "/home/appuser/app.jar"]
