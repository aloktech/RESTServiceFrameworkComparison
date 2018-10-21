# REST Service Frameworks Comparison
      The Framework that are used for server side REST services are Jersey and RestEasy, And the standalone REST Framework services are Spring Boot, Spark Java, Javalin and Helidon. For the client side REST services, the Frameworks that I have used are Jersey Client, OkHTTP and UniRest.
      
      Any number REST Client service can be integrated by implementing the Service Provider Interface.
      SPI for REST Client :
      ```java
      public interface ClientServiceProvider {    
          void config();
          void close();
          String execute(String url) throws Exception;
          String getClientServiceName();
      }
      ```
      The file 'META-INF/services/com.imos.ClientServiceProvider' must be added and edited 
      as follows :
      <full class name, which has implement the interface> i.e com.imos.JerseyClient(JerseyClient implement the interface ClientServiceProvider)
      
      
      Any number of REST Server can be added/removed
      The REST end point are follow :
      
      POST RESTServiceForUI/rest/regis
      DELETE RESTServiceForUI/rest/deregis
            
      Example
      -------
      The signature for POST and DELETE :
      ```json
      {
          "services": [{
                  "url_get": "http://localhost:27643/JerseyInPayara/hello/jersey",
                  "rest_service": "Jersey",
                  "server": "Payara 5"
              }
      }
      ```
      
      To the result of execution of all the configured REST Client to all the REST Server, the REST end point is :
      RESTServiceForUI/rest/all          : for 1000 as the default number of iteration
      RESTServiceForUI/rest/{iteration}  : for any number of iteration
      
      UI bar chart can dynamic in nature, it can adapt to any number of REST Server Service.
     
      - JerseyInPayara is deployed in Payara
      - ResteasyInWildfly is deployed in Wildfly
      - JerseyInTomcatServer is deployed in Tomcat
      - RESTServiceForUI is deployed in Payara
      - UIChart is deployed in Tomcat
