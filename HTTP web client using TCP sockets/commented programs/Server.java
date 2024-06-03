import java.net.*; // Importing the networking package for socket programming
import java.io.*; // Importing the package for input and output operations
import java.awt.image.*; // Importing the package for handling image data
import javax.imageio.*; // Importing the package for reading and writing images
import javax.swing.*; // Importing the Swing library for GUI components
import java.io.ByteArrayInputStream; // Importing the package for reading input data from a byte array

class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket server = null; // Declaring a ServerSocket object to listen for incoming connections
        Socket socket; // Declaring a Socket object to handle the connection with the client
        
        // Initializing the ServerSocket to listen on port 4000
        server = new ServerSocket(4000);
        System.out.println("Server Waiting for image"); // Printing a message to indicate the server is waiting for an image

        // Accepting a connection from a client
        socket = server.accept();
        System.out.println("Client connected."); // Printing a message to indicate a client has connected

        // Getting the input stream of the socket to receive data from the client
        InputStream in = socket.getInputStream();

        // Wrapping the input stream in a DataInputStream to read primitive data types
        DataInputStream dis = new DataInputStream(in);

        // Reading the length of the incoming byte array (image data)
        int len = dis.readInt();
        System.out.println("Image Size: " + len / 1024 + "KB"); // Printing the size of the image in kilobytes

        // Creating a byte array to hold the incoming image data
        byte[] data = new byte[len];

        // Reading the full byte array from the input stream
        dis.readFully(data);
        dis.close(); // Closing the DataInputStream
        in.close(); // Closing the InputStream

        // Creating an InputStream from the byte array to read the image data
        InputStream ian = new ByteArrayInputStream(data);

        // Reading the image from the InputStream and storing it in a BufferedImage object
        BufferedImage bImage = ImageIO.read(ian);

        // Creating a JFrame to display the received image
        JFrame f = new JFrame("Server");

        // Creating an ImageIcon from the BufferedImage
        ImageIcon icon = new ImageIcon(bImage);

        // Creating a JLabel and setting its icon to the ImageIcon
        JLabel l = new JLabel();
        l.setIcon(icon);

        // Adding the JLabel to the JFrame
        f.add(l);

        // Packing the JFrame to fit the components
        f.pack();

        // Making the JFrame visible
        f.setVisible(true);
    }
}
