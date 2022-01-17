FROM openjdk:8-alpine

COPY target/uberjar/rocket-link.jar /rocket-link/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/rocket-link/app.jar"]
