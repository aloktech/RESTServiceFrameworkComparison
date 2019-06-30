# Microservices/REST Service Frameworks Comparison
For microservices, we need Microservice Framework/REST services. But how to know, what to select.
So, we may have to compare Microservice Framework results and see which one performs better.

The Framework that are used for server side REST services are Jersey and RestEasy. 
And the standalone REST Framework services are Spring Boot, Spark Java, Javalin, Proteus, Jooby and Helidon. 
For the client side REST services, the Frameworks that I have used are Jersey Client, OkHTTP, Unirest, Spring and HttpClientJDK 11.

**Service Provider**
- ClientServiceProvider

**REST Client**
- OKHttpClient
- UniRESTClient
- JerseyRESTClient
- SpringClient

**REST Server**
- SparkRESTServer
- JavalinRESTServer
- SpringBootRESTServer
- HelidonRESTServer
- RestEasyInWildflyServer
- JerseyInMicroPayaraServer
- JerseyInTomcatServer
- JoobyRESTServer
- VertxRESTServer
- MicronautServer
- QuarkusServer
- NodeJSServer*

**REST Controller for UI**
- RESTServiceForUI

**UI**
- UIChart


Any number REST Client service can be integrated by implementing the Service Provider Interface(SPI).

SPI(Service Provider Interface) for REST Client :
---------------------
```java
public interface ClientServiceProvider {    
    void config();
    void close();
    String execute(String url) throws Exception;
    String getClientServiceName();
}
```

The file `META-INF/services/com.imos.ClientServiceProvider` must be added and edited 
as follows :

<full class name, which has implement the interface> i.e com.imos.JerseyClient(JerseyClient implement the interface ClientServiceProvider)
- The above configuration can be automated by using Google Auto Value

REST Signature for REST Server adding/removing
-------
Any number of REST Server can be added/removed
The REST end point are follow :

**POST** **:** RESTServiceForUI/rest/regis

**DELETE** **:** RESTServiceForUI/rest/deregis

The signature for POST and DELETE :
```json
{
    "services": [{
            "base_url": "http://192.168.1.8:8080/JerseyInPayara/hello/jersey",
            "rest_service": "Jersey",
            "server": "Payara 5"
        }
    ]
}
```

Data for UI
-----------
To get all the data for all the configured REST Client and REST Server, the REST end point are :

**RESTServiceForUI/rest/all**          : for 1000 as the default number of iteration

**RESTServiceForUI/rest/{iteration}**  : for any number of iteration


UI Bar Chart
-------------
UI bar chart are dynamic. It can adapt to any number of REST Server result.


Deployment
-----------
- JerseyInPayara has to deployed in Payara
- ResteasyInWildfly has to deployed in Wildfly
- JerseyInTomcatServer has to deployed in Tomcat
- RESTServiceForUI has to deployed in Payara Micro
- UIChart has to deployed in Tomcat
- Other Standalone Frameworks has to be run as usual java program

The complete build and deployment are automated by using docker and script files.

With reference to DZone article **Choosing a REST Framework for Microservices**(https://dzone.com/articles/choosing-rest-framework-for-microservices)
