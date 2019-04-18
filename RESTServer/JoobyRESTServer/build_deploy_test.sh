docker stop rest.jooby
sleep 2
docker rm rest.jooby
sleep 2
docker rmi rest.jooby
sleep 2
docker build --rm --tag rest.jooby .
sleep 2
docker run -it -d -p 8087:8080 --name rest.jooby --network rest-net rest.jooby
sleep 30
curl http://localhost:8087/rest/jooby