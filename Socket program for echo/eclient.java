import java.io.*;
import java.net.*;

public class eclient {
    public static void main(String args[]) {
        Socket c = null;
        String line;
        BufferedReader is;
        PrintStream os;

        try {
            c = new Socket("localhost", 8080);
            os = new PrintStream(c.getOutputStream());
            is = new BufferedReader(new InputStreamReader(System.in));

            do {
                System.out.println("client");
                line = is.readLine();
                os.println(line);

                if (!line.equals("exit")) {
                    System.out.println("server:" + is.readLine());
                }
            } while (!line.equals("exit"));
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (c != null) {
                    c.close();
                }
            } catch (IOException e) {
                System.out.println("socket closed");
            }
        }
    }
}
