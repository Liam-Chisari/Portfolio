import java.util.Scanner;

public class AgentInGrid {
    private static final char AGENT = '@';
    private static final char EMPTY_SPACE = '.';
    private static final char VERTICAL_BORDER = '|';
    private static final char HORIZONTAL_BORDER = '_';

    private int gridSize;
    private int agentX;
    private int agentY;
    private char agentOrientation;

    private char[][] grid;

    public AgentInGrid(int gridSize) {
        this.gridSize = gridSize;
        this.agentX = 0;
        this.agentY = 0;
        this.agentOrientation = '^';

        this.grid = new char[gridSize][gridSize];
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == 0 || i == gridSize - 1 || j == 0 || j == gridSize - 1) {
                    grid[i][j] = HORIZONTAL_BORDER;
                } else {
                    grid[i][j] = EMPTY_SPACE;
                }
            }
        }

        grid[agentX][agentY] = AGENT;
    }

    public void displayGrid() {
        System.out.println("------------------");
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("------------------");
    }

    public void moveAgent(int spaces) {
        int newX = agentX;
        int newY = agentY;

        switch (agentOrientation) {
            case '^':
                newY -= spaces;
                break;
            case '>':
                newX += spaces;
                break;
            case 'v':
                newY += spaces;
                break;
            case '<':
                newX -= spaces;
                break;
        }

        if (isValidMove(newX, newY)) {
            grid[agentX][agentY] = EMPTY_SPACE;
            agentX = newX;
            agentY = newY;
            grid[agentX][agentY] = AGENT;
        } else {
            System.out.println("Invalid move. The agent would go out of the grid.");
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public void rotateAgent(char rotationDirection) {
        switch (rotationDirection) {
            case '-':
                switch (agentOrientation) {
                    case '^':
                        agentOrientation = '<';
                        break;
                    case '<':
                        agentOrientation = 'v';
                        break;
                    case 'v':
                        agentOrientation = '>';
                        break;
                    case '>':
                        agentOrientation = '^';
                        break;
                }
                break;
            case '+':
                switch (agentOrientation) {
                    case '^':
                        agentOrientation = '>';
                        break;
                    case '>':
                        agentOrientation = 'v';
                        break;
                    case 'v':
                        agentOrientation = '<';
                        break;
                    case '<':
                        agentOrientation = '^';
                        break;
                }
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the grid: ");
        int gridSize = scanner.nextInt();

        AgentInGrid agentInGrid = new AgentInGrid(gridSize);
        agentInGrid.displayGrid();

        while (true) {
            System.out.print("Enter an instruction for the agent (integer for movement, '-' for counter-clockwise rotation, '+' for clockwise rotation, 'q' to quit): ");
            String input = scanner.next();

            if (input.equals("q")) {
                System.out.println("Exiting the program...");
                break;
            } else if (input.equals("-") || input.equals("+")) {
                agentInGrid.rotateAgent(input.charAt(0));
            } else {
                try {
                    int spaces = Integer.parseInt(input);
                    agentInGrid.moveAgent(spaces);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid instruction.");
                }
            }

            agentInGrid.displayGrid();
        }

        scanner.close();
    }
}
