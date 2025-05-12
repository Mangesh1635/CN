

import java.io.*;
import java.net.*;

public class tcplient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Hello from TCP Client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
