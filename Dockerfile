FROM openjdk:8-jdk-alpine

VOLUME /tmp

RUN addgroup -g 1000 -S app \
  && adduser -u 1000 -S -G app app

USER app

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar", "/app.jar"]
