FROM openjdk:8
MAINTAINER Iddy Magohe
ENV spring.application.name pango-rentals-service
ENV spring.cloud.config.uri http://localhost:8888
ENV AWS_ACCESS_KEY_ID some_key
ENV AWS_SECRET_ACCESS_KEY some_Id
EXPOSE 8090
ADD pango-service-apis.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]