set JAVA_HOME=E:\Tools\Java\jdk-11
set MAVEN_HOME=E:\Tools\apache-maven-3.6.0
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
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
call docker network rm rest-net
call docker network create --driver bridge rest-net
call docker run --rm -it -d -p 8099:8084 --name rest.helidon --network rest-net rest.helidon
timeout 30 /nobreak
curl http://localhost:8099/rest/helidon