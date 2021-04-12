FROM openjdk:13-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/tknotes-api-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]