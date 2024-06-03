import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class eserver {
    public static void main(String args[]) throws IOException {
        ServerSocket s = null; // Declaring a ServerSocket object to listen for incoming connections
        String line; // Declaring a String variable to hold the input from the client
        BufferedReader is; // Declaring a BufferedReader to read input from the client
        PrintStream ps; // Declaring a PrintStream to send output to the client
        Socket c = null; // Declaring a Socket object to handle the connection with the client

        try {
            // Initializing the ServerSocket to listen on port 8080
            s = new ServerSocket(8080);
        } catch (IOException e) {
            // Handling any IOException that occurs while initializing the ServerSocket
            System.out.println(e); // Printing the exception message
        }

        try {
            // Accepting a connection from a client
            c = s.accept();

            // Getting the input stream of the socket and wrapping it in a BufferedReader
            is = new BufferedReader(new InputStreamReader(c.getInputStream()));

            // Getting the output stream of the socket and wrapping it in a PrintStream
            ps = new PrintStream(c.getOutputStream());

            while (true) {
                // Reading a line of input from the client
                line = is.readLine();

                // Printing a message to indicate a message is received and being sent back to the client
                System.out.println("msg received and sent back to client");

                // Sending the received line back to the client
                ps.println(line);
            }
        } catch (IOException e) {
            // Handling any IOException that occurs during the process
            System.out.println(e); // Printing the exception message
        } finally {
            try {
                // Closing the client socket if it was established
                if (c != null) {
                    c.close();
                }

                // Closing the server socket if it was established
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                // Handling any IOException that occurs while closing the sockets
                System.out.println("socket closed"); // Printing a message indicating the socket is closed
            }
        }
    }
}
