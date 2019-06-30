FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/MicronautRESTServer.jar MicronautRESTServer.jar
ADD target/lib/*.jar lib/
CMD java ${JAVA_OPTS} -jar MicronautRESTServer.jar