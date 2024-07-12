import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StateSearch {
    private List<State> states = new ArrayList<>();
    private Scanner userInput = new Scanner(System.in);
    

    public StateSearch(String fileName) {
        loadStatesFromFile(fileName);
    }

    private void loadStatesFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                State state = new State(parts[0], parts[1], parts[2], parts[3]);
                states.add(state);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Error loading data from file: " + e.getMessage());
            System.exit(1);
        }
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1) Search by country name");
            System.out.println("2) Search by country abbreviation");
            System.out.println("3) Search by state name");
            System.out.println("4) Search by state abbreviation");
            System.out.println("5) Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(userInput.nextLine());
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Input must be a single number.");
                choice = 0;
            }
            switch (choice) {
                case 1:
                    searchByCountryName();
                    break;
                case 2:
                    searchByCountryAbbreviation();
                    break;
                case 3:
                    searchByStateName();
                    break;
                case 4:
                    searchByStateAbbreviation();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void searchByCountryName() {
        System.out.print("Enter the country name: ");
        String searchTerm = userInput.nextLine();
        for (State state : states) {
            if (state.getCountryName().equalsIgnoreCase(searchTerm)) {
                System.out.println(state);
            }
        }
        if (states.stream().noneMatch(s -> s.getCountryName().equalsIgnoreCase(searchTerm))) {
            System.out.println("No matches found.");
        }
    }

    private void searchByCountryAbbreviation() {
        System.out.print("Enter the country abbreviation: ");
        String searchTerm = userInput.nextLine();
        for (State state : states) {
            if (state.getCountryAbbreviation().equalsIgnoreCase(searchTerm)) {
                System.out.println(state);
            }
        }
        if (states.stream().noneMatch(s -> s.getCountryAbbreviation().equalsIgnoreCase(searchTerm))) {
            System.out.println("No matches found.");
        }
    }

    private void searchByStateName() {
        System.out.print("Enter the state name: ");
        String searchTerm = userInput.nextLine();
        for (State state : states) {
            if (state.getStateName().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(state);
            }
        }
        if (states.stream().noneMatch(s -> s.getStateName().toLowerCase().contains(searchTerm.toLowerCase()))) {
            System.out.println("No matches found.");
        }
    }

    private void searchByStateAbbreviation() {
        System.out.print("Enter the state abbreviation: ");
        String searchTerm = userInput.nextLine();
        for (State state : states) {
            if (state.getStateAbbreviation().equalsIgnoreCase(searchTerm)) {
                System.out.println(state);
            }
        }
        if (states.stream().noneMatch(s -> s.getStateAbbreviation().equalsIgnoreCase(searchTerm))) {
            System.out.println("No matches found.");
        }
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter the name of the CSV file: ");
        String fileName = userInput.nextLine();
        StateSearch stateSearch = new StateSearch(fileName);
        System.out.println("File loaded successfully!");
        stateSearch.displayMenu();
        userInput.close();
    }

    static class State {
        private String stateAbbreviation;
        private String stateName;
        private String countryAbbreviation;
        private String countryName;

        public State(String stateAbbreviation, String stateName, String countryAbbreviation, String countryName) {
            this.stateAbbreviation = stateAbbreviation;
            this.stateName = stateName;
            this.countryAbbreviation = countryAbbreviation;
            this.countryName = countryName;
        }

        public String getStateAbbreviation() {
            return stateAbbreviation;
        }

        public String getStateName() {
            return stateName;
        }

        public String getCountryAbbreviation() {
            return countryAbbreviation;
        }

        public String getCountryName() {
            return countryName;
        }

        @Override
        public String toString() {
            return "State{" +
                    "stateAbbreviation='" + stateAbbreviation + '\'' +
                    ", stateName='" + stateName + '\'' +
                    ", countryAbbreviation='" + countryAbbreviation + '\'' +
                    ", countryName='" + countryName + '\'' +
                    '}';
        }
    }
}
