import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class StringExtractor {

    private final static ArrayList<String[]> rows = new ArrayList<String[]>();
    private final static CsvHandler csvHandler = new CsvHandler();
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String filename;
        int accessRow = -1;
        int accessColumn = -1;
        boolean readSuccess = false;

        while(!readSuccess) {
            System.out.print("Please enter the name of the input file:");
            filename = scanner.nextLine();
            readSuccess = csvHandler.loadFromCsv(rows, filename);
        }

        //rows should be populated by this point.

        System.out.print("Enter a row to access:");
        accessRow = scanner.nextInt();
        //Consume the newline character.
        scanner.nextLine();


        System.out.print("Enter a column to access:");
        accessColumn = scanner.nextInt();

        if(accessRow >= 0 && accessRow < rows.size()) {

            if(accessColumn >= 0 && accessColumn < rows.get(accessRow).length) {

                System.out.printf("String at position %d, %d is:%s%n",
                        accessRow, accessColumn,
                        rows.get(accessRow)[accessColumn]);
                return;

            }
            System.out.println("Invalid column!");
            return;

        }

        System.out.println("Invalid row!");



    }


}
