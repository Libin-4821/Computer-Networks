import java.io.*; 
import java.net.*; 

class ArpClient { 
    public static void main(String args[]) throws IOException { 
        try { 
            // Creating a Socket to connect to the server running on localhost at port 1100
            Socket ss = new Socket(InetAddress.getLocalHost(), 1100); 
            // Creating a PrintStream to send output to the server
            PrintStream ps = new PrintStream(ss.getOutputStream()); 
            // Creating a BufferedReader to read input from the console
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            String ip; 
            // Prompting the user to enter an IP address
            System.out.println("Enter the IPADDRESS:"); 
            // Reading the IP address from the console
            ip = br.readLine(); 
            // Sending the IP address to the server
            ps.println(ip); 
            String str;
            // Creating a BufferedReader to read the response from the server
            BufferedReader br2 = new BufferedReader(new InputStreamReader(ss.getInputStream())); 
            System.out.println("ARP From Server::"); 
            // Reading the response from the server line by line and printing it to the console
            do { 
                str = br2.readLine(); 
                System.out.println(str); 
            } while(!(str.equalsIgnoreCase("end"))); 
        } catch(IOException e) { 
            System.out.println("Error: " + e); 
        } 
    } 
}
