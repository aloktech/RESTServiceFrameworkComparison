export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$JAVA_HOME/bin:$PATH
#mvn clean install
docker stop ui
docker rm ui
docker rmi ui
docker build --rm --tag ui . 
docker run -it -d -p 8090:8080 --name ui --network rest-net ui
sleep 10
curl -i http://localhost:8090/UIChart