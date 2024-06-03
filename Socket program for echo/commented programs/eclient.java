import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class eclient {
    public static void main(String args[]) {
        Socket c = null; // Declaring a Socket object to establish a connection with the server
        String line; // Declaring a String variable to hold the input from the user
        BufferedReader is; // Declaring a BufferedReader to read input from the user
        PrintStream os; // Declaring a PrintStream to send output to the server

        try {
            // Establishing a connection to the server running on localhost at port 8080
            c = new Socket("localhost", 8080);
            
            // Getting the output stream of the socket and wrapping it in a PrintStream
            os = new PrintStream(c.getOutputStream());
            
            // Creating a BufferedReader to read input from the standard input (console)
            is = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("client"); // Printing a message to indicate the client is ready for input
                
                // Reading a line of input from the user
                line = is.readLine();
                
                // Sending the input line to the server
                os.println(line);

                // If the input line is not "exit", read and print the server's response
                if (!line.equals("exit")) {
                    System.out.println("server:" + is.readLine());
                }
            } while (!line.equals("exit")); // Continue the loop until the user inputs "exit"
        } catch (IOException e) {
            // Handling any IOExceptions that occur during the process
            System.out.println(e); // Printing the exception message
        } finally {
            try {
                // Closing the socket connection if it was established
                if (c != null) {
                    c.close();
                }
            } catch (IOException e) {
                // Handling any IOExceptions that occur while closing the socket
                System.out.println("socket closed"); // Printing a message indicating the socket is closed
            }
        }
    }
}
