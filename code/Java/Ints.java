import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
public class Ints {
    public static void main(String[] args) throws IOException {

        int i = 0;

        File outputFile = new File("ints.csv");
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        for(i = 0; i< 1000; i++) {
            output.write(String.valueOf(i));
            output.write(",");
            output.newLine();

        }

        output.close();




    }


}
