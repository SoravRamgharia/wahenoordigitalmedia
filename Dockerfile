FROM openjdk:21
WORKDIR /app

COPY target/WahenoorDigitalMedia-0.0.1-SNAPSHOT.jar /app/WahenoorDigitalMedia-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "WahenoorDigitalMedia-0.0.1-SNAPSHOT.jar"]