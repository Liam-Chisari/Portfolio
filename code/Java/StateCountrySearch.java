import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

class StateInformation {
    private String stateAbbr;
    private String stateName;
    private String countryAbbr;
    private String countryName;
   
    // Constructor
    public StateInformation(String stateAbbr, String stateName, String countryAbbr, String countryName) {
        this.stateAbbr = stateAbbr;
        this.stateName = stateName;
        this.countryAbbr = countryAbbr;
        this.countryName = countryName;
    }
   // getters and setters (omitted for brevity)
}
public class StateCountrySearch {
    private List<StateInformation> dataList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    public void runProgram() throws FileNotFoundException, CsvException {
        String fileName = promptForFileName();
        if (loadDataFromCSV(fileName)) {
            System.out.println("File has been loaded successfully!");
        } else {
            return;
        }
        int option;
        do {
            showMenu();
            option = getMenuOption();
            processMenuOption(option);
        } while (option != 5);
    }
    private boolean loadDataFromCSV(String fileName) throws FileNotFoundException, CsvException {
        File file = new File(fileName);
        CSVReader csvReader = new CSVReader(new FileReader(file));
        String[] headerRow = csvReader.readNext(); // Read and skip the header row
        if (headerRow == null || !headerRow[0].equals("State abbreviation/number")) {
            System.out.println("Invalid file format. Expecting correct header.");
            return false; // Invalid header
        }
        String[] data;
        while ((data = csvReader.readNext()) != null) { 
            if (data.length != 4) {
                System.out.println("Invalid data row: " + String.join(", ", data));
                continue; // Skip invalid data row
            }
                dataList.add(new StateInformation(data[0], data[1], data[2], data[3]));        
        }
        csvReader.close();
        return true;
    }
    private String promptForFileName() {
        System.out.print("Please enter the CSV file name: ");
        return scanner.nextLine();
    }
    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1) Search by country name");
        System.out.println("2) Search by country abbreviation");
        System.out.println("3) Search by state name");
        System.out.println("4) Search by state abbreviation");
        System.out.println("5) Exit");
        System.out.print("Enter your choice: ");
    }
    private int getMenuOption() {
        int option = 0;
        try {
            option = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid menu option.");
        }
        return option;
    }   private void processMenuOption(int option) {
        switch (option) {
            case 1:
                searchByCountryName();
                break;
            case 2:
                searchByCountryAbbr();
                break;
            case 3:
                searchByStateName();
                break;
            case 4:
                searchByStateAbbr();
                break;
            case 5:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }     
    private void searchByCountryName() {
        System.out.print("Enter the country name: ");
        String countryName = scanner.nextLine();
        displayMatches(dataList.stream().filter(state -> 
                state.getCountryName().toLowerCase().contains(countryName.toLowerCase()))
                .collect(toList()));
    }     private void searchByCountryAbbr() {  
        System.out.print("Enter the country abbreviation: ");
        String countryAbbr = scanner.nextLine();     displayMatches(dataList.stream().filter(state -> 
                state.getCountryAbbr().equalsIgnoreCase(countryAbbr.trim()))
                .collect(toList()));
    }  private void searchByStateName() {   
        System.out.print("Enter the state name: ");
        String stateName = scanner.nextLine();
        displayMatches(dataList.stream().filter(state ->     state.getStateName().toLowerCase().contains(stateName.toLowerCase()))
                .collect(toList()));  
    }   private void searchByStateAbbr() {  
        System.out.print("Enter the state abbreviation: ");
        String stateAbbr = scanner.nextLine();    displayMatches(dataList.stream().filter(state -> 
                state.getStateAbbr().equalsIgnoreCase(stateAbbr.trim()))
                .collect(toList()));
    }   private void displayMatches(List<StateInformation> matches) {
        if (matches.size() > 0) {
            System.out.println("\nMatches:");
            for (StateInformation state : matches) {
                System.out.println(state.getStateAbbr() + ", " + state.getStateName() + ", " +        state.getCountryAbbr() + ", " + state.getCountryName());         }
        } else {
            System.out.println("No matches found.");
        }   
    }   public static void main(String[] args) {     
        try {
            new StateCountrySearch().runProgram();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    } 
}
