cls
call mvn clean install
timeout 2 /nobreak
call docker stop rest.resteasy
timeout 2 /nobreak
call docker rm rest.resteasy
timeout 2 /nobreak
call docker rmi rest.resteasy
timeout 2 /nobreak
call docker build --rm --tag rest.resteasy .
timeout 2 /nobreak
call docker run --rm -it -d -p 8094:8080 --name rest.resteasy rest.resteasy
timeout 30 /nobreak
curl http://localhost:8094/RestEasyInWildlfyServer/rest/resteasy