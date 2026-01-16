Upgrade plan: Move project to Java 21 (LTS)

Goals
- Ensure all Maven builds compile and run under Java 21
- Update CI, Docker images and runtime to Java 21
- Run tests and fix any compatibility issues

Steps
1. Verify local JDK
   - Install or confirm JDK 21 available (`java -version`, `javac -version`)

2. Set Maven build targets
   - Add `maven.compiler.release=21` and `java.version=21` properties in POMs
   - Configure `maven-compiler-plugin` with `<release>21</release>`

3. Update module POMs
   - Ensure every module (`common`, `writer`, `reader`, others) sets `maven.compiler.release` or inherits from a parent POM

4. Update Dockerfiles and container images
   - Replace base images with a Java 21 JRE/JDK (e.g., `eclipse-temurin:21-jre` or `azul/zulu-openjdk:21-jre`)

5. Update CI/CD
   - Ensure CI runners use JDK 21; update GitHub Actions/workflows or Jenkins agents

6. Run build & tests
   - `mvn -T 1C clean verify` (adjust concurrency)
   - Address compilation errors, deprecated APIs, module issues

7. Runtime validation
   - Start services (locally or in a test environment) and run smoke tests

8. Rollout
   - Build and push updated images
   - Deploy to staging, monitor, then promote to production

Notes
- Spring Boot 3.x supports Java 17+; confirm all dependencies support Java 21
- Prefer using `maven-compiler-plugin`'s `release` flag instead of `source`/`target` for cross-JDK compatibility
- Keep backups and ensure CI artifacts are reproducible

Commands to try locally
```
java -version
javac -version
mvn -version
mvn clean verify
```
