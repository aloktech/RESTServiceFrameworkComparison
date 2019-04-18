FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/VertxRESTServer.jar VertxRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8086
CMD ["java","-showversion", "-jar","VertxRESTServer.jar"]
