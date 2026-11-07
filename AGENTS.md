# Repository Guidelines

## Project Structure & Module Organization

GURPS Lite is an incomplete implementation of game rules in Java. The root `pom.xml` aggregates two Maven modules:

- `model/`: domain objects for dice, action rolls, damage, and character attributes under `src/main/java/me/mmnoda/gurps/lite/domain/model/`. JSON converters live under `infrastructure/converter/json/`; tests mirror packages in `src/test/java/`.
- `web/`: Spring Boot application, launched through `me.mmnoda.gurps.lite.Application`, depending on the model. Frontend libraries are declared as WebJar dependencies in `web/pom.xml`.

Keep game-rule behavior in `model/`. The root `src/` and legacy `gurps-core-model/` directories are outside the current Maven reactor; add code to the active modules.

## Build, Test, and Development Commands

Use a Java 8 JDK and Maven. Run these commands from the repository root, with the required RTK shell prefix:

- `rtk mvn test`: run unit tests across both modules.
- `rtk mvn verify`: build both modules and run unit and Failsafe integration tests.
- `rtk mvn package`: create module JARs under their respective `target/` directories, including the executable web JAR.
- `rtk mvn -pl model -am test`: run the model module's unit tests and required reactor projects.
- `rtk proxy java -jar web/target/gurps-lite-web-1.0-SNAPSHOT.jar`: launch the packaged application locally.

Maven's `process-sources` phase updates Apache license headers; inspect resulting diffs before committing.

## Coding Style & Naming Conventions

Write all code comments, including Javadoc, in English.

Use four-space indentation, Java 8 syntax, `UpperCamelCase` class names, and `lowerCamelCase` methods and fields. Preserve existing package names, including underscore-separated names such as `dice_representation`. Follow nearby Lombok and static factory patterns (`of(...)`). No formatter or lint plugin is configured; match surrounding code and retain license headers.

## Testing Guidelines

Existing tests use JUnit 4 and AssertJ; Mockito and TestNG are also declared dependencies. Name unit tests `*Test.java` and integration tests `*IT.java`. Follow descriptive methods such as `should_format_D6`. Add deterministic tests for changed rules, boundary values, and formatting. No coverage threshold is configured. Run `verify` before submitting changes affecting integration behavior.

## Commit & Pull Request Guidelines

Write all commit messages and pull request or review comments in English.

History uses short, descriptive subjects such as `Document GURPS Lite project`; no strict commit prefix convention is established. Keep commits focused. Pull requests should explain the behavior changed, identify affected modules, link relevant issues, and report validation commands and results. Include screenshots for visible web changes.

