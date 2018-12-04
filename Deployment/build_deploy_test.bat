set JAVA_HOME=E:\Tools\Java\jdk-11
set MAVEN_HOME=E:\Tools\apache-maven-3.6.0
set PATH=%JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%

echo %JAVA_HOME%
echo %MAVEN_HOME%
echo %PATH%

java -version
javac -version
call mvn clean install
timeout 2 /nobreak
call docker stop rest.deploy
timeout 2 /nobreak
call docker rm rest.deploy
timeout 2 /nobreak
call docker rmi rest.deploy
timeout 2 /nobreak
call docker build --rm --tag rest.deploy .
timeout 2 /nobreak
call docker run -it -d --name rest.deploy --network rest-net rest.deploy