import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;



public class DictionarySearch {

    public int countErrors(HashMap<String, String> dictionary, String textFilename)
    throws IOException {



        int numSpellingErrors = 0;
        BufferedReader reader = new BufferedReader(new FileReader(textFilename));

        String currentLine = reader.readLine();

        String[] words = currentLine.split(" ,.?!");

        for(int i = 0; i < words.length; i++) {

            if(!dictionary.containsKey(words[i].toLowerCase())) {
                numSpellingErrors++;
            }

        }


        return numSpellingErrors;
    }

}
