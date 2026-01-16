## NOTE: build with the repository root as the build context
## Example: `docker build -f writer/Dockerfile -t helm-test/writer:0.1.0 .`
## Build Stage: build and install `common`, then build `writer`
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY ./pom.xml ./

# Build and install the common module into the container-local Maven repo
COPY ./common/pom.xml ./common/pom.xml
COPY ./common/src ./common/src
RUN mvn -B -DskipTests -f common/pom.xml install

# Build the writer module (will use the installed common artifact)
COPY ./writer/pom.xml ./writer/pom.xml
COPY ./writer/src ./writer/src
RUN mvn -B -DskipTests -f writer/pom.xml package

## Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/writer/target/writer-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]