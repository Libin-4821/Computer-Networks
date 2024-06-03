import java.io.*;
import java.net.*;
import java.util.*;

class Clientrarp12 {
    public static void main(String args[]) {
        try {
            // Create a DatagramSocket for the client
            DatagramSocket client = new DatagramSocket();
            // Get the InetAddress for localhost (127.0.0.1)
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            // Byte arrays to hold data for sending and receiving
            byte[] sendbyte = new byte[1024];
            byte[] receivebyte = new byte[1024];
            // BufferedReader for user input
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // Prompt user to enter the Physical address (MAC)
            System.out.println("Enter the Physical address (MAC):");
            // Read user input
            String str = in.readLine();
            // Convert user input to bytes
            sendbyte = str.getBytes();
            // Create a DatagramPacket to send data to the server
            DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, 1309);
            // Send the packet to the server
            client.send(sender);
            // Create a DatagramPacket to receive data from the server
            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            // Receive the packet from the server
            client.receive(receiver);
            // Convert received data to a string
            String s = new String(receiver.getData());
            // Print the Logical Address (IP)
            System.out.println("The Logical Address is(IP): " + s.trim());
            // Close the client socket
            client.close();
        } catch (Exception e) {
            // Print any exceptions that occur
            System.out.println(e);
        }
    }
}
