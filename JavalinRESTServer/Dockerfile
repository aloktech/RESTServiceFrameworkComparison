FROM openjdk:8
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/JavalinRESTServer.jar JavalinRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8096
CMD ["java", "-jar","JavalinRESTServer.jar"]