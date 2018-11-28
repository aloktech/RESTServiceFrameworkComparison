mvn clean install
sleep 2
docker stop rest.deploy
sleep 2
docker rm rest.deploy
sleep 2
docker rmi rest.deploy
sleep 2
docker build --rm --tag rest.deploy .
sleep 2
docker run -it -d --name rest.deploy --network rest-net rest.deploy
