FROM eclipse-temurin:24-jre

WORKDIR /app

COPY target/mastermind.jar mastermind.jar

ENTRYPOINT ["java", "-jar", "mastermind.jar"]