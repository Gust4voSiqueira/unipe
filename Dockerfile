FROM ubuntu:24.04 AS build

ENV POSTGRES_DATABASE=$POSTGRES_DATABASE
ENV POSTGRES_USER=$POSTGRES_USER
ENV POSTGRES_PASSWORD=$POSTGRES_PASSWORD

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
