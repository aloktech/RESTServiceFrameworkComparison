call mvn clean install
timeout 2 /nobreak
call docker stop rest.springboot
timeout 2 /nobreak
call docker rm rest.springboot
timeout 2 /nobreak
call docker rmi rest.springboot
timeout 2 /nobreak
call docker build --rm --tag rest.springboot .
timeout 2 /nobreak
call docker run -it -d -p 8097:8083 --name rest.springboot --network rest-net rest.springboot
timeout 30 /nobreak
curl http://localhost:8097/rest/springboot