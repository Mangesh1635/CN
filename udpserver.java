import java.net.*;

public class udpserver {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1235);
            byte[] receiveBuffer = new byte[1024];

            System.out.println("UDP Server started. Waiting for datagram...");

            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from client: " + message);

            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}