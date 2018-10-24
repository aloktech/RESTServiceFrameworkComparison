call mvn clean install
timeout 2 /nobreak
call docker stop rest.helidon
timeout 2 /nobreak
call docker rm rest.helidon
timeout 2 /nobreak
call docker rmi rest.helidon
timeout 2 /nobreak
call docker build --rm --tag rest.helidon .
timeout 2 /nobreak
call docker run --rm -it -d -p 8099:8084 --name rest.helidon rest.helidon
timeout 30 /nobreak
curl http://localhost:8099/rest/helidon