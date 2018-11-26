mvn clean install
sleep 2 
docker stop rest.helidon
sleep 2 
docker rm rest.helidon
sleep 2 
docker rmi rest.helidon
sleep 2 
docker build --rm --tag rest.helidon .
sleep 2 
docker run --rm -it -d -p 8099:8084 --name rest.helidon rest.helidon
sleep 30 
curl http://localhost:8099/rest/helidon

