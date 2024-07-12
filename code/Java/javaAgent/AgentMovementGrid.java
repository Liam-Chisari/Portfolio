import java.util.Scanner;

public class AgentMovementGrid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get grid size from the user
        System.out.print("Enter the size of the square grid: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        char[][] grid = new char[size][size];
        int agentX = 0, agentY = 0; // Agent starts at top left (0, 0)
        char agentOrientation = '^'; // Start facing north

        // Initialize grid with empty spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '.';
            }
        }

        while (true) {
            // Display the grid with agent
            displayGrid(grid, agentX, agentY, agentOrientation);

            // Get user input for movement or rotation
            System.out.print("Enter an instruction for the agent: ");
            String input = scanner.nextLine();

            // Check for valid input
            if (input.matches("-?\\d+") || input.equals("+") || input.equals("-") || input.equals("q")) {
                // Process valid input (movement, rotation, or quit)
                if (input.equals("q")) {
                    System.out.println("Exiting program. Goodbye!");
                    break;
                } else if (input.equals("+")) {
                    agentOrientation = rotateClockwise(agentOrientation);
                } else if (input.equals("-")) {
                    agentOrientation = rotateCounterClockwise(agentOrientation);
                } else {
                    int steps = Integer.parseInt(input);
                    if (!moveAgent(grid, agentX, agentY, agentOrientation, steps)) {
                        System.out.println("Error: Movement out of bounds. Try again.");
                        continue; // Skip to the next iteration
                    } else {
                        // Update agent position after a successful move
                        switch (agentOrientation) {
                            case '^': agentY -= steps; break;
                            case '>': agentX += steps; break;
                            case 'v': agentY += steps; break;
                            case '<': agentX -= steps; break;
                        }
                    }
                }
            } else {
                // Invalid input
                System.out.println("Error: Invalid input. Valid inputs are:");
                System.out.println("  - Number (e.g., 5): Move forward the specified number of spaces");
                System.out.println("  - '+': Rotate 90 degrees clockwise");
                System.out.println("  - '-': Rotate 90 degrees counter-clockwise");
                System.out.println("  - 'q': Quit the program");
            }
        }

        scanner.close();
    }

    // ... (The displayGrid, moveAgent, rotateClockwise, and rotateCounterClockwise functions from previous response) 
}
