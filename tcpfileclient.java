import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tcpfileclient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9999)) {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Choose Option: \n1. Say Hello\n2. Send File\n3. Calculator");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    out.println("HELLO");
                    System.out.println("Server: " + in.readLine());
                    break;

                case 2:
                    out.println("FILE");
                    System.out.print("Enter file path to send: ");
                    String filePath = scanner.nextLine();
                    File file = new File(filePath);
                    out.println(file.getName());

                    byte[] buffer = new byte[1024];
                    FileInputStream fis = new FileInputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    bos.flush();
                    fis.close();
                    System.out.println("File sent.");
                    break;

                case 3:
                    out.println("CALC");
                    System.out.print("Enter expression (e.g., 5 + 3): ");
                    String expr = scanner.nextLine();
                    out.println(expr);
                    System.out.println("Server: " + in.readLine());
                    break;

                default:
                    System.out.println("Invalid option.");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}