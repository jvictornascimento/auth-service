FROM maven:3.9-amazoncorretto-17-alpine as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn package

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/auth-service-0.0.1-SNAPSHOT.jar /app/app.jar
# Define o diretório de trabalho no contêiner
WORKDIR /app

# Expõe a porta padrão
EXPOSE 9001

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]