import javax.swing.*; // Importing the Swing library for GUI components
import java.net.*; // Importing the networking package for socket programming
import java.awt.image.*; // Importing the package for handling image data
import javax.imageio.*; // Importing the package for reading and writing images
import java.io.*; // Importing the package for input and output operations

public class Client {
    public static void main(String args[]) throws Exception {
        Socket soc; // Declaring a Socket object to establish a connection with the server
        BufferedImage img = null; // Declaring a BufferedImage object to hold the image data

        // Establishing a connection to the server running on localhost at port 4000
        soc = new Socket("localhost", 4000);
        System.out.println("Client is running"); // Printing a message to indicate the client is running

        try {
            System.out.println("Reading image from disk"); // Printing a message to indicate the image is being read from the disk

            // Reading the image file "digital_image_processing.jpg" from the disk
            img = ImageIO.read(new File("digital_image_processing.jpg"));

            // Creating a ByteArrayOutputStream to hold the image data in byte array format
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Writing the image data to the ByteArrayOutputStream in JPG format
            ImageIO.write(img, "jpg", baos);
            baos.flush(); // Flushing the stream to ensure all data is written

            // Converting the ByteArrayOutputStream to a byte array
            byte[] bytes = baos.toByteArray();
            baos.close(); // Closing the ByteArrayOutputStream

            System.out.println("Sending image to server."); // Printing a message to indicate the image is being sent to the server

            // Getting the output stream of the socket to send data to the server
            OutputStream out = soc.getOutputStream();

            // Wrapping the output stream in a DataOutputStream to send primitive data types
            DataOutputStream dos = new DataOutputStream(out);

            // Sending the length of the byte array to the server
            dos.writeInt(bytes.length);

            // Sending the image byte array to the server
            dos.write(bytes, 0, bytes.length);

            dos.close(); // Closing the DataOutputStream
            out.close(); // Closing the OutputStream
        } catch (Exception e) {
            // Handling any exceptions that occur during the process
            System.out.println("Exception: " + e.getMessage()); // Printing the exception message
            soc.close(); // Closing the socket
        }

        // Closing the socket connection
        soc.close();
    }
}
