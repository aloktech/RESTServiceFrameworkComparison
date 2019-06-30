#FROM amazoncorretto:11.0.3.7.1
FROM amazoncorretto:8u212
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/RESTServiceForUI.jar RESTServiceForUI.jar
ADD target/lib/*.jar lib/
EXPOSE 8096
CMD ["java","-showversion", "-jar","RESTServiceForUI.jar"]