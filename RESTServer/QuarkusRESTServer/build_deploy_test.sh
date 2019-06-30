mvn clean install
sleep  2
docker stop rest.quarkus
sleep 2
docker rm rest.quarkus
sleep 2
docker rmi rest.quarkus
sleep 2
docker build --rm --tag rest.quarkus .
sleep 2
docker run -it -d -p 8088:8080 --name rest.quarkus --network rest-net rest.quarkus
sleep 30
curl http://localhost:8088/rest/quarkus
