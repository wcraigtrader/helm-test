## NOTE: build with the repository root as the build context
## Example: `docker build -f reader/Dockerfile -t helm-test/reader:0.1.0 .`
## Build Stage: build and install `common`, then build `reader`
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /workspace
# Copy root pom so multi-module builds work
COPY pom.xml ./

# Build and install the common module into the container-local Maven repo
COPY common/pom.xml ./common/pom.xml
COPY common/src ./common/src
RUN mvn -B -DskipTests -f common/pom.xml install

# Build the reader module (will use the installed common artifact)
COPY reader/pom.xml ./reader/pom.xml
COPY reader/src ./reader/src
RUN mvn -B -DskipTests -f reader/pom.xml package

## Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/reader/target/reader-0.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
