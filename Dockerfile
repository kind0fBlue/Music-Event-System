# build stage
FROM maven:3.9-amazoncorretto-17 AS build

WORKDIR /app

COPY . .
RUN mvn clean install

# run stage
FROM tomcat:10.0.27-jre17

COPY --from=build /app/target/SDA1_MusicSystem-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/musicsystem1013.war

