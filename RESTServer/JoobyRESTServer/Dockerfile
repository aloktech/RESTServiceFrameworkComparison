FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/JoobyRESTServer.jar JoobyRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8087
CMD ["java","-showversion", "-jar","JoobyRESTServer.jar"]