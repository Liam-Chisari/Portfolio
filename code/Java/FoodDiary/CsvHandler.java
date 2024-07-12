import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

class CsvHandler {


    public boolean loadFromCsv(ArrayList<String[]> rows, String filename) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            //Read file here.
            String currentLine = reader.readLine();
            String[] columns;
            int i = 0;
            while(currentLine != null) {
                columns = currentLine.split(",");

                rows.add(columns);

                currentLine = reader.readLine();
            }
        }
        catch(IOException e) {
            System.out.println("Error reading file." +
                    " Check file name and contents.");
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }



}
