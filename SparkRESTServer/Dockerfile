FROM openjdk:11
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/SparkRESTServer.jar SparkRESTServer.jar
ADD target/lib/*.jar lib/
EXPOSE 8095
CMD ["java", "-jar","SparkRESTServer.jar"]