# DevOpsCalculatorJUnit

A small Java calculator project for learning JUnit 5 unit testing with Maven.

## Requirements
- JDK 21
- Maven
- VS Code (optional)

## Project structure
```text
DevOpsCalculatorJUnit/
├── pom.xml
└── src/
    ├── main/java/com/example/
    │   ├── Calculator.java
    │   └── CalculatorApp.java
    └── test/java/com/example/
        └── CalculatorTest.java
```

## Run the application
Open CMD or the VS Code Integrated Terminal in this folder and run:
```cmd
mvn compile
java -cp target\classes com.example.CalculatorApp
```

Expected output:
```text
Addition: 15
Subtraction: 5
Multiplication: 50
Division: 2
```

## Run the JUnit tests
```cmd
mvn test
```

The test suite contains five tests: addition, subtraction, multiplication, division, and division by zero. Look for `BUILD SUCCESS`. Detailed reports are written to `target\surefire-reports\`.

## Learning exercise
In `CalculatorTest.java`, temporarily change the expected value in `addsTwoNumbers()` from `15` to `16`. Run `mvn test` and observe the failure. Change it back to `15` and run the tests again.

JUnit tests the `Calculator` class directly. The application source does not need to change to add tests.
