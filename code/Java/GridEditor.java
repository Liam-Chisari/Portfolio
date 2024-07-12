import java.util.Scanner;

public class GridEditor {
    private static final int MIN_GRID_SIZE = 3;
    private static final int MAX_GRID_SIZE = 80;
    private static final int BORDER_OFFSET = 2; //Border offset should be an even number.
    private static Scanner scanner = new Scanner(System.in);
    private static char[][] grid;
    private static int n;

    public static void main(String[] args) {
        // Increment n by 2 to account for the border characters.
        n = getValidGridSize() + BORDER_OFFSET;
        grid = new char[n][n];
        initializeGrid();

        // Main interaction loop
        while (true) {
            displayGrid();
            int[] coordinates = getValidCoordinates();
            char character = getCharacter();

            // Update grid if character is different
            if (grid[coordinates[0]][coordinates[1]] != character) {
                grid[coordinates[0]][coordinates[1]] = character;
            } else {
                System.out.println("Character already exists at that location.");
            }
        }
    }

    private static int getValidGridSize() {
        int size;
        do {
            System.out.printf("Enter grid size (%d < n <= %d): ", MIN_GRID_SIZE, MAX_GRID_SIZE);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume invalid input
            }
            size = scanner.nextInt();
        } while (size <= MIN_GRID_SIZE || size > MAX_GRID_SIZE);
        return size;
    }

    private static void initializeGrid() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = (i == 0 || i == n - 1) ? '_' : (j == 0 || j == n - 1) ? '|' : '.';
            }
        }
    }

    private static void displayGrid() {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private static int[] getValidCoordinates() {
        int x, y;
        //Width/height of each border side is defined as half of the border offset.
        int borderWidth = BORDER_OFFSET / 2;
        do {
            System.out.print("Enter coordinates (x,y): ");
            String input = scanner.next();
            String[] coordinates = input.split(",");
            if (coordinates.length == 2) {
                try {
                    x = Integer.parseInt(coordinates[0]);
                    y = Integer.parseInt(coordinates[1]);
                    //Using borderWidth to account for the border characters in the grid.
                    if (x >= borderWidth && x < n - borderWidth && y >= borderWidth && y < n - borderWidth) {
                        return new int[]{x, y}; // Valid coordinates
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            System.out.println("Invalid coordinates. Please enter in the format x,y (1-based).");
        } while (true);
    }

    private static char getCharacter() {
        System.out.print("Enter a character: ");
        return scanner.next().charAt(0);
    }
}
