mvn clean install
sleep  2
mvn clean install
docker stop rest.micronaut
sleep 2
docker rm rest.micronaut
sleep 2
docker rmi rest.micronaut
sleep 2
docker build --rm --tag rest.micronaut .
sleep 2
docker run -it -d -p 8089:8088 --name rest.micronaut --network rest-net rest.micronaut
sleep 30
curl http://localhost:8089/rest/micronaut