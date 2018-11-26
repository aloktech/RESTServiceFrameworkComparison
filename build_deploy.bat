call mvn clean
cd UIChart
call build_deploy_test.bat
cd ..
cd RESTServiceForUI
call build_deploy_test.bat
cd ..
cd SpringBootRESTServer
call build_deploy_test.bat
cd ..
cd SparkRESTServer
call build_deploy_test.bat
cd ..
cd RestEasyInWildflyServer
call build_deploy_test.bat
cd ..
cd ProteusRESTServer
call build_deploy_test.bat
cd ..
cd JerseyInTomcatServer
call build_deploy_test.bat
cd ..
cd JerseyInPayaraServer
call build_deploy_test.bat
cd ..
cd JavalinRESTServer
call build_deploy_test.bat
cd ..
cd HelidonRESTServer
call build_deploy_test.bat
cd ..
cd Deployment
call build_deploy_test.bat
cd..
curl -i  -H 'Accepted : application/json' http://localhost:8091/RESTServiceForUI/rest/
curl -i  -H 'Accepted : application/json' http://localhost:8092/JerseyInPayaraServer/rest/jersey
curl -i  -H 'Accepted : application/json' http://localhost:8093/JerseyInTomcatServer/rest/jersey
curl -i  -H 'Accepted : application/json' http://localhost:8094/RestEasyInWildlfyServer/rest/resteasy
curl -i  -H 'Accepted : application/json' http://localhost:8095/rest/spark
curl -i  -H 'Accepted : application/json' http://localhost:8096/rest/javalin
curl -i  -H 'Accepted : application/json' http://localhost:8097/rest/springboot
curl -i  -H 'Accepted : application/json' http://localhost:8098/v1/rest/proteus
curl -i  -H 'Accepted : application/json' http://localhost:8099/rest/helidon
start chrome http://localhost:8090/UIChart/