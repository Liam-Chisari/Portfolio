public class Replace {

    public static void main(String[] args) {


        String testString = "{\"id\": \"1002\",\"name\": \"item_name\",\"description\": \"item_description\",\"price\": \"item_price\",\"quantity\": \"item_quantity\",\"discount\": \"item_discount\",\"category\": \"item_category\"";


    testString = testString.replaceAll("\\W", " ");
    String testString2 = "{";
    String testString3 = "\n";
    String testString4 = "},";
    String testString5 = "\"id\": \"item_id\"";
    System.out.println(testString);
    System.out.printf("2:%b 3:%b 4:%b 5:%b \n", testString2.matches("\\W"), 
            testString3.matches("\\W"), testString4.matches("[^A-Za-z0-9]+"),
            testString5.matches("[^A-Za-z0-9]+"));


    }


}
