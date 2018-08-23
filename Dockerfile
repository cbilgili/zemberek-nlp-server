FROM openjdk:8-jdk-alpine
MAINTAINER cbilgili

ADD target/turkish-nlp-examples-1.0.jar /usr/local/turkish-nlp-examples/
CMD ["java", "-cp", "/usr/local/turkish-nlp-examples/target/turkish-nlp-examples-1.0.jar", "server.ServiceMain"]