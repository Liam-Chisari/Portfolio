 import java.util.Scanner;

 public class GridDisplay {
     private int size;
     private char[][] grid;
     Scanner scanner = new Scanner(System.in);

     public GridDisplay(int n) {
         if (n < 3) {
            System.out.println("Minimum side length is 3. Setting side length to 3.");
            this.size = 3;
         } 
         else {
             this.size = n;
         }
         initGrid();
     }

     private void initGrid() {
         grid = new char[size][size];
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 grid[i][j] = '.';
             }
         }
     }

      public void displayGrid() {
         for (int i = 0; i < size; i++) {
             for (int j = 0; j < size; j++) {
                 System.out.print(grid[i][j]);
                 if (j != size - 1) {
                     System.out.print("_");
                 }
             }
             System.out.print("\n");
             if (i != size - 1) {
                 for (int k = 0; k < size; k++) {
                     System.out.print("|");
                 }
                 System.out.print("\n");
             }
         }
     }

     public void placeCharacterAt(int x, int y, char c) {
         if (x >= 0 && x < size && y >= 0 && y < size) {
             grid[x][y] = c;
         }
     }

     public char getCharacterAt(int x, int y) {
         if (x >= 0 && x < size && y >= 0 && y < size) {
             return grid[x][y];
         }
         return '_';
     }

     public boolean validCoordinates(int x, int y) {
         return (x >= 0 && x < size && y >= 0 && y < size);
     }public void run() {
         int x, y;
         boolean quit = false;
         while (!quit) {
             displayGrid();
             System.out.print("Enter coordinates in the format x,y: ");
             String coords = scanner.nextLine();
             String[] parts = coords.split(",");
             if (parts.length != 2) {
                 System.out.println("Invalid coordinates. Please enter again.");
                 continue;
             }
             try {
                 x = Integer.parseInt(parts[0]);
                 y = Integer.parseInt(parts[1]);
             } catch (NumberFormatException e) {
                 System.out.println("Invalid coordinates. Please enter again.");
                 continue;
             }
                 if (!validCoordinates(x, y)) {
                     System.out.println("Invalid coordinates. Please enter again.");
                     continue;
                 }
             System.out.print("Enter a character: ");
             String character = scanner.nextLine();
             if (character.length() != 1) {
                 System.out.println("Invalid character. Please enter again or 'q' to quit.");  continue;
             }
             char c = character.charAt(0);
             if (c == 'q') {
                System.out.print("Are you sure you want to quit? (y/n): ");
                 String confirm = scanner.nextLine();
                 if (confirm.equals("y")) {
                    quit = true;
                    break;
                 } else {
                    continue;
                 }
             }

             if (getCharacterAt(x, y) == c) {
                System.out.println("Character already exists in that location. Please try again.");
             }  else {
                 placeCharacterAt(x, y, c);
             }
         }
         scanner.close();
         System.out.println("Goodbye!");
     }
 
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter the length of the grid side: ");
         int n = scanner.nextInt();
         scanner.nextLine(); // consume newline
         GridDisplay gridDisplay = new GridDisplay(n);
         gridDisplay.run();
     }
 }
