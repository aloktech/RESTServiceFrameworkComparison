cls
call mvn clean install
timeout 2 /nobreak
call docker stop rest.javalin
timeout 2 /nobreak
call docker rm rest.javalin
timeout 2 /nobreak
call docker rmi rest.javalin
timeout 2 /nobreak
call docker build --rm --tag rest.javalin .
timeout 2 /nobreak
call docker run -it -d -p 8096:7000 --name rest.javalin rest.javalin
timeout 30 /nobreak
curl http://localhost:8096/rest/javalin