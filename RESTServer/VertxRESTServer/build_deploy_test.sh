mvn clean install
sleep  2
docker stop rest.vertx
docker rm rest.vertx
docker rmi rest.vertx
docker build --rm -t rest.vertx .
docker run -it -d -p 8086:8084 --name rest.vertx --network rest-net rest.vertx
sleep 10
curl -i http://localhost:8086/rest/vertx