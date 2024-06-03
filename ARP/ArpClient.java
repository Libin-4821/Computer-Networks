import java.io.*; 
import java.net.*; 

class ArpClient { 
    public static void main(String args[]) throws IOException { 
        try { 
            Socket ss = new Socket(InetAddress.getLocalHost(), 1100); 
            PrintStream ps = new PrintStream(ss.getOutputStream()); 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            String ip; 
            System.out.println("Enter the IPADDRESS:"); 
            ip = br.readLine(); 
            ps.println(ip); 
            String str;
            BufferedReader br2 = new BufferedReader(new InputStreamReader(ss.getInputStream())); 
            System.out.println("ARP From Server::"); 
            do { 
                str = br2.readLine(); 
                System.out.println(str); 
            } while(!(str.equalsIgnoreCase("end"))); 
        } catch(IOException e) { 
            System.out.println("Error: " + e); 
        } 
    } 
}
