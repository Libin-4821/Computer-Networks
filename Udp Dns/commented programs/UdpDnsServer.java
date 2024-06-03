import java.net.*; // Importing the package for networking operations
import java.util.HashMap; // Importing the package for using HashMap

public class UdpDnsServer {
    // Defining the port number on which the server will listen for requests
    private static final int PORT = 1234;

    // Creating a HashMap to act as a DNS table mapping domain names to IP addresses
    private static HashMap<String, String> dnsTable = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // Creating a DatagramSocket to receive and send UDP packets
        DatagramSocket socket = new DatagramSocket(PORT);
        System.out.println("UDP DNS Server started on port " + PORT); // Printing a message to indicate the server has started

        // Prepopulating the DNS table with some domain names and their corresponding IP addresses
        dnsTable.put("www.example.com", "93.184.216.34");
        dnsTable.put("www.google.com", "142.250.180.14");

        // Creating a buffer to hold incoming data
        byte[] buffer = new byte[1024];

        while (true) {
            // Creating a DatagramPacket to receive incoming requests
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            // Receiving a request from a client
            socket.receive(request);

            // Extracting the domain name from the request data
            String domainName = new String(request.getData(), 0, request.getLength());
            System.out.println("Received request for domain: " + domainName); // Printing the received domain name

            // Looking up the IP address for the requested domain name in the DNS table
            String ipAddress = dnsTable.getOrDefault(domainName, "Domain not found");

            // Converting the IP address to a byte array
            byte[] responseBytes = ipAddress.getBytes();

            // Creating a DatagramPacket to send the response back to the client
            DatagramPacket response = new DatagramPacket(
                responseBytes, responseBytes.length, request.getAddress(), request.getPort()
            );

            // Sending the response packet
            socket.send(response);
            System.out.println("Sent response: " + ipAddress); // Printing the sent IP address or error message
        }
    }
}
