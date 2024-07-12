import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TowerOfHanoi {
    private final int numDiscs = 3;
    private List<List<Integer>> towers;
    private int moves = 0;

    public TowerOfHanoi() {
        towers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            towers.add(new ArrayList<>());
        }

        // Initialize the towers with discs
        for (int i = numDiscs; i > 0; i--) {
            towers.get(0).add(i);
        }
    }

    public void displayTowers() {
        System.out.println("\n======================");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 4 - towers.get(i + 1).size(); j++) {
                System.out.print("  ");
            }
            for (int j : towers.get(i + 1)) {
                int numDashes = j * 2 - 1;
                for (int k = 0; k < numDashes; k++) {
                    System.out.print("-");
                }
                System.out.print(" ");
            }
            System.out.print("|\n");
        }
        System.out.println("======================\n");
    }
    public void moveDisc(int sourceTower, int destinationTower) {

        if (isValidMove(sourceTower, destinationTower)) {
            int topDisc = towers.get(sourceTower).remove(towers.get(sourceTower).size() - 1);
            towers.get(destinationTower).add(topDisc);
            moves++;
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }

    public boolean isValidMove(int sourceTower, int destinationTower) {
        if (sourceTower < 1 || sourceTower > 3 || destinationTower < 1 || destinationTower > 3) {
            System.out.println("Invalid tower numbers. Please try again.");
            return false;
        }

        if (towers.get(sourceTower).isEmpty()) {
            System.out.println("The source tower is empty. Please try again.");
            return false;
        }

        int topDisc = towers.get(sourceTower).get(towers.get(sourceTower).size() - 1);

        if (towers.get(destinationTower).isEmpty()) {
            return true;
        }

        int topDiscDestination = towers.get(destinationTower).get(towers.get(destinationTower).size() - 1);
        return topDisc < topDiscDestination;
    }
    public void play() {
        Scanner scanner = new Scanner(System.in);
        displayTowers();

        while (!isSolved()) {
            System.out.print("Enter your move (source tower, destination tower): ");
            String[] move = scanner.nextLine().split(",");
            if (move.length != 2) {
                System.out.println("Invalid move. Please try again.");
                continue;
            }
            try {
                int sourceTower = Integer.parseInt(move[0].trim());
                int destinationTower = Integer.parseInt(move[1].trim());
                moveDisc(sourceTower, destinationTower);
                displayTowers();
            } catch (NumberFormatException e) {
                System.out.println("Invalid move. Please try again.");
            }
        }
        System.out.println("Congratulations! You won in " + moves + " moves.");
        scanner.close();
    }
    public boolean isSolved() {
       return towers.get(3).size() == numDiscs;
    }
    public static void main(String[] args) {
        new TowerOfHanoi().play();  
    } 
}
