import java.io.*;
import java.net.*;

public class FileClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connecting...");

        byte[] byteArray = new byte[6022386];
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream("destination.pdf");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(byteArray, 0, byteArray.length);
        int current = bytesRead;

        do {
            bytesRead = is.read(byteArray, current, (byteArray.length - current));
            if (bytesRead >= 0) current += bytesRead;
        } while (bytesRead > -1);

        bos.write(byteArray, 0, current);
        bos.flush();

        socket.close();
        System.out.println("File downloaded.");
    }
}
