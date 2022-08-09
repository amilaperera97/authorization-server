FROM adoptopenjdk/openjdk11:alpine-jre

ADD /target/*.jar authorizaton-service.jar
EXPOSE 9191
ENTRYPOINT ["java","-jar","authorizaton-service.jar"]