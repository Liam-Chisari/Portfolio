import java.io.*;
import java.util.Scanner;

public class CipherEncodingProgramOrig {
   private static final String FILE_EXTENSION = ".txt";
   private static final String ENCODED_FILE_SUFFIX = "-encoded";
   private static final String DECODED_FILE_SUFFIX = "-decoded";

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       int option;

       do {
           displayMenu();
           option = scanner.nextInt();
           scanner.nextLine(); // Consume the newline character

           switch (option) {
               case 1:
                   encodeFile(scanner);
                   break;
               case 2:
                   decodeFile(scanner);
                   break;
               case 3:
                   System.out.println("Goodbye!");
                   break;
               default:
                   System.out.println("Invalid option. Please try again.");
           }
       } while (option != 3);

       scanner.close();
   }

   private static void displayMenu() {
       System.out.println("\nMenu:");
       System.out.println("1. Encode file");
       System.out.println("2. Decode file");
       System.out.println("3. Exit");
       System.out.print("Enter your choice: ");
   }

   private static void encodeFile(Scanner scanner) {
       System.out.print("Enter the name of the file to encode: ");
       String fileName = scanner.nextLine();

       if (fileName.isEmpty()) {
           System.out.println("Invalid file name. Please try again.");
           return;
       }

       File originalFile = new File(fileName);
       if (!originalFile.exists()) {
           System.out.println("File not found. Please try again.");
           return;
       }

       try {
           String encodedText = encodeText(readFile(originalFile));
           saveToFile(encodedText, getEncodedFileName(fileName));
           System.out.println("File encoded successfully.");
       } catch (IOException e) {
           System.out.println("Error while encoding the file: " + e.getMessage());
       }
   }

   private static void decodeFile(Scanner scanner) {
       System.out.print("Enter the name of the file to decode: ");
       String fileName = scanner.nextLine();

       if (fileName.isEmpty()) {
           System.out.println("Invalid file name. Please try again.");
           return;
       }

       File encodedFile = new File(fileName);
       if (!encodedFile.exists()) {
           System.out.println("File not found. Please try again.");
           return;
       }

       try {
           String decodedText = decodeText(readFile(encodedFile));
           saveToFile(decodedText, getDecodedFileName(fileName));
           System.out.println("File decoded successfully.");
       } catch (IOException e) {
           System.out.println("Error while decoding the file: " + e.getMessage());
       }
   }

   private static String readFile(File file) throws IOException {
       BufferedReader reader = new BufferedReader(new FileReader(file));
       StringBuilder text = new StringBuilder();
       String line;

       while ((line = reader.readLine()) != null) {
           text.append(line).append("\n");
       }

       reader.close();
       return text.toString();
   }

   private static void saveToFile(String text, String fileName) throws IOException {
       FileWriter writer = new FileWriter(fileName);
       writer.write(text);
       writer.close();
   }

   // Simple Caesar cipher for encoding/decoding (you can replace this with any cipher of your choice)
   private static String encodeText(String text) {
       StringBuilder encodedText = new StringBuilder();

       for (char c : text.toCharArray()) {
           if (Character.isLetter(c)) {
               char shiftedChar = (char) (c + 3);
               if (Character.isLowerCase(c)) {
                   if (shiftedChar > 'z') {
                       encodedText.append((char) (c - 26 + 3));
                   } else {
                       encodedText.append(shiftedChar);
                   }
               } else {
                   if (shiftedChar > 'Z') {
                       encodedText.append((char) (c - 26 + 3));
                   } else {
                       encodedText.append(shiftedChar);
                   }
               }
           } else {
               encodedText.append(c);
           }
       }

       return encodedText.toString();
   }

   private static String decodeText(String text) {
       return encodeText(text); // Using the same cipher for decoding
   }

   private static String getEncodedFileName(String fileName) {
       int index = fileName.lastIndexOf(FILE_EXTENSION);
       if (index != -1) {
           return fileName.substring(0, index) + ENCODED_FILE_SUFFIX + fileName.substring(index);
       } else {
           return fileName + ENCODED_FILE_SUFFIX;
       }
   }

   private static String getDecodedFileName(String fileName) {
       int index = fileName.lastIndexOf(FILE_EXTENSION);
       if (index != -1) {
           return fileName.substring(0, index) + DECODED_FILE_SUFFIX + fileName.substring(index);
       } else {
           return fileName + DECODED_FILE_SUFFIX;
       }
   }
}
