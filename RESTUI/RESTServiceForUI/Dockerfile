FROM amazoncorretto:8u202
WORKDIR /
CMD ["mkdir", "lib"]
ADD target/RESTServiceForUI.jar RESTServiceForUI.jar
ADD target/lib/*.jar lib/
EXPOSE 8096
CMD ["java","-showversion", "-jar","RESTServiceForUI.jar"]