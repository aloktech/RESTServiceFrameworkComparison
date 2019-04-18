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
curl -i -d '{"services": [{"base_url": "http://192.168.1.8:8092/JerseyInPayaraServer/rest/jersey","rest_service": "Jersey","server": "Payara 5"}, {"base_url": "http://192.168.1.8:8093/JerseyInTomcatServer/rest/jersey","rest_service": "Jersey","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8094/RestEasyInWildflyServer/rest/resteasy","rest_service": "RestEasy","server": "Wildlfy 14"}, {"base_url": "http://192.168.1.8:8095/rest/spark","rest_service": "Spark Java","server": "Jetty"}, {"base_url": "http://192.168.1.8:8096/rest/javalin","rest_service": "Javalin","server": "Jetty"}, {"base_url": "http://192.168.1.8:8097/rest/springboot","rest_service": "Spring Boot 2.0","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8099/rest/helidon","rest_service": "Helidon","server": "Netty"}, {"base_url": "http://192.168.1.8:8087/rest/jooby","rest_service": "Jooby","server": "Netty"}, {"base_url": "http://192.168.1.8:3000/rest/nodejs","rest_service": "NodeJS","server": "NodeJS"}, {"base_url": "http://192.168.1.8:8086/rest/vertx","rest_service": "Vertx","server": "Netty"}]}' -H "Content-Type: application/json" -X POST http://localhost:8091/rest/regis
curl -i http://localhost:8091/rest/all
