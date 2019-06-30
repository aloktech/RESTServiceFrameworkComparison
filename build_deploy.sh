clear
source /etc/environment
mvn clean
sleep 10
export JAVA_HOME=/usr/lib/jvm/corretto-8.202
export PATH=$JAVA_HOME/bin:$PATH
java -version
javac -version
mvn --version
mvn clean install
docker network rm rest-net
docker network create --driver bridge rest-net
cd RESTCore
echo RESTCore
./build_deploy_test.sh
sleep 10
cd ..
cd RESTClient/ClientServiceProvider/
echo ClientServiceProvider
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/JerseyRESTClient/
echo JerseyRESTClient
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/OKHttpRESTClient/
echo OKHttpRESTClient
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/SpringRESTClient/
echo SpringRESTClient
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/UnirestRESTClient/
echo UnirestRESTClient
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/JDK12HttpClient
echo JDK12HttpClient
./build_deploy_test.sh
sleep 10
cd ../..
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$JAVA_HOME/bin:$PATH
java -version
javac -version
mvn --version
cd RESTServer/SpringBootRESTServer/
echo SpringBootRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/SparkRESTServer/
echo SparkRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JavalinRESTServer/
echo JavalinRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/HelidonRESTServer/
echo HelidonRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JoobyRESTServer/
echo JoobyRESTServer
./build_deploy_test.sh
sleep 10
cd ../..

cd RESTServer/RestEasyInWildflyServer/
echo RestEasyInWildflyServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JerseyInTomcatServer/
echo JerseyInTomcatServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JerseyInPayaraServer/
echo JerseyInPayaraServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/NodeJSRESTServer/
echo NodeJSRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/VertxRESTServer/
echo VertxRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/QuarkusRESTServer/
echo QuarkusRESTServer
./build_deploy_test.sh
cd ../..
cd RESTServer/MicronautRESTServer/
echo MicronautRESTServer
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/NodeJSRESTServer/
echo NodeJSRESTServer
./build_deploy_test.sh
sleep 10
cd ../..


cd RESTUI/RESTServiceForUI/
echo RESTServiceForUI
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTUI/UIChart/
echo UIChart
./build_deploy_test.sh
sleep 10
cd ../..
curl -i http://192.168.1.8:8091/RESTServiceForUI/rest/
sleep 10
curl -d '{"services": [ {"base_url": "http://192.168.1.8:8092/JerseyInPayaraServer/rest/jersey","rest_service": "Jersey","server": "Micro Payara"}, {"base_url": "http://192.168.1.8:8093/JerseyInTomcatServer/rest/jersey","rest_service": "Jersey","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8094/RestEasyInWildflyServer/rest/resteasy","rest_service": "RestEasy","server": "Wildfly"}, {"base_url": "http://192.168.1.8:8095/rest/spark","rest_service": "Spark Java","server": "Jetty"}, {"base_url": "http://192.168.1.8:8096/rest/javalin","rest_service": "Javalin","server": "Jetty"}, {"base_url": "http://192.168.1.8:8097/rest/springboot","rest_service": "Spring Boot","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8099/rest/helidon","rest_service": "Helidon","server": "Netty"}, {"base_url": "http://192.168.1.8:8087/rest/jooby","rest_service": "Jooby","server": "Netty"}, {"base_url": "http://192.168.1.8:8086/rest/vertx","rest_service": "Vertx","server": "Netty"}, {"base_url": "http://192.168.1.8:8088/rest/quarkus","rest_service": "Quarkus","server": "Undertow"}, {"base_url": "http://192.168.1.8:8085","rest_service": "Node JS","server": "Node JS"}]}' -H "Content-Type: application/json" -X POST http://192.168.1.8:8091/rest/regis
