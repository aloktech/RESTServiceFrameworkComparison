#FROM payara/server-full:latest
FROM tomcat:latest

COPY target/RESTServiceForUI.war ${CATALINA_HOME}/webapps

CMD ["catalina.sh", "run"]
