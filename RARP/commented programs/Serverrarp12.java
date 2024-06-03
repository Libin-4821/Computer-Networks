import java.io.*;
import java.net.*;
import java.util.*;

class Serverrarp12 {
    public static void main(String args[]) {
        try {
            // Create a DatagramSocket for the server with port 1309
            DatagramSocket server = new DatagramSocket(1309);
            // Infinite loop to keep the server running
            while (true) {
                // Byte arrays to hold data for sending and receiving
                byte[] sendbyte = new byte[1024];
                byte[] receivebyte = new byte[1024];
                // DatagramPacket to receive data from the client
                DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
                // Receive the packet from the client
                server.receive(receiver);
                // Convert received data to string
                String str = new String(receiver.getData());
                String s = str.trim();
                // Get the address and port of the client
                InetAddress addr = receiver.getAddress();
                int port = receiver.getPort();
                // Arrays holding IP and MAC addresses
                String ip[] = { "165.165.80.80", "165.165.79.1" };
                String mac[] = { "6A:08:AA:C2", "8A:BC:E3:FA" };
                // Iterate through MAC addresses
                for (int i = 0; i < ip.length; i++) {
                    // If MAC address matches, send corresponding IP address
                    if (s.equals(mac[i])) {
                        sendbyte = ip[i].getBytes();
                        // DatagramPacket to send data to the client
                        DatagramPacket sender = new DatagramPacket(sendbyte, sendbyte.length, addr, port);
                        // Send the packet to the client
                        server.send(sender);
                        break;
                    }
                }
                // Exiting the loop after processing one request
                break;
            }
        } catch (Exception e) {
            // Print any exceptions that occur
            System.out.println(e);
        }
    }
}
