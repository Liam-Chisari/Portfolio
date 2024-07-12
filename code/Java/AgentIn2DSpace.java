import java.util.Scanner;

public class AgentIn2DSpace {
    private static final char AGENT_NORTH = '^';
    private static final char AGENT_EAST = '>';
    private static final char AGENT_SOUTH = 'v';
    private static final char AGENT_WEST = '<';
    private static final char EMPTY_SPACE = '.';
    private static final char VERTICAL_BORDER = '|';
    private static final char HORIZONTAL_BORDER = '_';

    private int gridSize;
    private int agentX;
    private int agentY;
    private char agentOrientation;

    public AgentIn2DSpace(int gridSize) {
        this.gridSize = gridSize;
        this.agentX = 0;
        this.agentY = 0;
        this.agentOrientation = AGENT_NORTH;
    }

    public void displayGrid() {
        System.out.println(HORIZONTAL_BORDER);
        for (int i = 0; i < gridSize; i++) {
            System.out.print(VERTICAL_BORDER);
            for (int j = 0; j < gridSize; j++) {
                if (i == agentY && j == agentX) {
                    System.out.print(agentOrientation);
                } else {
                    System.out.print(EMPTY_SPACE);
                }
            }
            System.out.print(VERTICAL_BORDER);
            System.out.println();
        }
        System.out.println(HORIZONTAL_BORDER);
    }

    public void rotateAgentCounterClockwise() {
        switch (agentOrientation) {
            case AGENT_NORTH:
                agentOrientation = AGENT_WEST;
                break;
            case AGENT_EAST:
                agentOrientation = AGENT_NORTH;
                break;
            case AGENT_SOUTH:
                agentOrientation = AGENT_EAST;
                break;
            case AGENT_WEST:
                agentOrientation = AGENT_SOUTH;
                break;
        }
    }

    public void rotateAgentClockwise() {
        switch (agentOrientation) {
            case AGENT_NORTH:
                agentOrientation = AGENT_EAST;
                break;
            case AGENT_EAST:
                agentOrientation = AGENT_SOUTH;
                break;
            case AGENT_SOUTH:
                agentOrientation = AGENT_WEST;
                break;
            case AGENT_WEST:
                agentOrientation = AGENT_NORTH;
                break;
        }
    }

    public void moveAgent(int spaces) {
        int deltaX = 0;
        int deltaY = 0;
        switch (agentOrientation) {
            case AGENT_NORTH:
                deltaY = -1;
                break;
            case AGENT_EAST:
                deltaX = 1;
                break;
            case AGENT_SOUTH:
                deltaY = 1;
                break;
            case AGENT_WEST:
                deltaX = -1;
                break;
        }

        int newX = agentX + (deltaX * spaces);
        int newY = agentY + (deltaY * spaces);
        if (isValidPosition(newX, newY)) {
            agentX = newX;
            agentY = newY;
        } else {
            System.out.println("Invalid move. There is not enough space on the grid.");
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the grid: ");
        int gridSize = scanner.nextInt();
        AgentIn2DSpace agent = new AgentIn2DSpace(gridSize);

        while (true) {
            agent.displayGrid();
            System.out.print("Enter an instruction for the agent: ");
            String instruction = scanner.next();

            if (instruction.equalsIgnoreCase("q")) {
                System.out.println("Exiting the program...");
                break;
            } else if (instruction.equals("-")) {
                agent.rotateAgentCounterClockwise();
            } else if (instruction.equals("+")) {
                agent.rotateAgentClockwise();
            } else if (instruction.matches("\\d+")) {
                int spaces = Integer.parseInt(instruction);
                agent.moveAgent(spaces);
            } else {
                System.out.println("Invalid input. Please enter an integer for movement, '-' for counter-clockwise rotation, '+' for clockwise rotation, or 'q' to exit.");
            }
        }

        scanner.close();
    }
}
