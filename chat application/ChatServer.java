import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running and waiting for a client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (true) {
            message = reader.readLine();
            if (message.equals("bye")) {
                break;
            }
            System.out.println("Client: " + message);
            System.out.print("Server: ");
            message = serverInput.readLine();
            writer.write(message + "\n");
            writer.flush();
        }

        socket.close();
        System.out.println("Connection closed.");
    }
}
