import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;


public class DictionaryApp {

    //Instantiate file handler objects here.

    //Dictionary file handler.
    static FileHelper fileHelper = new FileHelper();
    
    //Text input file handler.
    static DictionarySearch dictionarySearch = new DictionarySearch();


    public static void main(String[] args) {


        HashMap<String, String> dictionaryMap = new HashMap<String, String>();  
        Scanner scanner = new Scanner(System.in);
        int numErrors = 0;
       
        System.out.print("Please specify the dictionary file:");
        //Prompt for the name of the dictionary file
        String dictionaryFilename = scanner.nextLine();

        //Encase the call to fileHelper.loadDictionary in a try block
        try {
            fileHelper.loadDictionary(dictionaryMap, dictionaryFilename);
        }
        catch(IOException ex) {
            System.out.println("Failed to load the dictionary file. Check the file path.");
        }

        System.out.print("Please specify the file to be checked:");
        String inputFilename = scanner.nextLine();

        try {
            numErrors = dictionarySearch.countErrors(dictionaryMap, inputFilename);
        }
        catch (IOException ex) {
            System.out.println("Failed to load the input file. Check the file path.");
        }

        System.out.printf("The file %s contains %d errors.%n", inputFilename, numErrors);

            



    }

}


