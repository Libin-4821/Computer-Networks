import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Connected to the server!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (true) {
            System.out.print("Client: ");
            message = clientInput.readLine();
            writer.write(message + "\n");
            writer.flush();
            if (message.equals("bye")) {
                break;
            }
            message = reader.readLine();
            System.out.println("Server: " + message);
        }

        socket.close();
        System.out.println("Connection closed.");
    }
}
