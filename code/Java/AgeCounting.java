import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class AgeCounting {

    public static void main(String[] args) {
        try {
            String url = "https://coderbyte.com/api/challenges/json/age-counting";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Optional default is GET
            con.setRequestMethod("GET");

            // Add request header
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("Sending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println(response.toString());

            // Parse the response
            JSONObject myResponse = new JSONObject(response.toString());
            String data = myResponse.getString("data");

            // Split the data string by comma
            String[] items = data.split(", ");

            int count = 0;
            for (String item : items) {
                // Split each item by equal sign
                String[] keyValue = item.split("=");

                // Check if the key is 'age' and if the age is >= 50
                if ("age".equals(keyValue[0]) && Integer.parseInt(keyValue[1]) >= 50) {
                    count++;
                }
            }

            // Print the count
            System.out.println("Number of items with age >= 50: " + count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
