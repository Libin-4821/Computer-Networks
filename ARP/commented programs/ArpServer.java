import java.io.*; 
import java.net.*; 

class ArpServer { 
    public static void main(String args[]) throws IOException { 
        try { 
            // Creating a ServerSocket to listen for incoming connections on port 1100
            ServerSocket ss = new ServerSocket(1100); 
            // Accepting a connection from a client
            Socket s = ss.accept(); 
            // Creating a PrintStream to send output to the client
            PrintStream ps = new PrintStream(s.getOutputStream()); 
            // Creating a BufferedReader to read input from the client
            BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            // Reading the IP address sent by the client
            String ip = br1.readLine(); 
            // Getting the runtime environment to execute the ARP command
            Runtime r = Runtime.getRuntime(); 
            // Executing the ARP command with the received IP address
            Process p = r.exec("arp -a " + ip); 
            // Creating a BufferedReader to read the output of the ARP command
            BufferedReader br2 = new BufferedReader(new InputStreamReader(p.getInputStream())); 
            String str; 
            // Reading the output of the ARP command line by line and sending it to the client
            while((str = br2.readLine()) != null) { 
                ps.println(str); 
            } 
            // Indicating the end of the ARP output
            ps.println("end");
        } catch(IOException e) { 
            System.out.println("Error: " + e); 
        } 
    } 
}
