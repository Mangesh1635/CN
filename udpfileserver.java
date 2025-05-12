

import java.io.*;
import java.net.*;

public class udpfileserver {
    public static void main(String[] args)  throws IOException, InterruptedException  {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] receiveBuffer = new byte[65535]; // Max UDP size

        System.out.println("Server is running... Waiting for filename request...");

        // Step 1: Receive file name
        DatagramPacket filenamePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(filenamePacket);
        String fileName = new String(filenamePacket.getData(), 0, filenamePacket.getLength());
        System.out.println("Client requested file: " + fileName);

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        // Step 2: Send file content in chunks
        FileInputStream fis = new FileInputStream(file);
        byte[] sendBuffer = new byte[1024];
        InetAddress clientAddress = filenamePacket.getAddress();
        int clientPort = filenamePacket.getPort();
        int bytesRead;
        while ((bytesRead = fis.read(sendBuffer)) != -1) {
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, bytesRead, clientAddress, clientPort);
            socket.send(sendPacket);
            Thread.sleep(10); // small delay for stability
        }

        // Step 3: Send "end" signal
        byte[] endMsg = "EOF".getBytes();
        DatagramPacket endPacket = new DatagramPacket(endMsg, endMsg.length, clientAddress, clientPort);
        socket.send(endPacket);

        System.out.println("File sent successfully.");
        fis.close();
        socket.close();
    }
}