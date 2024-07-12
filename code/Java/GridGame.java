import java.util.Scanner;
import java.util.Arrays;
import java.util.InputMismatchException;

public class GridGame {
    private char[][] grid;
    private int size;

    public GridGame(int size) {
        if(size < 3) {
            throw new IllegalArgumentException("Grid size must be at least 3.");
        }
        this.size = size;
        grid = new char[size][size];
        for(char[] row : grid) {
            Arrays.fill(row, '.');
        }
    }

    public void displayGrid() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void placeCharacter(int x, int y, char character) {
        if(x < 0 || x >= size || y < 0 || y >= size) {
            throw new IllegalArgumentException("Invalid coordinates. Coordinates must be between 1 and " + (size) + ".");
        }
        if(grid[y][x] == character) {
            System.out.println("Position is already occupied with this" +
                    " character. Please choose a different position or character.");
        } else {
            grid[y][x] = character;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 0;

        while(size == 0) {
            System.out.print("Enter the size of the grid: ");
            try {
                size = scanner.nextInt();
            }
            catch (InputMismatchException ex) {
                System.out.println("Invalid input, please enter a single number.");
            }
            scanner.nextLine();
            if(size > 80) {
                System.out.println("Size too large. Enter a number <= 80.");
                size = 0;
            }

        }

        GridGame gridGame = new GridGame(size);

        while(true) {
            gridGame.displayGrid();

            System.out.print("Enter coordinates in the format of x,y: ");
            String coordinatesInput = scanner.nextLine();
            String[] coordinates = coordinatesInput.split(",");
            if(coordinates.length != 2) {
                System.out.println("Invalid coordinates. Please enter coordinates in the format of x,y.");
                continue;
            }

            int x;
            int y;
            try {
                x = Integer.parseInt(coordinates[0].trim());
                y = Integer.parseInt(coordinates[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid coordinates. Please enter valid integer coordinates.");
                continue;
            }

            System.out.print("Enter a character: ");
            String characterInput = scanner.nextLine();
            if(characterInput.length() != 1) {
                System.out.println("Invalid character. Please enter a single character.");
                continue;
            }

            char character = characterInput.charAt(0);
            if(character == 'q') {
                System.out.print("Are you sure you want to quit? (y/n): ");
                String confirmInput = scanner.nextLine();
                if(confirmInput.equalsIgnoreCase("y")) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    continue;
                }
            }

            try {
                gridGame.placeCharacter(x, y, character);
            }
            catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


        }

        scanner.close();
    }
}
