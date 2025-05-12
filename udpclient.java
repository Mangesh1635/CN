import java.net.*;

public class udpclient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            String message = "Hello from UDP Client";
            byte[] sendBuffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 1235);
            socket.send(sendPacket);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}