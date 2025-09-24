package scanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import scanner.ScannerPractice;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.Scanner;

public class ScannerPracticeTest {

    private final ScannerPractice scan = new ScannerPractice();

    private String normalizeString(String str) {
    	return str.replaceAll("\r\n", "\n"); // convert CRLF to LF
    }
    
    private String md5(String input) {
    	// https://stackoverflow.com/a/30119004
    	// md5 hashing utility
    	try {
    		return String.format("%032x", new BigInteger(1,MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"))));
    	} catch (Exception ex) {
    		return ""; // should never happen unless you intentionally output garbage but this will fail it
    	}
    }
    
    private void writeFile(String filename, String contents) {
    	File file = new File(filename);
    	try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(contents);
			bw.close();
		} catch (IOException e) {
			System.err.println("unable to write to " + file);
			e.printStackTrace();
		}
    }

    @Test
    public void testScannerCreate() {
    	try {
	        writeFile("test.txt", "Test content");
	        Scanner sc = scan.createScanner("test.txt");
	        try {
	        	assertTrue(sc.hasNextLine(), "Scanner should have next line.");
	        } catch (IllegalStateException ex) {
	        	fail("Scanner should be redy to read a line.");
	        }
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	}
    	
    	try {
    		Scanner sc = scan.createScanner("thisdoesnotexist.txt");
    		
    		fail("Attempt to open nonexistent file should not work.");
    		
    	} catch (FileNotFoundException ex) {
    		
    	}
    }
    
    @Test
    public void testLineReads() {
    	try {
	        writeFile("line_read_test.txt", "Line 1\r\nAnother 2nd Line");
	        String[] readLines = scan.readFile("line_read_test.txt", 2);
	        assertEquals(readLines.length, 2, "2 lines should be read");
	        assertEquals(readLines[0], "Line 1");
	        assertEquals(readLines[1], "Another 2nd Line");
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	}
    }

    @Test
    public void testSum() {
    	try {
	        writeFile("numbers.txt", "6 9 3 1");
	        assertEquals(19, scan.sumInt("numbers.txt"));
	        writeFile("numbers.txt", "1337 ".repeat(42));
	        assertEquals(42 * 1337, scan.sumInt("numbers.txt"));
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	}
    }
    
    private static String getFirstDinner() {
    	// this could be a constant but since I can't assume stuff about Java version and I want it to look nice it'll look like this
    	String bill = "";
    	bill += "Grilled Salmon with Lemon Herb Butter 28.95\n";
    	bill += "Caesar Salad 12.50\n";
    	bill += "Truffle Mac and Cheese 16.75\n";
    	bill += "Garlic Bread 8.25\n";
    	bill += "Chocolate Lava Cake 11.50\n";
    	bill += "Glass of Pinot Grigio 13.00\n";
    	bill += "Sparkling Water 4.50\n";
    	bill += "Coffee 3.75";
    	return bill;
    }
    
    private static String getFirstLunch() {
    	// this could be a constant but since I can't assume stuff about Java version and I want it to look nice it'll look like this
    	String bill = "";
    	bill += "Pizza Slice (Pepperoni) 3.25\n";
    	bill += "Chicken Nuggets (6 pieces) 4.50\n";
    	bill += "French Fries 2.75\n";
    	bill += "Garden Salad with Ranch 3.00\n";
    	bill += "Apple Slices 1.50\n";
    	bill += "Chocolate Chip Cookie 2.00\n";
    	bill += "Milk (1% Low Fat) 1.25\n";
    	bill += "Orange Juice 2.25";
    	return bill;
    }
    
    @Test
    public void testCalculateBill() {
    	try {
	        writeFile("bill.txt", getFirstDinner());
	        // when comparing doubles you need to specify a margin of error basically
	        // otherwise you get funny things like
	        // org.opentest4j.AssertionFailedError: expected: <107.63> but was: <107.63199668884278>

	        assertEquals(107.63, scan.calculateBill("bill.txt", 0.085), 0.02);
	        
	        writeFile("bill.txt", getFirstLunch());
	        assertEquals(21.83, scan.calculateBill("bill.txt", 0.065), 0.02);
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	}
    }
    
    @Test
    public void testMostExpensiveItem() {
    	try {
	        writeFile("bill.txt", getFirstDinner());
	        // when comparing doubles you need to specify a margin of error basically
	        // otherwise you get funny things like
	        // org.opentest4j.AssertionFailedError: expected: <107.63> but was: <107.63199668884278>

	        assertEquals("Grilled Salmon with Lemon Herb Butter", scan.mostExpensiveItem("bill.txt"));
	        
	        writeFile("bill.txt", getFirstLunch());
	        assertEquals("Chicken Nuggets (6 pieces)", scan.mostExpensiveItem("bill.txt"));
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	}
    }

    @Test
    public void testCopyToOutput() {
    	
    	String[] inputs = {"abcdef =D\n12345", "~!@#$%^&*()\n)(*&^%$#@!~"};
    	
    	for(String testCaseContent: inputs) {
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        writeFile("input.txt", testCaseContent);
	        System.setOut(new PrintStream(outContent));
	        try {
	        	scan.copyFileToOutput("input.txt");
	        } catch (FileNotFoundException ex) {
	    		// will never happen unless the file is read incorrectly
	    		System.err.println("File read was not completed correctly.");
	    		ex.printStackTrace();
	    		fail("File was not read from correctly. Is your path argument correct?", ex);
	    	}
	        
	        // handle edge cases like diff newline types and off by one newline things
	        assertEquals(testCaseContent.strip(), normalizeString(outContent.toString()).strip());
    	}
        
    }
    
    @Test
    public void testSingleLineRead() {
    	
    	String[] testCases = {"abcdefg\nhijklmnop\nxyz", "3\n2\n1"};
    	String[] testCaseAnswers = {"abcdefg", "3"};
    	
    	for(int i = 0; i < testCases.length; i ++) {
	    	String testCaseContent = testCases[i];
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        writeFile("single_line_read_input.txt", testCaseContent);
	        System.setOut(new PrintStream(outContent));
	        try {
	        	scan.readLine("single_line_read_input.txt");
	        } catch (FileNotFoundException ex) {
	    		// will never happen unless the file is read incorrectly
	    		System.err.println("File read was not completed correctly.");
	    		ex.printStackTrace();
	    		fail("File was not read from correctly. Is your path argument correct?", ex);
	    	}
	        
	        // handle edge cases like diff newline types and off by one newline things
	        assertEquals(testCaseAnswers[i], normalizeString(outContent.toString()).strip());
    	}
    }
    
    @Test
    public void testMiniSearchEngine() {
    	
    	String[][] testCases = {{
    		"the quick brown fox",
    		"jumped over",
    		"the lazy dog"
    	}, {
    		"something"
    	}};
    	
    	String[] queries = {"the", "nothing"};
    	String[] testCaseAnswers = {"the quick brown fox\nthe lazy dog", "No results."};
    	
    	for(int i = 0; i < testCases.length; i ++) {
	    	String[] testCaseLines = testCases[i];
	    	String testCaseContent = String.join("\n", testCaseLines);
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        writeFile("search_test.txt", testCaseContent);
	        System.setOut(new PrintStream(outContent));
	        try {
	        	scan.searchFor("search_test.txt", queries[i]);
	        } catch (FileNotFoundException ex) {
	    		// will never happen unless the file is read incorrectly
	    		System.err.println("File read was not completed correctly.");
	    		ex.printStackTrace();
	    		fail("File was not read from correctly. Is your path argument correct?", ex);
	    	}
	        
	        // handle edge cases like diff newline types and off by one newline things
	        assertEquals(testCaseAnswers[i].strip(), normalizeString(outContent.toString()).strip());
    	}
    }
    
    
    @Test
    public void testMiniSearchEngineRavenCase() {
    	
    	// systemInfo();
    	
    	try {
    		writeRavenPoem();
    	} catch (IOException ex) {
    		fail("Failed to write poem input file for test case.", ex);
    	}
    		
    	try {
    		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    		System.setOut(new PrintStream(outContent));

    		// System.err.println(new File("the_raven.md").getAbsolutePath());
    		
    		scan.searchFor("the_raven.md", "raven");
    		
    		String answerToCheck = normalizeString(outContent.toString()).strip();
    		System.err.println(answerToCheck);
    		System.err.println("ans md5 " + md5(answerToCheck));
    		// if you know how to collide this, you would know how to write the method anyways
    		assertNotEquals("No results.", answerToCheck, "Should have at least one result searching for raven.");
    		assertEquals("f699503a344aebb1689465ac700ccb0a", md5(answerToCheck));
    		// otherwise implement the method as specified and this will pass
    	} catch (FileNotFoundException ex) {
    		// will never happen unless the file is read incorrectly
    		System.err.println("File read was not completed correctly.");
    		ex.printStackTrace();
    		fail("File was not read from correctly. Is your path argument correct?", ex);
    	} catch (IOException ex) {
			ex.printStackTrace();
			fail("File was not read from correctly. Is your path argument correct?", ex);
		}
    }
    private void writeRavenPoem() throws IOException {
    	// how to encode
    	// go to "CyberChef" (search it up)
    	// add an escape string block with double quotes that is json compatible and put the poem in
    	// this is to workaround a stupid java "bug" that happens if we don't write the file oursleves at test time
    	BufferedWriter bw = new BufferedWriter(new FileWriter("the_raven.md"));
    	bw.write("Once upon a midnight dreary, while I pondered, weak and weary,  \nOver many a quaint and curious volume of forgotten lore\u2014  \nWhile I nodded, nearly napping, suddenly there came a tapping,  \nAs of someone gently rapping, rapping at my dorm room door.  \n\"'Tis some visitor,\" I muttered, \"tapping at my dorm room door\u2014  \nOnly this and nothing more.\"\n\nAh, distinctly I remember, it was in the bleak December,  \nAnd each separate dying ember wrought its ghost upon the floor.  \nEagerly I wished the morrow;\u2014vainly I had sought to borrow  \nFrom my books surcease of sorrow\u2014sorrow for the lost APs,  \nFor the ones who\u2019d never make it to the college admissions office  \nWith their 4.8 GPA and flawless extracurriculars.\n\nAnd the silken, sad, uncertain rustling of each purple curtain  \nThrilled me\u2014filled me with fantastic terrors never felt before;  \nSo that now, to still the beating of my heart, I stood repeating,  \n\"'Tis some visitor entreating entrance at my chamber door\u2014  \nSome late visitor entreating entrance at my chamber door;\u2014  \nThis it is and nothing more.\"\n\nPresently my soul grew stronger; hesitating then no longer,  \n\"Sir,\" said I, \"or Madam, truly your forgiveness I implore;  \nBut the fact is I was napping, and so gently you came rapping,  \nAnd so faintly you came tapping, tapping at my chamber door,  \nThat I scarce was sure I heard you\"\u2014here I opened wide the door;\u2014  \nDarkness there and nothing more.\n\nDeep into that darkness peering, long I stood there wondering, fearing,  \nDoubting, dreaming dreams no mortal ever dared to dream before;  \nBut the silence was unbroken, and the stillness gave no token,  \nAnd the only word there spoken was the whispered word, \"Lenore?\"  \nThis I whispered, and an echo murmured back the word, \"Lenore!\"\u2014  \nMerely this and nothing more.\n\nBack into the chamber turning, all my soul within me burning,  \nSoon again I heard a tapping somewhat louder than before.  \n\"Surely,\" said I, \"surely that is something at my window lattice;  \nLet me see, then, what there is, and this mystery explore\u2014  \nLet my heart be still a moment and this mystery explore;\u2014  \n'Tis the wind and nothing more!\"\n\nOpen here I flung the shutter, when, with many a flirt and flutter,  \nIn there stepped a stately raven of the saintly days of yore;  \nNot the least obeisance made he; not a minute stopped or stayed he;  \nBut, with mien of lord or lady, perched above my chamber door\u2014  \nPerched, and sat, and will not fly, and still is sitting, still is sitting  \nOn the pallid bust of Pallas just above my chamber door;  \nAnd his eyes have all the seeming of a demon\u2019s that is dreaming,  \nAnd the lamplight o\u2019er him streaming throws his shadow on the floor;  \nAnd my soul from out that shadow that lies floating on the floor  \nShall be lifted\u2014nevermore!\n\nAnd the Raven, never flitting, still is sitting, still is sitting  \nOn the pallid bust of Pallas just above my chamber door;  \nAnd his eyes have all the seeming of a demon\u2019s that is dreaming,  \nAnd the lamplight o\u2019er him streaming throws his shadow on the floor;  \nAnd my soul from out that shadow that lies floating on the floor  \nShall be lifted\u2014nevermore!\n\nAnd the Raven, never flitting, still is sitting, still is sitting  \nOn the pallid bust of Pallas just above my chamber door;  \nAnd his eyes have all the seeming of a demon\u2019s that is dreaming,  \nAnd the lamplight o\u2019er him streaming throws his shadow on the floor;  \nAnd my soul from out that shadow that lies floating on the floor  \nShall be lifted\u2014nevermore!\n\nBut the Raven, never flitting, still is sitting, still is sitting  \nOn the pallid bust of Pallas just above my chamber door;  \nAnd his eyes have all the seeming of a demon\u2019s that is dreaming,  \nAnd the lamplight o\u2019er him streaming throws his shadow on the floor;  \nAnd my soul from out that shadow that lies floating on the floor  \nShall be lifted\u2014nevermore!\n\nAnd now, in the hallowed halls of Canyon Crest Academy,  \nWhere the ivy climbs the brick and the SAT scores rise like fire,  \nThere stands a raven in the library, perched on a textbook spine,  \nWhispering, \u201c*No, you will not get into Stanford.*\u201d  \nAnd every student who walks past, with their 4.0 and their essay,  \nHears the echo: *\u201cNevermore.\u201d*  \nAnd the Raven, never flitting, still is sitting, still is sitting  \nOn the honor roll, just above the GPA calculator.  \nAnd his eyes have all the seeming of a college counselor\u2019s that is dreaming\u2026  \nAnd the lamplight o\u2019er him streaming throws his shadow on the floor\u2026  \nAnd my soul from out that shadow that lies floating on the floor  \nShall be lifted\u2014nevermore.\n\nAnd so, dear student, if you see a raven in the quad at 3 a.m.,  \nWith a backpack full of AP textbooks and a look of judgment in its eyes\u2026  \nDo not speak. Do not move.  \nJust whisper: *\u201cI just want to be happy.\u201d*  \nAnd the Raven will reply:  \n**\u201cNevermore.\u201d**");
    	bw.close();
    }
}
