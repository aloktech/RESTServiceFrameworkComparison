docker stop rest.javalin
sleep 2
docker rm rest.javalin
sleep 2
docker rmi rest.javalin
sleep 2
docker build --rm --tag rest.javalin .
sleep 2
docker run -it -d -p 8096:7000 --name rest.javalin --network rest-net rest.javalin
sleep 30
curl http://localhost:8096/rest/javalin