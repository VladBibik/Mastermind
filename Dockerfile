FROM eclipse-temurin:24-jre

WORKDIR /app

COPY target/mastermind.jar mastermind.jar

ENV MASTERMIND_RUNTIME=true

ENTRYPOINT ["java", "-jar", "mastermind.jar"]