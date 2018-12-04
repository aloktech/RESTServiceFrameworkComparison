call mvn clean install
timeout 2 /nobreak
call docker stop ui
timeout 2 /nobreak
call docker rm ui
timeout 2 /nobreak
call docker rmi ui
timeout 2 /nobreak
call docker build --rm --tag ui .
timeout 2 /nobreak
docker network rm rest-net
docker network create --driver bridge rest-net
call docker run --rm -it -d -p 8090:8080 --name ui --network rest-net ui
timeout 10 /nobreak
curl http://localhost:8090/UIChart