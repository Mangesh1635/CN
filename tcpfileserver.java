import java.io.*;
import java.net.*;

public class tcpfileserver {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String command = in.readLine();
                if (command == null) break;

                switch (command) {
                    case "HELLO":
                        out.println("Hello from Server!");
                        break;

                    case "FILE":
                        String fileName = in.readLine();
                        FileOutputStream fos = new FileOutputStream("received_" + fileName);
                        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                            if (bytesRead < 1024) break;
                        }
                        fos.close();
                        System.out.println("File received: " + fileName);
                        break;

                    case "CALC":
                        String expr = in.readLine();
                        try {
                            double result = eval(expr);
                            out.println("Result: " + result);
                        } catch (Exception e) {
                            out.println("Invalid expression.");
                        }
                        break;

                    default:
                        out.println("Unknown command.");
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Very basic calculator (no operator precedence)
    public static double eval(String expr) {
        String[] parts = expr.split(" ");
        double a = Double.parseDouble(parts[0]);
        String op = parts[1];
        double b = Double.parseDouble(parts[2]);

        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: throw new IllegalArgumentException("Invalid operator");
        }
    }
}