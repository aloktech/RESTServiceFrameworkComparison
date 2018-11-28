FROM openjdk:11
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/Deployment.jar Deployment.jar
ADD target/lib/*.jar lib/
ADD default.json /
CMD ["java", "-jar","Deployment.jar"]