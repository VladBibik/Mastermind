FROM eclipse-temurin:24-jre

WORKDIR /app

COPY target/mastermind.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]