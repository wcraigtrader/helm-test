## Build Stage
FROM maven:3.9.4-eclipse-temurin-21 as build
WORKDIR /workspace
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

## Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/target/reader-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
