call mvn clean install
timeout 2 /nobreak
call docker stop ui-rest
timeout 2 /nobreak
call docker rm ui-rest
timeout 2 /nobreak
call docker rmi ui-rest
timeout 2 /nobreak
call docker build --rm --tag ui-rest .
timeout 2 /nobreak
call docker run --rm -it -d -p 8091:8080 --name ui-rest ui-rest
timeout 40 /nobreak
curl http://localhost:8091/RESTServiceForUI/rest
timeout 2 /nobreak
curl -H "Origin: http://example.com" --verbose http://localhost:8091/RESTServiceForUI/rest