mvn clean
docker network rm rest-net
docker network create --driver bridge rest-net
cd UIChart
build_deploy_test.sh
cd ..
cd RESTServiceForUI
build_deploy_test.sh
cd ..
cd SpringBootRESTServer
build_deploy_test.sh
cd ..
cd SparkRESTServer
build_deploy_test.sh
cd ..
cd RestEasyInWildflyServer
build_deploy_test.sh
cd ..
cd JerseyInTomcatServer
build_deploy_test.sh
cd ..
cd JerseyInPayaraServer
build_deploy_test.sh
cd ..
cd JavalinRESTServer
build_deploy_test.sh
cd ..
cd HelidonRESTServer
build_deploy_test.sh
cd ..
cd ProteusRESTServer
build_deploy_test.sh
cd ..
cd JoobyRESTServer
build_deploy_test.sh
cd ..
