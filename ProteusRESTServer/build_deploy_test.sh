mvn clean install
sleep 2
docker stop rest.proteus
sleep 2
docker rm rest.proteus
sleep 2
docker rmi rest.proteus
sleep 2
docker build --rm --tag rest.proteus .
sleep 2
docker run -it -d -p 8098:8090 --name rest.proteus --network rest-net rest.proteus
sleep 30
curl http://localhost:8098/v1/rest/proteus