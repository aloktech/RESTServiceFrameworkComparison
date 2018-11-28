mvn clean install
docker stop rest.resteasy.wildfly
docker rm rest.resteasy.wildfly
docker rmi rest.resteasy.wildfly
docker build --rm --tag rest.resteasy.wildfly . 
docker run -it -d -p 8094:8080 -p 9990:9990 --name rest.resteasy.wildfly --network rest-net rest.resteasy.wildfly
sleep 30
curl http://192.168.1.8:8094/RestEasyInWildlfyServer/rest/resteasy

