import java.util.Scanner;

public class AgentMovement {
        static int agentRow = 0;
        static int agentCol = 0;
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the square grid: ");
        int gridSize = scanner.nextInt();
        char[][] grid = new char[gridSize][gridSize]; 


        char agentOrientation = '^'; // Start facing north

        initialiseGrid(grid);
        displayGrid(grid, agentRow, agentCol, agentOrientation);

        boolean running = true;
        while (running) {
            System.out.print("Enter an instruction for the agent: ");
            String instruction = scanner.next();

            if (instruction.equals("q")) {
                System.out.println("Exiting program...");
                running = false;
            } else if (instruction.equals("+")) {
                agentOrientation = rotateClockwise(agentOrientation);
            } else if (instruction.equals("-")) {
                agentOrientation = rotateCounterClockwise(agentOrientation);
            } else {
                try {
                    int steps = Integer.parseInt(instruction);

                    moveAgent(grid, agentRow, agentCol, agentOrientation, steps, gridSize);
                } catch (NumberFormatException e) {
                    displayErrorMessage();
                }
            }

            if (running) { // Display grid only if the program is still running
                displayGrid(grid, agentRow, agentCol, agentOrientation);
            }
        }
    }


    static void initialiseGrid(char[][] grid) {


        // Initialize grid with empty spaces
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = '.';
            }
        }

    }

    static void displayGrid(char[][] grid, Integer agentRow, Integer agentCol, char agentOrientation) {

        grid[agentCol][agentRow] = agentOrientation; // Place the agent


        // Print the grid
        System.out.println("+" + "-".repeat(grid.length * 2 - 1) + "+"); // Top border
        for (int i = 0; i < grid.length; i++) {
            System.out.print("|");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("+" + "-".repeat(grid.length * 2 - 1) + "+"); // Bottom border
    }

    static char rotateClockwise(char orientation) {
        switch (orientation) {
            case '^': return '>';
            case '>': return 'v';
            case 'v': return '<';
            case '<': return '^';
            default: return orientation; // Shouldn't happen
        }
    }

    static char rotateCounterClockwise(char orientation) {
        switch (orientation) {
            case '^': return '<';
            case '>': return '^';
            case 'v': return '>';
            case '<': return 'v';
            default: return orientation; // Shouldn't happen
        }
    }

static void moveAgent(char[][] grid, int agentRow, int agentCol, char orientation, int steps, int gridSize) {
    int oldRow = agentRow;
    int oldCol = agentCol;

    switch (orientation) {
        case '^': agentRow -= steps; break;
        case 'v': agentRow += steps; break;
        case '>': agentCol += steps; break;
        case '<': agentCol -= steps; break;
    }



    // Check for bounds
    if (agentRow < 0 || agentRow >= gridSize || agentCol < 0 || agentCol >= gridSize) {
        agentRow = oldRow; // Reset position
        agentCol = oldCol;
    }
     else {
        grid[oldCol][oldRow] = '.'; // Clear old position
        grid[agentCol][agentRow] = orientation;
    }
}

static void displayErrorMessage() {
    // ...
} 


}
