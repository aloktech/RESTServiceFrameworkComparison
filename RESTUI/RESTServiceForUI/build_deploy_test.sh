mvn clean install
sleep  2
docker stop ui.rest
sleep 2
docker rm ui.rest
sleep 2
docker rmi ui.rest
sleep 2
docker build --rm --tag ui.rest .
sleep 2
docker run -it -d -p 8091:7000 --name ui.rest --network rest-net ui.rest
sleep 30
curl -i http://localhost:8091/rest
curl -d '{"services": [ {"base_url": "http://192.168.1.8:8092/JerseyInPayaraServer/rest/jersey","rest_service": "Jersey","server": "Micro Payara"}, {"base_url": "http://192.168.1.8:8093/JerseyInTomcatServer/rest/jersey","rest_service": "Jersey","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8094/RestEasyInWildflyServer/rest/resteasy","rest_service": "RestEasy","server": "Wildfly"}, {"base_url": "http://192.168.1.8:8095/rest/spark","rest_service": "Spark Java","server": "Jetty"}, {"base_url": "http://192.168.1.8:8096/rest/javalin","rest_service": "Javalin","server": "Jetty"}, {"base_url": "http://192.168.1.8:8097/rest/springboot","rest_service": "Spring Boot","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8099/rest/helidon","rest_service": "Helidon","server": "Netty"}, {"base_url": "http://192.168.1.8:8087/rest/jooby","rest_service": "Jooby","server": "Netty"}, {"base_url": "http://192.168.1.8:8086/rest/vertx","rest_service": "Vertx","server": "Netty"}, {"base_url": "http://192.168.1.8:8088/rest/quarkus","rest_service": "Quarkus","server": "Undertow"}, {"base_url": "http://192.168.1.8:8089/rest/micronaut","rest_service": "Micronaut","server": "Netty"}, {"base_url": "http://192.168.1.8:8085","rest_service": "Node JS","server": "Node JS"}]}' -H "Content-Type: application/json" -X POST http://192.168.1.8:8091/rest/regis
curl -i http://localhost:8091/rest/all
