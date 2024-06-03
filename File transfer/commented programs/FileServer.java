import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class FileServer {
    public static void main(String[] args) throws Exception {
        // Creating a ServerSocket to listen for incoming connections on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and waiting for a client..."); // Printing a message to indicate the server is waiting for a client

        // Accepting a connection from a client
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!"); // Printing a message to indicate a client has connected

        // Getting the input stream of the socket to receive data from the client
        InputStream is = socket.getInputStream();

        // Creating a FileOutputStream to write the received data to a file
        FileOutputStream fos = new FileOutputStream("received.txt");

        // Creating a BufferedOutputStream to buffer the file output stream
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        
        // Creating a byte array to hold the incoming data
        byte[] buffer = new byte[1024];
        int bytesRead;

        // Reading data from the input stream into the buffer and writing it to the file output stream
        while ((bytesRead = is.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush(); // Flushing the output stream to ensure all data is written

        // Closing the BufferedOutputStream
        bos.close();

        // Closing the socket connection
        socket.close();
        
        System.out.println("File received from the client!"); // Printing a message to indicate the file has been received
    }
}
