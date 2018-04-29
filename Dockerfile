FROM openjdk:8-jdk-alpine
MAINTAINER www.arima.eu

#/tmp is where a Spring Boot application creates working directories for Tomcat by default
VOLUME /tmp
COPY target/spring-boot-dex-*.jar app.jar

#Add a system property pointing to "/dev/urandom" as a source of entrop to reduce Tomcat startup time
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]