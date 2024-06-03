import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class ChatServer {
    public static void main(String[] args) throws Exception {
        // Creating a ServerSocket to listen for incoming connections on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and waiting for a client..."); // Printing a message to indicate the server is waiting for a client

        // Accepting a connection from a client
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!"); // Printing a message to indicate a client has connected

        // Creating a BufferedReader to read input from the client
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Creating a BufferedWriter to send output to the client
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Creating a BufferedReader to read input from the server's console
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

        String message; // Declaring a String variable to hold messages
        while (true) {
            // Reading a message from the client
            message = reader.readLine();
            
            // Checking if the message is "bye" to break the loop and close the connection
            if (message.equals("bye")) {
                break;
            }

            // Printing the client's message to the server's console
            System.out.println("Client: " + message);

            // Prompting the server to enter a response
            System.out.print("Server: ");
            
            // Reading the server's response from the console
            message = serverInput.readLine();
            
            // Sending the server's response to the client
            writer.write(message + "\n");
            writer.flush(); // Flushing the writer to ensure the message is sent
        }

        // Closing the socket connection
        socket.close();
        System.out.println("Connection closed."); // Printing a message to indicate the connection is closed
    }
}
