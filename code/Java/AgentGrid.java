import java.util.Scanner;

public class AgentGrid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get grid size from user
        System.out.print("Enter the length of the grid's sides: ");
        int gridSize = scanner.nextInt();

        // Initialize grid and agent
        char[][] grid = new char[gridSize][gridSize];
        int agentX = 0;  // Agent's initial X position
        int agentY = 0;  // Agent's initial Y position
        char agentOrientation = '^'; // Agent's initial orientation (North)

        // Initialize grid with empty spaces and borders
        initializeGrid(grid);

        // Display initial grid
        displayGrid(grid, agentX, agentY, agentOrientation);

        // Main game loop
        while (true) {
            System.out.print("Enter an instruction for the agent: ");
            String instruction = scanner.next();

            // Check for 'q' to quit
            if (instruction.equals("q")) {
                System.out.println("Exiting program...");
                break;
            }

            // Handle movement or rotation instructions
            if (isInteger(instruction)) {
                int distance = Integer.parseInt(instruction);
                if (!moveAgent(grid, agentX, agentY, agentOrientation, distance)) {
                    System.out.println("Error: Agent cannot move that far.");
                }
            } else if (instruction.equals("-")) {
                agentOrientation = rotateCounterClockwise(agentOrientation);
            } else if (instruction.equals("+")) {
                agentOrientation = rotateClockwise(agentOrientation);
            } else {
                // Invalid input
                System.out.println("Invalid input. Valid options are:");
                System.out.println(" - A number to move forward");
                System.out.println(" - '-' to rotate 90 degrees counter-clockwise");
                System.out.println(" - '+' to rotate 90 degrees clockwise");
                System.out.println(" - 'q' to quit");
                continue; // Skip to the next iteration of the loop
            }

            // Update and display grid
            displayGrid(grid, agentX, agentY, agentOrientation);
        }

        scanner.close();

    }

    // Function to check if a string is an integer
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
// Initializes the grid with dots, borders, and initial agent position
private static void initializeGrid(char[][] grid) {
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid.length; col++) {
            if (row == 0 || row == grid.length - 1) {
                grid[row][col] = '_'; 
            } else if (col == 0 || col == grid.length - 1) {
                grid[row][col] = '|';
            } else {
                grid[row][col] = '.';
            }
        }
    }
    grid[0][0] = '^'; // Initial agent position and orientation
}

// Displays the grid
private static void displayGrid(char[][] grid, int x, int y, char orientation) {
    // Clear the previous grid (if needed)
    for (int i = 0; i < 50; i++) {
        System.out.println(); 
    }

    grid[y][x] = orientation; // Place agent on grid
    for (char[] row : grid) {
        System.out.println(row);
    }
}



}
