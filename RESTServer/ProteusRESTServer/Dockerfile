FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/ProteusRESTServer.jar ProteusRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8098
CMD ["java","-showversion", "-jar","ProteusRESTServer.jar"]