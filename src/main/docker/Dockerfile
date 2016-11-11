FROM openjdk:8
MAINTAINER Iddy Magohe
ENV spring.application.name pango-rentals-service
ENV spring.cloud.config.uri http://localhost:8888
EXPOSE 8090
ADD pango-service-apis.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]