import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;
    private Socket client;
    private BufferedReader reader;

    public static void main( String[] args ) {
        // The port you are using
        final int port = 12345;
        // Server class object
        Server server = new Server();
       
        try {
            // Start the server and listen for the client
            server.start(port);
            // Stop the server and clean all resources
            server.stop();
        } catch (IOException e) {
            // If something went wrong, log it on console and exit the program
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Start the server to listen for a message from a client
     */
    public void start(int port) throws IOException {
        // Initialize the server
        this.server = new ServerSocket(port);
        // Listen for a client connection
        this.client = this.server.accept();
        // Open the input stream
        this.reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        // Read the message from the client
        String message = this.reader.readLine();
        System.out.println("Client says: " + message);
    }

    /**
     * Clean up the resources
     */
    public void stop() {
        try {
            this.reader.close();
            this.client.close();
            this.server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
