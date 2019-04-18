FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/SpringBootRESTServer.jar SpringBootRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8097
CMD ["java","-showversion", "-jar","SpringBootRESTServer.jar"]