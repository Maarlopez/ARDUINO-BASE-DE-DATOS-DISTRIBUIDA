FROM maven:3.8.1-openjdk-11 as build
WORKDIR /

COPY . .
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:11
WORKDIR /app
COPY --from=build target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]