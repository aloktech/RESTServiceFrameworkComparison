call mvn clean
call mvn clean install
docker network rm rest-net
docker network create --driver bridge rest-net
cd UIChart
call build_deploy_test.bat
cd ..
cd RESTServiceForUI
call build_deploy_test.bat
cd ..
cd RestEasyInWildflyServer
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
cd SpringBootRESTServer
call build_deploy_test.bat
cd ..
cd SparkRESTServer
call build_deploy_test.bat
cd ..
cd ProteusRESTServer
call build_deploy_test.bat
cd ..
call mvn clean