[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/_PPYcOQN)

# Scanner Practice Project

This project is for Mr. Hare’s APCS-A and focuses on using `java.util.Scanner` for file I/O, parsing, and simple aggregation. You will implement methods in `ScannerPractice.java` and verify them with unit tests.

## Project Structure

- `src/main/java/scanner/ScannerPractice.java` — method headers and task descriptions. Implement the bodies.
- `src/test/java/scanner/ScannerPracticeTest.java` — JUnit tests that validate your implementations.
- `pom.xml` — Maven configuration for building and running tests.
- Test data files may be referenced by tests; if provided, they will be placed in `src/test/resources/`.

## Prerequisites

- Java 17 (or compatible)
- Maven 3.x
- An IDE (IntelliJ/Eclipse) or GitHub Desktop for cloning

## Getting Started

1. Accept the assignment using the link in Google Classroom.
2. Clone the repository locally (GitHub Desktop recommended).
3. Open the project in your IDE.

### Running Tests

- From the IDE: run `ScannerPracticeTest`.
- From the terminal:
  ```bash
  mvn -q test
  ```

## Methods to Implement (in `ScannerPractice.java`)

1. `Scanner createScanner(String fileName)`  
   Create and return a `Scanner` for a file in the current working directory. May throw `FileNotFoundException`.

2. `void readLine(String fileName)`  
   Print the next line of the file to standard output. If there is no next line, print `No next line`. Close the scanner you create.

3. `String[] readFile(String fileName, int numLines)`  
   Read all lines and return them in an array of length `numLines`. Close the scanner you create.

4. `int sumInt(String fileName)`  
   Sum all integer tokens in the file using `hasNextInt()` and return the total. Close the scanner you create.

5. `double calculateBill(String fileName, double taxProportion)`  
   Each line is like `Roasted Duck 36.99`. Sum the prices and return the total with tax applied. Aim to be within a few cents of the correct answer.

6. `String mostExpensiveItem(String fileName)`  
   Using the same format as above, return the name (text before the last space) of the most expensive item. Return `""` if none found.

7. `void copyFileToOutput(String filename)`  
   Print every line in the file to standard output.

8. `void searchFor(String filename, String query)`  
   Case-insensitive search. Print each line that contains `query`. If no lines match, print `No results.`

9. `double stdDev(double[] arr)`  
   Optional helper for later tests. Return the sample standard deviation.

## Notes and Tips

- Always close any `Scanner` you create to avoid resource leaks.
- Paths are relative to the process working directory. For Maven tests, place test files in `src/test/resources/` and load them via the classpath if needed.
- Normalize output carefully when comparing strings in tests. Be mindful of spaces and line endings.
- Handle parsing errors gracefully where applicable; tests will define expected behavior.

## Common Mistakes

- Forgetting to close the `Scanner`.
- Using `nextLine()` and `nextInt()` together without consuming leftover newlines.
- Not checking `hasNextLine()` or `hasNextInt()` before reading.
- Incorrectly splitting lines when extracting the final price token.

## Contributing

Open an issue or submit a pull request if you find a bug or have a suggestion.

## Contact

Questions? Email Mr. Hare at kevin@csplusplus.com.
