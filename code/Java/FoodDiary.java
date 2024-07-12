import java.io.*;
import java.util.*;

public class FoodDiary {

    private static List<Day> diary = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadDiary();

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addFood();
                    break;
                // ... (cases for other options)
                case 5:
                    saveDiary();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ... (other methods: loadDiary, saveDiary, displayMenu, addFood, etc.)
}
