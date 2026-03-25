# CS++ Java — Unit 7.5: Scanner Practice

> **Unit 7.5** | 100 Points | 9 Autograded Tests

In this assignment you will practice using `java.util.Scanner` for file input. You will read text files, parse data, sum integers, calculate bills, find the most expensive item, copy file contents, and build a simple search engine.

---

## Table of Contents

1. [Concepts You Need](#concepts-you-need)
2. [Project Overview](#project-overview)
3. [Methods to Implement](#methods-to-implement)
4. [File Structure](#file-structure)
5. [Autograding](#autograding)
6. [Try It Yourself — Practice Examples](#try-it-yourself--practice-examples)
7. [Tips for Success](#tips-for-success)
8. [FAQ](#faq)

---

## Concepts You Need

### Creating a Scanner for File Input

```java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

Scanner scanner = new Scanner(new File("data.txt"));
```

This throws `FileNotFoundException` if the file does not exist, so your method must declare `throws FileNotFoundException`.

### Reading Lines

```java
// Read one line at a time
while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
    System.out.println(line);
}
```

### Reading Integers

```java
// Read integers from a file
while (scanner.hasNextInt()) {
    int num = scanner.nextInt();
    System.out.println(num);
}
```

### Reading into an Array

When you know the number of lines:

```java
String[] lines = new String[numLines];
for (int i = 0; i < numLines; i++) {
    if (scanner.hasNextLine()) {
        lines[i] = scanner.nextLine();
    }
}
```

### Parsing Prices from Text

If a file has lines like `"Roasted Duck 36.99"`, the price is the last token:

```java
String line = "Roasted Duck 36.99";
String[] parts = line.split(" ");
double price = Double.parseDouble(parts[parts.length - 1]);
// price is 36.99
```

### Calculating a Bill with Tax

```java
double subtotal = 0;
// ... sum all prices ...
double total = subtotal + (subtotal * taxProportion);
// If tax is 10%, taxProportion = 0.10
```

### Searching for Text in a File

```java
while (scanner.hasNextLine()) {
    String line = scanner.nextLine();
    if (line.contains(query)) {
        System.out.println(line);
    }
}
```

---

## Project Overview

You will implement eight methods in `ScannerPractice.java`. Each method reads from a text file using `Scanner` and processes the data in different ways. The test files are provided in the project.

---

## Methods to Implement

### 1. createScanner(String fileName)
Create and return a `Scanner` object for the given file name.

```java
Scanner s = createScanner("data.txt");
// Returns a Scanner ready to read from data.txt
```

### 2. readLine(String fileName)
Read and print the first line of the file using `System.out.println()`.

```java
readLine("data.txt")
// Prints the first line of data.txt to the console
```

### 3. readFile(String fileName, int numLines)
Read the first `numLines` lines from the file and return them as a `String[]` array.

```java
String[] lines = readFile("data.txt", 3);
// Returns an array with the first 3 lines of data.txt
```

### 4. sumInt(String fileName)
Read all integers from the file and return their sum. Use `hasNextInt()` and `nextInt()`.

```java
// If data.txt contains: 10 20 30
sumInt("data.txt")    // returns 60
```

### 5. calculateBill(String fileName, double taxProportion)
Read a menu file where each line has an item name followed by a price (e.g., `"Roasted Duck 36.99"`). Sum all the prices and add tax.

```java
// menu.txt:
// Roasted Duck 36.99
// Salad 12.50
calculateBill("menu.txt", 0.10)    // returns (36.99 + 12.50) * 1.10
```

### 6. mostExpensiveItem(String fileName)
Read the same format menu file and return the name of the most expensive item (everything before the last space/price).

```java
// menu.txt:
// Roasted Duck 36.99
// Salad 12.50
mostExpensiveItem("menu.txt")    // returns "Roasted Duck"
```

### 7. copyFileToOutput(String filename)
Read every line from the file and print it to the console using `System.out.println()`.

```java
copyFileToOutput("data.txt")
// Prints every line of data.txt
```

### 8. searchFor(String filename, String query)
Read every line from the file and print lines that contain the query string using `System.out.println()`.

```java
// poem.txt contains lines of a poem
searchFor("poem.txt", "Raven")
// Prints only lines containing "Raven"
```

---

## File Structure

```
scannerPractice/
├── pom.xml                                        <-- Maven config (DO NOT MODIFY)
├── src/
│   ├── main/java/scanner/
│   │   └── ScannerPractice.java                   <-- YOUR CODE GOES HERE
│   └── test/java/scanner/
│       └── ScannerPracticeTest.java               <-- Tests (DO NOT MODIFY)
├── bill.txt                                        <-- Menu data file
├── ints.txt                                        <-- Integer data file
├── raven.txt                                       <-- Poem text file
└── .github/
    └── workflows/
        └── classroom.yml                          <-- Autograding (DO NOT MODIFY)
```

**Edit only `ScannerPractice.java`.**

---

## Autograding

| Test | What It Checks | Points |
|------|---------------|--------|
| testCreateScanner | Returns a valid Scanner object | 10 |
| testReadLine | Prints the first line of the file | 10 |
| testReadFile | Returns correct number of lines in array | 10 |
| testSumInt | Sums all integers in the file | 15 |
| testCalculateBill | Sums prices and applies tax correctly | 15 |
| testMostExpensiveItem | Returns name of highest-priced item | 10 |
| testCopyFileToOutput | Prints all lines of the file | 10 |
| testSearchFor (basic) | Prints lines containing query | 10 |
| testSearchFor (Raven) | Correctly searches the poem file | 10 |

**Total: 100 points**

---

## Try It Yourself — Practice Examples

Create `Practice.java` in the same directory and run it with `javac Practice.java && java Practice`.

**Example 1 — Reading a file line by line:**
```java
// Practice.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Practice {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("bill.txt"));
        int lineNum = 1;
        while (scanner.hasNextLine()) {
            System.out.println(lineNum + ": " + scanner.nextLine());
            lineNum++;
        }
        scanner.close();
    }
}
```

**Example 2 — Summing integers from a file:**
```java
// Practice.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Practice {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("ints.txt"));
        int sum = 0;
        while (scanner.hasNextInt()) {
            sum += scanner.nextInt();
        }
        System.out.println("Sum: " + sum);
        scanner.close();
    }
}
```

**Example 3 — Parsing prices:**
```java
// Practice.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Practice {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("bill.txt"));
        double total = 0;
        String mostExpensive = "";
        double highestPrice = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            double price = Double.parseDouble(parts[parts.length - 1]);
            total += price;

            if (price > highestPrice) {
                highestPrice = price;
                // Item name is everything except the last token
                mostExpensive = line.substring(0, line.lastIndexOf(" "));
            }
        }

        System.out.println("Subtotal: $" + String.format("%.2f", total));
        System.out.println("With 10% tax: $" + String.format("%.2f", total * 1.10));
        System.out.println("Most expensive: " + mostExpensive);
        scanner.close();
    }
}
```

**Example 4 — Simple search:**
```java
// Practice.java
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Practice {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("raven.txt"));
        String query = "Raven";
        int matches = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(query)) {
                System.out.println(line);
                matches++;
            }
        }
        System.out.println("Found " + matches + " lines containing '" + query + "'");
        scanner.close();
    }
}
```

---

## Tips for Success

1. Every method that reads a file must declare `throws FileNotFoundException`
2. Use `new Scanner(new File(fileName))` to create a Scanner for file input
3. Use `hasNextLine()` / `nextLine()` for reading text lines
4. Use `hasNextInt()` / `nextInt()` for reading integers
5. For price parsing, use `split(" ")` and get the last element with `parts[parts.length - 1]`
6. For `mostExpensiveItem`, the item name is everything before the last space — use `line.lastIndexOf(" ")` and `line.substring(0, lastSpace)`
7. Close your Scanner when done (good practice, though the tests do not require it)
8. The data files (`bill.txt`, `ints.txt`, `raven.txt`) are in the project root directory

---

## FAQ

**Q: What is `FileNotFoundException`?**
It is a checked exception that Java requires you to handle when opening files. Add `throws FileNotFoundException` to your method signature.

**Q: What is the difference between `nextLine()` and `nextInt()`?**
`nextLine()` reads an entire line as a String. `nextInt()` reads the next integer token. Be careful when mixing them — `nextInt()` does not consume the newline character.

**Q: How do I parse the item name from "Roasted Duck 36.99"?**
Split by space to get `["Roasted", "Duck", "36.99"]`. The price is the last element. The name is everything else. Use `line.substring(0, line.lastIndexOf(" "))` to get `"Roasted Duck"`.

**Q: What if the file has blank lines?**
`hasNextLine()` returns `true` for blank lines. `nextLine()` returns an empty string `""`. Your code should handle this gracefully.

**Q: Do I need to close the Scanner?**
It is good practice to call `scanner.close()`, but the tests do not explicitly check for it.

---

View all assignments and scoring breakdowns at [csplusplus.com/maven-tests](https://csplusplus.com/maven-tests)

*CS++ — AP Computer Science A — [csplusplus.com](https://csplusplus.com)*
