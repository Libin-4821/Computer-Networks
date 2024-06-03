import java.io.*;
import java.net.*;

public class eserver {
    public static void main(String args[]) throws IOException {
        ServerSocket s = null;
        String line;
        BufferedReader is;
        PrintStream ps;
        Socket c = null;

        try {
            s = new ServerSocket(8080);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            c = s.accept();
            is = new BufferedReader(new InputStreamReader(c.getInputStream()));
            ps = new PrintStream(c.getOutputStream());

            while (true) {
                line = is.readLine();
                System.out.println("msg received and sent back to client");
                ps.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
