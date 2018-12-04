call mvn clean install
timeout 2 /nobreak
call docker stop rest.resteasy.wildfly
timeout 2 /nobreak
call docker rm rest.resteasy.wildfly
timeout 2 /nobreak
call docker rmi rest.resteasy.wildfly
timeout 2 /nobreak
call docker build --rm --tag rest.resteasy.wildfly .
timeout 2 /nobreak
call docker run --rm -it -d -p 8094:8080 --name rest.resteasy.wildfly --network rest-net rest.resteasy.wildfly
timeout 30 /nobreak
curl http://localhost:8094/RestEasyInWildlfyServer/rest/resteasy