## Build Stage
ARG JAVA_VERSION=21

FROM maven:3.9.4-eclipse-temurin-${JAVA_VERSION} as build
WORKDIR /workspace
COPY . .
RUN mvn -B -DskipTests -pl writer -am package

## Run Stage

FROM eclipse-temurin:${JAVA_VERSION}-jre
WORKDIR /app
COPY --from=build /workspace/writer/target/writer-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]