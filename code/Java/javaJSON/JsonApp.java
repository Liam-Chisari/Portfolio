import java.util.HashMap;

public class JsonApp {
    public static void main(String[] args) {


        JsonHandler jsonHandler = new JsonHandler();

        Order order1 = new Order();
        //This should be in the controller.
        jsonHandler.loadDataFromFile(order1, "example_data.json");



    }


}
