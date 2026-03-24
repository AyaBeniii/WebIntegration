package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpProverbeReceiver {

    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(7676);
        System.out.println("UdpProverbeReceiver en attente sur le port 7676...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Proverbe reçu : " + message);
        }
    }
}