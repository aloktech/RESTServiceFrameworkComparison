FROM openjdk:8
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/SpringBootRESTServer.jar SpringBootRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8097
CMD ["java", "-jar","SpringBootRESTServer.jar"]