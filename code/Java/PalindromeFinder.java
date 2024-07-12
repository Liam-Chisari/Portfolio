import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PalindromeFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();
        scanner.close();

        List<String> palindromes = findPalindromes(sentence);
        if (!palindromes.isEmpty()) {
            System.out.println("Palindromes found:");
            for (String palindrome : palindromes) {
                System.out.println(palindrome + " - " + reverseString(palindrome));
            }
            try {
                writeToFile(palindromes);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        } else {
            System.out.println("No palindromes found. Here's a random palindrome for you: " + generateRandomPalindrome());
        }
    }

    public static List<String> findPalindromes(String sentence) {
        List<String> palindromes = new ArrayList<>();
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (word.length() > 1 && isPalindrome(word)) {
                palindromes.add(word);
            }
        }
        return palindromes;
    }

    public static boolean isPalindrome(String word) {
        int left = 0;
        int right = word.length() - 1;
        while (left <= right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String reverseString(String word) {
        StringBuilder reversed = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed.append(word.charAt(i));
        }
        return reversed.toString();
    }

    public static void writeToFile(List<String> palindromes) throws IOException {
        File file = new File("palindromes.txt");
        FileWriter fileWriter = new FileWriter(file);
        for (String palindrome : palindromes) {
            fileWriter.write(palindrome + " - " + reverseString(palindrome) + System.lineSeparator());
        }
        fileWriter.close();
    }


public static String generateRandomPalindrome() {
    String[] englishPalindromes = {"bob", "dad", "deed", "noon", "refer", "redder", "solos", "tenet", "tobot"};

    Random random = new Random();
    int index = random.nextInt(englishPalindromes.length);
    return englishPalindromes[index];
}


}
