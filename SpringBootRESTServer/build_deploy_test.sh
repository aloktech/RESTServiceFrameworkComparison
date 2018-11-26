mvn clean install
sleep  2
docker stop rest.springboot
sleep  2
docker rm rest.springboot
sleep  2
docker rmi rest.springboot
sleep  2
docker build --rm --tag rest.springboot .
sleep  2
docker run -it -d -p 8097:8083 --name rest.springboot rest.springboot
sleep  30
curl http://localhost:8097/rest/springboot