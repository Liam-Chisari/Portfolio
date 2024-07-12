import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private Socket socket;
    private PrintWriter writer;

    public static void main(String[] args) {
        // Host and the port you are connecting to
        final String host = "localhost";
        final int port = 12345;

        Client client = null;
        try {
            // Initialize the client to connect to the server
            client = new Client(host, port);
        } catch (IOException e) {
            // If something went wrong, exit the program
            e.printStackTrace();
            System.exit(-1);
        }
        // Send the message to the server
        client.sendMessage("Hello server");
        // After sending, close the connection
        client.stopConnection();
    }

    public Client(String host, int port) throws IOException {
        this.socket = new Socket("localhost", 12345);
        this.writer = new PrintWriter(this.socket.getOutputStream(), true);
    }

    /**
     * Sends the message to the server
     */
    public void sendMessage(String message) {
        this.writer.println(message);
    }

    /**
     * Closes the connection with the server and clean up the resources
     */
    public void stopConnection() {
        try {
            this.writer.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
