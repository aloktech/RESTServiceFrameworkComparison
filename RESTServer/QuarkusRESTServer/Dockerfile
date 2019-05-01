FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/QuarkusRESTServer-runner.jar QuarkusRESTServer-runner.jar
ADD target/lib/*.jar lib/
EXPOSE 8096
CMD ["java","-showversion", "-jar","QuarkusRESTServer-runner.jar"]