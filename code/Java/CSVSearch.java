import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the .csv file:");
        String fileName = scanner.nextLine();
        System.out.println("Please enter the search term:");
        String searchTerm = scanner.nextLine();
        scanner.close();

        List<String> lines = readCSVFile(fileName);
        List<String> matchingLines = searchLines(lines, searchTerm);
        displayLines(matchingLines);
    }

    private static List<String> readCSVFile(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        return lines;
    }

    private static List<String> searchLines(List<String> lines, String searchTerm) {
        List<String> matchingLines = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();

        for (String line : lines) {
            for (String part : line.split(",")) {
                if (part.toLowerCase().contains(searchTerm)) {
                    matchingLines.add(line);
                    break;
                }
            }
        }

        return matchingLines;
    }

    private static void displayLines(List<String> lines) {
        if (lines.isEmpty()) {
            System.out.println("No matching entries found.");
        } else {
            System.out.println("Matching entries:");
            for (String line : lines) {
                System.out.println(line);
            }
        }
    }
}
