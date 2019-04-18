call mvn clean install
timeout 2 /nobreak
call docker stop rest.jersey.tomcat
timeout 2 /nobreak
call docker rm rest.jersey.tomcat
timeout 2 /nobreak
call docker rmi rest.jersey.tomcat
timeout 2 /nobreak
call docker build --rm --tag rest.jersey.tomcat .
timeout 2 /nobreak
call docker run --rm -it -d -p 8093:8080 --name rest.jersey.tomcat --network rest-net rest.jersey.tomcat
timeout 30 /nobreak
curl http://localhost:8093/JerseyInTomcatServer/rest/jersey