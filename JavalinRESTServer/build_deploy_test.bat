set JAVA_HOME=E:\Tools\Java\jdk-11
set MAVEN_HOME=E:\Tools\apache-maven-3.6.0
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
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
call docker network rm rest-net
call docker network create --driver bridge rest-net
call docker run -it -d -p 8096:7000 --name rest.javalin --network rest-net rest.javalin
timeout 30 /nobreak
curl http://localhost:8096/rest/javalin