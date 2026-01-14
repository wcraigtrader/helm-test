# Java 21 (Runtime)

This repository targets Java 21 (LTS). Use a local JDK 21 or the provided Docker build images for builds and tests.

Build and run tests locally:

```bash
mvn -T 1C -B clean test
```

Build jars (skip tests):

```bash
mvn -T 1C -B -DskipTests package
```

Build Docker images locally (uses `JAVA_VERSION` build-arg):

```bash
docker build --build-arg JAVA_VERSION=21 -t reader:local ./reader
docker build --build-arg JAVA_VERSION=21 -t writer:local ./writer
```

A GitHub Actions workflow `.github/workflows/ci.yml` runs builds/tests on JDK 21 and can build/push images to GHCR.
