import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection : " + socket);

            File file = new File("source.pdf");
            byte[] byteArray = new byte[(int) file.length()];

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(byteArray, 0, byteArray.length);

            OutputStream os = socket.getOutputStream();
            System.out.println("Sending file...");
            os.write(byteArray, 0, byteArray.length);
            os.flush();

            socket.close();
            System.out.println("File transfer complete.");
        }
    }
}
