mvn clean install
sleep  2
docker stop rest.spark
sleep 2
docker rm rest.spark
sleep 2
docker rmi rest.spark
sleep 2
docker build --rm --tag rest.spark .
sleep 2
docker run -it -d -p 8095:4567 --name rest.spark --network rest-net rest.spark
sleep 30
curl http://localhost:8095/rest/spark