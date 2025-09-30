package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerPractice {

    /**
     * Test your implementations here, perhaps for debugging purposes.
     * A test file has been created, with its path stored in the filePath variable for you.
     */
    public static void main(String[] args) {
        String filePath = "src//main//java//test_file.txt";
    }

    /**
     * Method 1: Create the scanner object.
     * For this method, assume that the file with name fileName is in the current directory.
     * Return the scanner object to use later on.
     *
     * @param fileName the name of the file in the current directory, for example "example_file.txt"
     * @return a Scanner configured to read the given file
     * @throws FileNotFoundException if the file cannot be found
     */
    public Scanner createScanner(String fileName) throws FileNotFoundException {
        // TODO: implement
        return null;
    }

    /**
     * Method 2: Read the next line in the file.
     * Create a scanner object using previous functions within this method. Make sure to close the scanner at the end.
     * Print the next line in the file; make sure to check that a next line exists. If there is no next line, print
     * "No next line".
     *
     * @param fileName the file to read
     * @throws FileNotFoundException if the file cannot be found
     */
    public void readLine(String fileName) throws FileNotFoundException {
        // TODO: implement
    }

    /**
     * Method 3: Read every line of the file and save it into an array.
     * Create a scanner object using previous functions within this method. Make sure to close the scanner at the end.
     * Return an array containing each file line as an element. Assume the file has numLines lines.
     *
     * @param fileName the file to read
     * @param numLines the number of lines expected in the file
     * @return a String[] of lines
     * @throws FileNotFoundException if the file cannot be found
     */
    public String[] readFile(String fileName, int numLines) throws FileNotFoundException {
        // TODO: implement
        return new String[numLines];
    }

    /**
     * Method 4: Sum every integer value from the file.
     * Create a scanner object using previous functions within this method. Make sure to close the scanner at the end.
     * The Scanner.hasNextInt() method may be helpful. Return the sum of every integer value in the file;
     * you may assume there is at least one integer contained in the file.
     *
     * @param fileName the file to scan for integers
     * @return the sum of all integers in the file
     * @throws FileNotFoundException if the file cannot be found
     */
    public int sumInt(String fileName) throws FileNotFoundException {
        // TODO: implement
        return 0;
    }

    /**
     * Calculate the total bill by summing up items and adding tax.
     * Each line is of the form "Roasted Duck 36.99".
     * taxProportion is something like 0.07.
     * Note: you must be within a few cents of the correct answer.
     *
     * @param fileName the file listing items and prices, where the last token on each line is the price
     * @param taxProportion tax as a proportion (e.g., 0.07 for 7%)
     * @return the total with tax applied
     * @throws FileNotFoundException if the file cannot be found
     */
    public double calculateBill(String fileName, double taxProportion) throws FileNotFoundException {
        // TODO: implement
        return 0.0;
    }

    /**
     * Using the same format as the bill above, return the name of the most expensive item.
     * Lines are of the form "<name> <price>" where the last space separates the name from the price.
     *
     * @param fileName the file listing items and prices
     * @return the name of the most expensive item, or an empty string if none found
     * @throws FileNotFoundException if the file cannot be found
     */
    public String mostExpensiveItem(String fileName) throws FileNotFoundException {
        // TODO: implement
        return "";
    }

    /**
     * Print every line in the given file to standard output.
     *
     * @param filename the file to copy to output
     * @throws FileNotFoundException if the file cannot be found
     */
    public void copyFileToOutput(String filename) throws FileNotFoundException {
        // TODO: implement
    }

    /**
     * Print all lines matching the query parameter (case-insensitive).
     * For example, if the file is:
     * I like dogs
     * I like cats
     * It's raining cats and dogs
     * and the query is "cats", you should print only the last two lines.
     * If nothing matches, print "No results."
     *
     * @param filename the file to search
     * @param query the case-insensitive substring to match
     * @throws FileNotFoundException if the file cannot be found
     */
    public void searchFor(String filename, String query) throws FileNotFoundException {
        // TODO: implement
        // Hint: Use .contains() some queries could have a period or comma, not just a space
    }
}
