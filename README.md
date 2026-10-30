# GURPS Lite

GURPS Lite is an object-oriented Java project that aims to implement the rules of GURPS Lite. It provides reusable domain objects for applications built around those rules.

The implementation is a work in progress. The model currently includes parts of character attributes, dice rolling, action rolls, critical results, and damage. It does not yet cover the complete GURPS Lite ruleset.

## Project structure

- `model/` — game domain objects and their tests.
- `web/` — a Spring Boot application that depends on the model.
- `pom.xml` — the Maven parent project for both modules.

The domain code lives under `model/src/main/java/me/mmnoda/gurps/lite/domain/model/`, with tests under `model/src/test/java/`.

## Build and test

The Maven project targets Java 8. From the repository root, run:

```sh
mvn test
mvn package
```

`mvn test` runs the tests. `mvn package` builds the module JARs under their respective `target/` directories.

## Unofficial project

This is an independent, unofficial project. It is not affiliated with, sponsored by, or endorsed by the publisher of GURPS. Refer to the official GURPS Lite materials for the rules themselves.
