clear
source /etc/environment
mvn clean
sleep 10
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$JAVA_HOME/bin:$PATH
java -version
javac -version
mvn --version
cd ClientServiceProvider/
./build_deploy_test.sh
sleep 10
cd ..
cd JerseyRESTClient/
./build_deploy_test.sh
sleep 10
cd ..
cd OKHttpRESTClient/
./build_deploy_test.sh
sleep 10
cd ..
cd SpringRESTClient/
./build_deploy_test.sh
sleep 10
cd ..
cd UnirestRESTClient/
./build_deploy_test.sh
sleep 10
cd ..
