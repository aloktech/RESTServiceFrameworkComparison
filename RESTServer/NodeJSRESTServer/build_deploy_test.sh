docker stop rest.nodejs
docker rm rest.nodejs
docker rmi rest.nodejs
docker build --rm -t rest.nodejs .
docker run -it -d -p 3000:3000 --name rest.nodejs rest.nodejs
sleep 10
curl -i http://localhost:3000/
