
FROM java:openjdk-8u111-alpine
RUN apk --no-cache add curl
COPY target/libs/*-all.jar MicronautRESTServer.jar
CMD java ${JAVA_OPTS} -jar MicronautRESTServer.jar