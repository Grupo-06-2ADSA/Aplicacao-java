FROM openjdk:17-jdk-slim
RUN apt-get update
WORKDIR /app

COPY target-java /app
COPY wait-for-it.sh /app

RUN chmod +x wait-for-it.sh

ENTRYPOINT ["./wait-for-it.sh", "bd-mindcore:3306", "--", "java", "-jar", "login-mind-core-1.0-SNAPSHOT-jar-with-dependencies.jar"]
