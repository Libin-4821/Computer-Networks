import java.io.*;
import java.net.*;

public class UdpDnsServer {
    private static int indexOf(String[] array, String str) {
        str = str.trim();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(str)) return i;
        }
        return -1;
    }

    public static void main(String arg[]) throws IOException {
        String[] hosts = {"yahoo.com", "gmail.com", "cricinfo.com", "facebook.com"};
        String[] ip = {"68.180.206.184", "209.85.148.19", "80.168.92.140", "69.63.189.16"};

        System.out.println("Press Ctrl + C to Quit");

        while (true) {
            DatagramSocket serverSocket = new DatagramSocket(1362);
            byte[] sendData = new byte[1021];
            byte[] receiveData = new byte[1021];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData()).trim();
            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence;
            System.out.println("Request for host " + sentence);

            if (indexOf(hosts, sentence) != -1) {
                capitalizedSentence = ip[indexOf(hosts, sentence)];
            } else {
                capitalizedSentence = "Host Not Found";
            }

            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);

            serverSocket.send(sendPacket);
            serverSocket.close();
        }
    }
}
