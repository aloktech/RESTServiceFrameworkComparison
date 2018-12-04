mvn clean install
docker stop ui
docker rm ui
docker rmi ui
docker build --rm --tag ui . 
docker network rm rest-net
docker network create --driver bridge rest-net
docker run -it -d -p 8090:8080 --name ui --network rest-net ui
