call mvn clean install
timeout 2 /nobreak
call docker stop rest.jersey.payara
timeout 2 /nobreak
call docker rm rest.jersey.payara
timeout 2 /nobreak
call docker rmi rest.jersey.payara
timeout 2 /nobreak
call docker build --rm --tag rest.jersey.payara .
timeout 2 /nobreak
call docker run --rm -it -d -p 8092:8080 --name rest.jersey.payara --network rest-net rest.jersey.payara
timeout 40 /nobreak
curl http://localhost:8092/JerseyInPayaraServer/rest/jersey