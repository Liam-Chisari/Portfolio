
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class CsvSearch {
    public static void main(String[] args) {
        try {
            // Read the .csv file
            String csvFilePath = "your_file.csv"; // Replace "your_file.csv" with the actual file path
            List lines = readCsvFile(csvFilePath);

        

                      
        // Prompt user for search term
        System.out.print("Enter a search term: ");
        String searchTerm = input().trim().toLowerCase();

        // Search for the search term in each line
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length >= 4 && (fields[1].toLowerCase().contains(searchTerm) || fields[3].toLowerCase().contains(searchTerm))) {
                System.out.println(line);
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private static List<String> readCsvFile(String filePath) throws Exception {
    List<String> lines = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;
    while ((line = reader.readLine()) != null) {
        lines.add(line);
    }
    reader.close();
    return lines;
}

private static String input() {
    BufferedReader reader = new BufferedReader(System.in);
    return reader.readLine();
}
                  

      

}
