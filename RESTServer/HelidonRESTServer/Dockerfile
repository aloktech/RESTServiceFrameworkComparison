FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/HelidonRESTServer.jar HelidonRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8099
CMD ["java","-showversion", "-jar","HelidonRESTServer.jar"]