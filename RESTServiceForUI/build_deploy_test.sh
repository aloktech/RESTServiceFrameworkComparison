mvn clean install
docker stop ui.rest
docker rm ui.rest
docker rmi ui.rest
docker build --rm --tag ui.rest . 
docker run -it -d -p 8091:8080 --name ui.rest --network rest-net ui.rest
sleep 30
curl http://localhost:8091/RESTServiceForUI/rest
