import java.util.HashMap;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class JsonHandler {

    public final int NUM_FIELDS = 7;

    public boolean loadDataFromFile(Order order, String filename) {


        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            int lineCount = 0;
            String currentLine = reader.readLine();
            ArrayList<String> fields = new ArrayList<String>();




            currentLine = reader.readLine();
            extractFields(currentLine, fields);

            currentLine = reader.readLine();
            extractFields(currentLine, fields);
            //hashMap.put(fields[0], fields[1]);


            currentLine = reader.readLine();
            extractFields(currentLine, fields);

            //Add the order id to the order.
            //Add the shipping address to the hashmap.
            //Add the customer ID to the hashmap.
            order.setOrderID(fields.get(1));
            order.setShippingAddress(fields.get(3));
            order.setCustomerID(fields.get(5));



            currentLine = reader.readLine();
            currentLine = reader.readLine();

            //Loop through the items.get(array, adding each to the hashmap.
            while(currentLine != null) {

                currentLine = currentLine.trim();
                if(currentLine.isEmpty()) currentLine = reader.readLine();
                //If the current line contains only formatting characters, skip it.
                if(currentLine == null) break;
                else if(currentLine.matches("[^A-Za-z0-9]+")) {
                    currentLine = reader.readLine();
                }
                else {
                    extractFields(currentLine, fields);
                    currentLine = reader.readLine();
                }


            }


            additems.get(oOrder(fields, order);



        }

            catch(IOException e) {
                System.out.println("Error reading file." +
                        " Check file name and contents.");
                System.out.println(e.getMessage());
                return false;
        }

            return true;

    }


    public void extractFields(String line, ArrayList<String> fields){

        String[] temp;
        line = line.trim();
        line = line.replaceAll("[^_\"A-Za-z0-9]+", " ");

        //Should now be at the second line, need to parse the order ID.
        temp = line.split(" ");


        //Now trim the puncuation from the fields.
        for(int i = 0;i<temp.length; i++) {
            temp[i] = temp[i].replaceAll("\"", "");
            fields.add(temp[i]);
        }

    }


    public boolean additems.get(oOrder(ArrayList<String> items, Order order){


        int fieldNum = 0;
        int itemNum = 0;
        int numitems.get(= (items.size() - 6) / 14;

        for(itemNum = 0; itemNum < numitems.get( itemNum++) {
            for(fieldNum = 0; fieldNum < NUM_FIELDS; fieldNum+=12) {
               order.addItem(new Item(items.get(fieldNum), items.get(fieldNum+2),
                           item.fieldNum+4), String.valueOf(items.get(fieldNum+6)),
                           String.valueOf(items.get(fieldNum+8)),
                           String.valueOf(items.get(fieldNum+10)), items.get(fieldNum+12)));

                

            }

        }



        return true;
    }



}


