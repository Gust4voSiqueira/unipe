FROM ubuntu:24.04 AS build

RUN apt-get update \
    && apt-get install -y openjdk-17-jdk wget unzip \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN chmod +x gradlew
RUN ./gradlew clean build

FROM openjdk:17-jdk-slim
EXPOSE 8080

COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
