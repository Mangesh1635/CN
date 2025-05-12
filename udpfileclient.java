import java.io.*;
import java.net.*;
import java.util.Scanner;

public class udpfileclient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);

        // Step 1: Request file
        System.out.print("Enter server IP address: ");
        String serverIP = scanner.nextLine();
        InetAddress serverAddress = InetAddress.getByName(serverIP);

        System.out.print("Enter file name to request: ");
        String fileName = scanner.nextLine();

        byte[] fileNameBytes = fileName.getBytes();
        DatagramPacket filenamePacket = new DatagramPacket(fileNameBytes, fileNameBytes.length, serverAddress, 9876);
        socket.send(filenamePacket);

        // Step 2: Receive file content
        FileOutputStream fos = new FileOutputStream("received_" + fileName);
        byte[] receiveBuffer = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

            if (msg.equals("EOF")) break; // End of file
            fos.write(receivePacket.getData(), 0, receivePacket.getLength());
        }

        System.out.println("File received and saved as: received_" + fileName);
        fos.close();
        socket.close();
    }
}