import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class FileClient {
    public static void main(String[] args) throws Exception {
        // Creating a Socket to connect to the server running on localhost at port 1234
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to the server!"); // Printing a message to indicate the client is connected to the server

        // Creating a File object to represent the file to be sent
        File file = new File("example.txt");

        // Creating a FileInputStream to read the file
        FileInputStream fis = new FileInputStream(file);

        // Creating a BufferedInputStream to buffer the file input stream
        BufferedInputStream bis = new BufferedInputStream(fis);

        // Getting the output stream of the socket to send data to the server
        OutputStream os = socket.getOutputStream();
        
        // Creating a byte array to hold the file data
        byte[] buffer = new byte[(int) file.length()];

        // Reading the file data into the byte array
        bis.read(buffer, 0, buffer.length);

        // Writing the byte array to the output stream to send the file data to the server
        os.write(buffer, 0, buffer.length);
        os.flush(); // Flushing the output stream to ensure all data is sent

        // Closing the BufferedInputStream
        bis.close();

        // Closing the socket connection
        socket.close();
        
        System.out.println("File sent to the server!"); // Printing a message to indicate the file has been sent
    }
}
