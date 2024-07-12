import java.util.Scanner;
class DiaryView {

    private Scanner scanner = new Scanner(System.in);


    public int showMainMenu(Menu mainMenu) {

       int choice;


        System.out.println(mainMenu.toString());
        choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

       return choice;

   }


    public String getString(String prompt) {

       System.out.println(prompt);

       return scanner.nextLine();
   }

    public int getInt(String prompt) {

       int tempInt = -1;
       System.out.println(prompt);

       tempInt = scanner.nextInt();
       scanner.nextLine();

       return tempInt;
   }

   public double getDouble(String prompt) {

       System.out.println(prompt);

       double choice = scanner.nextDouble();
       scanner.nextLine();
       return choice;

   }

    public void print(String string) {
        System.out.println(string);
    }


    public void clearScanner() {
        scanner.next();
    }


}
