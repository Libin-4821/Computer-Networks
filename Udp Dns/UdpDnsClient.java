import java.io.*; 
import java.net.*; 

public class UdpDnsClient { 
    public static void main(String args[]) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        DatagramSocket clientSocket = new DatagramSocket(); 
        InetAddress ipAddress;

        if (args.length == 0) {
            ipAddress = InetAddress.getLocalHost();
        } else {
            ipAddress = InetAddress.getByName(args[0]); 
        }

        byte[] sendData = new byte[1024]; 
        byte[] receiveData = new byte[1024]; 
        int portAddr = 1362; 

        System.out.print("Enter the hostname : "); 
        String sentence = br.readLine(); 
        sendData = sentence.getBytes(); 

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, portAddr); 
        clientSocket.send(sendPacket); 

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
        clientSocket.receive(receivePacket); 

        String modifiedSentence = new String(receivePacket.getData()); 
        System.out.println("IP Address: " + modifiedSentence.trim()); 

        clientSocket.close(); 
    } 
}
