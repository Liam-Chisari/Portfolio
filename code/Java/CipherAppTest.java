import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CipherAppTest {

    private static final int CIPHER_SHIFT = 3; // Must match the CipherApp's shift
    private static final String RELATIVE_PATH = "src/main/resources/"; // Update for test resources
    private static final String FILE_1 = "words.txt";
    private static final String FILE_2 = "moreWords.txt";

    // Utility to get text from a file
    private static String readFileContent(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    // Tests for encoding
    @Test
    void testEncodeValidFile() throws IOException {
        File original = new File(RELATIVE_PATH + FILE_1);
        File encoded = CipherApp.createEncodedFile(original);

        CipherApp.encodeFile(new Scanner(FILE_1)); // Simulate user input

        assertTrue(encoded.exists(), "Encoded file should be created.");

        String encodedContent = readFileContent(encoded);
        String expectedEncoded = CipherApp.caesarCipherEncode(readFileContent(original), CIPHER_SHIFT);
        assertEquals(expectedEncoded, encodedContent, "Encoded content should match.");
    }

    @Test
    void testEncodeEmptyFile() throws IOException {
        // ... (Create and test an empty file)
    }

    @Test
    void testEncodeSpecialCharacters() throws IOException {
        // ... (Create a file with special characters like !@#$%, and test encoding)
    }

    // Tests for decoding
    @Test
    void testDecodeValidEncodedFile() throws IOException {
        // ... (Similar to testEncodeValidFile, but in reverse: create a file, 
        // encode it manually, then test CipherApp.decodeFile)
    }

    @Test
    void testDecodeNonEncodedFile() {
        // ... (Try to decode a file that wasn't encoded with the cipher, expect an error)
    }


    // Tests for helper methods
    @Test
    void testGetValidFile() {
        File validFile = CipherApp.getValidFile(new Scanner(FILE_1), "");
        assertEquals(RELATIVE_PATH + FILE_1, validFile.getPath());

        // Test with an invalid file name
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        CipherApp.getValidFile(new Scanner("nonexistent.txt"), "");
        assertEquals(outStream.toString(), "FIle not found.");
    }

    @Test
    void testCreateEncodedFile() {
        File original = new File(RELATIVE_PATH + FILE_1);
        String[] filenameSplit = original.getName().split("\\.");
        File encoded = CipherApp.createEncodedFile(original);
        assertEquals(RELATIVE_PATH + filenameSplit[0] + "-encoded." + filenameSplit[1], encoded.getPath());
    }

    @Test
    void testCreateDecodedFile() {
        // ... (Similar to testCreateEncodedFile, but for decoded file naming)
    }

    @Test
    void testCaesarCipherEncode() {
        assertEquals("KHOOR", CipherApp.caesarCipherEncode("HELLO", CIPHER_SHIFT));
        // ... more tests for different cases (lowercase, mixed case, etc.)
    }

    @Test
    void testCaesarCipherDecode() {
        // ... (Similar to testCaesarCipherEncode, but for decoding)
    } 

    // ... other tests you might want to add:
    // - Error handling (file not found, etc.)
    // - User input validation (getValidIntChoice)
    // - Large file encoding/decoding performance
}
