import java.util.Scanner;

public class SnakeEat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the size of the square board: ");
        int size = scanner.nextInt();
        scanner.close();

        int maxEats = calculateMaxEats(size);
        System.out.println("The snake can eat a maximum of " + maxEats + " times.");
    }

    private static int calculateMaxEats(int size) {
        // Calculate the square root of the board size, rounded down to the nearest integer
        int sqrtSize = (int) Math.sqrt(size);

        // The snake can eat up to the number of cells along the diagonal of the board
        return sqrtSize - 1;
    }
}

