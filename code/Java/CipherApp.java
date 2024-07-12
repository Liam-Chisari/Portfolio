import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CipherApp {

    // Replace with your cipher of choice
    private static final int CIPHER_SHIFT = 3;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu(); 
            int choice = getValidIntChoice(scanner, 1, 3); 

            switch (choice) {
                case 1: 
                    encodeFile(scanner);
                    break;
                case 2: 
                    decodeFile(scanner);
                    break;
                case 3: 
                    running = false;
                    System.out.println("Exiting program.");
            }
        }
        scanner.close();
    }


    private static void displayMenu() {
        System.out.println("\nCipher Encoder/Decoder");
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }


    private static void encodeFile(Scanner scanner) {
        File fileToEncode = getValidFile(scanner, "Enter the file name to encode: ");

        try {
            String originalText = Files.readString(fileToEncode.toPath());
            String encodedText = caesarCipherEncode(originalText, CIPHER_SHIFT); 
            File encodedFile = createEncodedFile(fileToEncode);
            Files.writeString(encodedFile.toPath(), encodedText);
            System.out.println("File encoded successfully.");
        } catch (IOException e) {
            System.out.println("Error during encoding: " + e.getMessage());
        }
    }

    private static void decodeFile(Scanner scanner) {
        File fileToDecode = getValidFile(scanner, "Enter the file name to decode: ");

        try {
            String encodedText = Files.readString(fileToDecode.toPath());
            String decodedText = caesarCipherDecode(encodedText, CIPHER_SHIFT);
            File decodedFile = createDecodedFile(fileToDecode);
            Files.writeString(decodedFile.toPath(), decodedText);
            System.out.println("File decoded successfully.");
        } catch (IOException e) {
            System.out.println("Error during decoding: " + e.getMessage());
        }
    }


    private static File getValidFile(Scanner scanner, String prompt) {
        File file;
        do {
            System.out.print(prompt);
            file = new File(scanner.nextLine());
            if (!file.exists()) System.out.println("File not found.");
        } while (!file.exists());
        return file;
    }

    // Creates the encoded file 
    private static File createEncodedFile(File originalFile) {
        String originalName = originalFile.getName();
        int extensionIndex = originalName.lastIndexOf('.');
        String encodedFilename = originalName.substring(0, extensionIndex) + "-encoded" + originalName.substring(extensionIndex);
        return new File(encodedFilename);
    }


    // Creates the decoded file 
    private static File createDecodedFile(File originalFile) {
        String originalName = originalFile.getName();
        int extensionIndex = originalName.lastIndexOf('.');
        String decodedFilename = originalName.substring(0, extensionIndex) + "-decoded" + originalName.substring(extensionIndex);
        return new File(decodedFilename);
    }

    // Caesar cipher implementation - Encode
    private static String caesarCipherEncode(String text, int shift) {
        StringBuilder encodedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int originalAlphabetPosition = character - base;
                int newAlphabetPosition = (originalAlphabetPosition + shift) % 26;
                char encodedCharacter = (char) (base + newAlphabetPosition);
                encodedText.append(encodedCharacter);
            } else {
                encodedText.append(character);
            }
        }
        return encodedText.toString();
    }

    // Caesar cipher implementation - Decode (inverts the shift)
    private static String caesarCipherDecode(String text, int shift) {
        return caesarCipherEncode(text, 26 - shift);
    }


    private static int getValidIntChoice(Scanner scanner, int low, int high) {
        int choice;
        do {
            while (!scanner.hasNextInt()) { 
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice < low || choice > high) System.out.println("Invalid choice.");
        } while (choice < low || choice > high);
        return choice;
    }


}

