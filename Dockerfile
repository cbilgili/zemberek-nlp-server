FROM ubuntu:16.04
MAINTAINER cbilgili

RUN apt-get update && apt-get install -y python-software-properties software-properties-common
RUN add-apt-repository ppa:webupd8team/java

RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

RUN apt-get update && apt-get install -y oracle-java8-installer maven

ADD . /usr/local/turkish-nlp-examples
RUN cd /usr/local/turkish-nlp-examples && mvn assembly:assembly
CMD ["java", "-cp", "/usr/local/turkish-nlp-examples/target/turkish-nlp-examples-1.0-jar-with-dependencies.jar", "morphology.FindPOSwithSpark"]