FROM maven:3.6.0-jdk-8-slim as build

WORKDIR /app

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package

FROM alpine:3.13
RUN apk add --update --no-cache openjdk8-jre-base \
    && rm -f /var/cache/apk/*    
    
WORKDIR /app
COPY --from=build /app/target/fsmusic-0.0.1-SNAPSHOT.jar .

EXPOSE 80

CMD [ "java","-jar","/app/fsmusic-0.0.1-SNAPSHOT.jar"]