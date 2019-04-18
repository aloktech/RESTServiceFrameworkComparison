docker stop rest.jersey.tomcat
docker rm rest.jersey.tomcat
docker rmi rest.jersey.tomcat
docker build --rm --tag rest.jersey.tomcat . 
docker run -it -d -p 8093:8080 --name rest.jersey.tomcat --network rest-net rest.jersey.tomcat
sleep 30
curl http://192.168.1.8:8093/JerseyInTomcatServer/rest/jersey

