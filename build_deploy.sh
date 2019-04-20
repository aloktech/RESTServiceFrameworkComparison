clear
source /etc/environment
mvn clean
sleep 10
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$JAVA_HOME/bin:$PATH
java -version
javac -version
mvn --version
mvn clean install
docker network rm rest-net
docker network create --driver bridge rest-net
cd RESTCore
./build_deploy_test.sh
sleep 10
cd ..
cd RESTClient/ClientServiceProvider/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/JerseyRESTClient/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/OKHttpRESTClient/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/SpringRESTClient/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTClient/UnirestRESTClient/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/SpringBootRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/SparkRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JavalinRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/HelidonRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JoobyRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/ProteusRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/RestEasyInWildflyServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JerseyInTomcatServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/JerseyInPayaraServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/NodeJSRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTServer/VertxRESTServer/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTUI/RESTServiceForUI/
./build_deploy_test.sh
sleep 10
cd ../..
cd RESTUI/UIChart/
./build_deploy_test.sh
sleep 10
cd ../..
curl -i http://192.168.1.8:8091/RESTServiceForUI/rest/
curl -d '{"services": [{"base_url": "http://192.168.1.8:8092/JerseyInPayaraServer/rest/jersey","rest_service": "Jersey","server": "Payara 5"}, {"base_url": "http://192.168.1.8:8093/JerseyInTomcatServer/rest/jersey","rest_service": "Jersey","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8094/RestEasyInWildflyServer/rest/resteasy","rest_service": "RestEasy","server": "Wildlfy 14"}, {"base_url": "http://192.168.1.8:8095/rest/spark","rest_service": "Spark Java","server": "Jetty"}, {"base_url": "http://192.168.1.8:8096/rest/javalin","rest_service": "Javalin","server": "Jetty"}, {"base_url": "http://192.168.1.8:8097/rest/springboot","rest_service": "Spring Boot 2.0","server": "Tomcat"}, {"base_url": "http://192.168.1.8:8099/rest/helidon","rest_service": "Helidon","server": "Netty"}, {"base_url": "http://192.168.1.8:8087/rest/jooby","rest_service": "Jooby","server": "Netty"},{"base_url": "http://192.168.1.8:3000/rest/nodejs","rest_service": "NodeJS","server": "NodeJS"}, {"base_url": "http://192.168.1.8:8086/rest/vertx","rest_service": "Vertx","server": "Netty"}]}' -H "Content-Type: application/json" -X POST http://192.168.1.8:8091/rest/regis
