# Scanner Practice - Java File I/O

Practice using `java.util.Scanner` for file input, parsing, and text processing.

## Getting Started with GitHub Codespaces

1. Click the green **"Code"** button at the top of this repository
2. Select the **"Codespaces"** tab
3. Click **"Create codespace on main"**
4. Wait for the environment to load (this may take a few minutes the first time)
5. Once loaded, you can edit code and run tests directly in the browser

> **Note:** If the Java extension shows errors on first load, press `Cmd+Shift+P` (Mac) or `Ctrl+Shift+P` (Windows) and run **"Developer: Reload Window"**. This is a one-time setup step.

## Alternative: Local Development

1. Accept the assignment via the GitHub Classroom link in Google Classroom
2. Clone the repository using GitHub Desktop
3. Open in VS Code or IntelliJ IDEA
4. Make sure you have Java 17+ and Maven installed

## Project Structure

- `src/main/java/scanner/ScannerPractice.java` — Implement your methods here
- `src/test/java/scanner/ScannerPracticeTest.java` — Tests that verify your work
- `pom.xml` — Maven project configuration

## Running Tests

### In VS Code / Codespaces
- Click the **Testing** icon (flask) in the left sidebar
- Click the **Run All Tests** button
- Or right-click individual tests to run them one at a time

### From the Command Line
```bash
mvn test
```

To run a single test:
```bash
mvn -Dtest=ScannerPracticeTest#testScannerCreate test
```

## Scoring

| Method | Points | Description |
|--------|--------|-------------|
| `createScanner` | 10 | Create a Scanner for file reading |
| `readLine` | 10 | Read and print first line of a file |
| `readFile` | 10 | Read all lines into a String array |
| `sumInt` | 15 | Sum all integer tokens in a file |
| `calculateBill` | 15 | Sum prices and apply tax |
| `mostExpensiveItem` | 10 | Find the most expensive item by name |
| `copyFileToOutput` | 10 | Print entire file to standard output |
| `searchFor` (basic) | 10 | Case-insensitive line search |
| `searchFor` (Raven) | 10 | Search a poem for matching lines |
| **Total** | **100** | |

## Method Details

### `createScanner(String fileName)` — 10 pts
Create and return a `Scanner` for the given filename. The file is in the current working directory.

### `readLine(String fileName)` — 10 pts
Print the **first line** of the file. If no line exists, print `"No next line"`. Close the scanner when done.

### `readFile(String fileName, int numLines)` — 10 pts
Read all lines and return them in a `String[]` of length `numLines`. Close the scanner when done.

### `sumInt(String fileName)` — 15 pts
Sum all integer tokens using `hasNextInt()` / `nextInt()`. Close the scanner when done.

### `calculateBill(String fileName, double taxProportion)` — 15 pts
Each line is like `"Roasted Duck 36.99"`. Sum the prices (last token per line) and return total × (1 + taxProportion).

### `mostExpensiveItem(String fileName)` — 10 pts
Same format as above. Return the **name** (everything before the last space) of the most expensive item.

### `copyFileToOutput(String filename)` — 10 pts
Print every line of the file to standard output.

### `searchFor(String filename, String query)` — 10+10 pts
Print every line containing the query (case-insensitive). If no matches, print `"No results."`.

## Common Mistakes

- **Forgetting to close the Scanner** — always call `scanner.close()` when done
- **Wrong file path** — the file is in the **current working directory**, not inside `src/`
- **Mixing `nextLine()` with `nextInt()`** — consumes the newline character differently
- **Not checking `hasNextLine()` / `hasNextInt()`** before reading
- **Case sensitivity** — `searchFor` should be case-**insensitive** (use `.toLowerCase()`)
- **Splitting lines incorrectly** — for bill methods, the price is the **last token** on each line

## How Autograding Works

When you push your code to GitHub, the autograding workflow runs each test individually and assigns points based on the scoring table above. Check the **Actions** tab to see your score.

## Need Help?

- Check the Javadoc comments in `ScannerPractice.java` for hints
- Review the test file to understand expected inputs and outputs
- Contact Mr. Hare at kevin@csplusplus.com
