docker stop rest.jersey.payara
docker rm rest.jersey.payara
docker rmi rest.jersey.payara
docker build --rm --tag rest.jersey.payara . 
docker run -it -d -p 8092:8080 -p 4848:4848 --name rest.jersey.payara --network rest-net rest.jersey.payara
sleep 30
curl http://192.168.1.8:8092/JerseyInPayaraServer/rest/jersey
