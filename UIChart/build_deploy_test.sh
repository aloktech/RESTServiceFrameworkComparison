mvn clean install
docker stop ui
docker rm ui
docker rmi ui
docker build --rm --tag ui . 
docker run -it -d -p 8090:8080 --name ui --network rest-net ui
