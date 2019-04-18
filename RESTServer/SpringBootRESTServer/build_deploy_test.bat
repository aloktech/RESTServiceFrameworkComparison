set JAVA_HOME=E:\Tools\Java\jdk-11
set MAVEN_HOME=E:\Tools\apache-maven-3.6.0
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
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
call docker network rm rest-net
call docker network create --driver bridge rest-net
call docker run -it -d -p 8097:8083 --name rest.springboot --network rest-net rest.springboot
timeout 30 /nobreak
curl http://localhost:8097/rest/springboot