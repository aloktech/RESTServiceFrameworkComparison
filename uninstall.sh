docker stop ui
docker rm ui
docker rmi ui

docker stop ui.rest
docker rm ui.rest
docker rmi ui.rest

docker stop rest.jersey.payara
docker rm rest.jersey.payara
docker rmi rest.jersey.payara

docker stop rest.jersey.tomcat
docker rm rest.jersey.tomcat
docker rmi rest.jersey.tomcat

docker stop rest.resteasy.wildfly
docker rm rest.resteasy.wildfly
docker rmi rest.resteasy.wildfly

docker stop rest.jooby
docker rm rest.jooby
docker rmi rest.jooby

docker stop rest.javalin
docker rm rest.javalin
docker rmi rest.javalin

docker stop rest.spark
docker rm rest.spark
docker rmi rest.spark

docker stop rest.helidon
docker rm rest.helidon
docker rmi rest.helidon

docker stop rest.springboot
docker rm rest.springboot
docker rmi rest.springboot

docker stop rest.nodejs
docker rm rest.nodejs
docker rmi rest.nodejs

docker stop rest.vertx
docker rm rest.vertx
docker rmi rest.vertx

docker stop rest.quarkus
docker rm rest.quarkus
docker rmi rest.quarkus

docker stop rest.micronaut
docker rm rest.micronaut
docker rmi rest.micronaut

docker images