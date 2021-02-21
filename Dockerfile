FROM zenika/kotlin:1.1.61-jdk8

RUN apt-get update
RUN apt-get install -y maven

EXPOSE 9090
