import java.io.*; // Importing the package for input and output operations
import java.net.*; // Importing the networking package for socket programming

public class UdpDnsClient { 
    public static void main(String args[]) throws IOException { 
        // Creating a BufferedReader to read input from the console
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        // Creating a DatagramSocket for sending and receiving UDP packets
        DatagramSocket clientSocket = new DatagramSocket(); 
        
        InetAddress ipAddress; // Declaring an InetAddress object to hold the server's IP address

        // Checking if a command-line argument is provided for the server address
        if (args.length == 0) {
            // If no argument is provided, use the local host address
            ipAddress = InetAddress.getLocalHost();
        } else {
            // If an argument is provided, use it as the server address
            ipAddress = InetAddress.getByName(args[0]); 
        }

        byte[] sendData = new byte[1024]; // Creating a byte array to hold the data to be sent
        byte[] receiveData = new byte[1024]; // Creating a byte array to hold the received data
        int portAddr = 1362; // Defining the port number on which the server is listening

        System.out.print("Enter the hostname : "); // Prompting the user to enter a hostname
        String sentence = br.readLine(); // Reading the user input (hostname) from the console
        sendData = sentence.getBytes(); // Converting the user input to a byte array

        // Creating a DatagramPacket to send the hostname to the server
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portAddr); 
        // Sending the packet to the server
        clientSocket.send(sendPacket); 

        // Creating a DatagramPacket to receive the response from the server
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        // Receiving the response packet from the server
        clientSocket.receive(receivePacket); 

        // Converting the received byte array to a string and trimming any trailing whitespace
        String modifiedSentence = new String(receivePacket.getData()); 
        System.out.println("IP Address: " + modifiedSentence.trim()); // Printing the received IP address

        // Closing the DatagramSocket
        clientSocket.close(); 
    } 
}
