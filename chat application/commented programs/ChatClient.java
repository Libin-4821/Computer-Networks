import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class ChatClient {
    public static void main(String[] args) throws Exception {
        // Creating a Socket to connect to the server running on localhost at port 1234
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to the server!"); // Printing a message to indicate the client is connected to the server

        // Creating a BufferedReader to read input from the server
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Creating a BufferedWriter to send output to the server
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Creating a BufferedReader to read input from the client's console
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        String message; // Declaring a String variable to hold messages
        while (true) {
            // Prompting the client to enter a message
            System.out.print("Client: ");

            // Reading the client's message from the console
            message = clientInput.readLine();

            // Sending the client's message to the server
            writer.write(message + "\n");
            writer.flush(); // Flushing the writer to ensure the message is sent

            // Checking if the message is "bye" to break the loop and close the connection
            if (message.equals("bye")) {
                break;
            }

            // Reading the server's response
            message = reader.readLine();

            // Printing the server's response to the client's console
            System.out.println("Server: " + message);
        }

        // Closing the socket connection
        socket.close();
        System.out.println("Connection closed."); // Printing a message to indicate the connection is closed
    }
}
