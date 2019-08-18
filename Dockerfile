FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:8-jdk-alpine
ADD target/bowling*.jar bowling.jar
ADD src/integration-test/resources/games/demogame.txt demogame.txt
EXPOSE 8080
CMD java -jar bowling.jar demogame.txt