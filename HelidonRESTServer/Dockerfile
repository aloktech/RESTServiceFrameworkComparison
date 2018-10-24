FROM openjdk:8
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/HelidonRESTServer.jar HelidonRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8099
CMD ["java", "-jar","HelidonRESTServer.jar"]