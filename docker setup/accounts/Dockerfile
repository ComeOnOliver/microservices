#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

LABEL Author="Haoran" 

#Add the application to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute this application when the container starts
ENTRYPOINT [ "java", "-jar", "accounts-0.0.1-SNAPSHOT.jar" ]