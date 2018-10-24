call mvn clean install
timeout 2 /nobreak
call docker stop rest.proteus
timeout 2 /nobreak
call docker rm rest.proteus
timeout 2 /nobreak
call docker rmi rest.proteus
timeout 2 /nobreak
call docker build --rm --tag rest.proteus .
timeout 2 /nobreak
call docker run -it -d -p 8098:8090 --name rest.proteus rest.proteus
timeout 30 /nobreak
curl http://localhost:8098/v1/rest/proteus