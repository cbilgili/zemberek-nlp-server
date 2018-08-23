FROM openjdk:8-jdk-alpine
MAINTAINER cbilgili

ADD target/zemberek-nlp-server-jar-with-dependencies.jar /app.jar
CMD ["java", "-jar", "/app.jar"]