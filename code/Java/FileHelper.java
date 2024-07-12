import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class FileHelper {

    public boolean loadDictionary(HashMap<String, String> dictionaryMap,
    String dictionaryFilename) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(dictionaryFilename));

        String currentLine = reader.readLine();

        while(currentLine != null) {

            dictionaryMap.put(currentLine.toLowerCase(), null);
            currentLine = reader.readLine();
        }


        return true;


    }






}
