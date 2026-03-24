package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt(args[0]);

        DatagramSocket socket = new DatagramSocket(port);
        System.out.println("Receiver en attente sur le port " + port + "...");

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        String message = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Message reçu : " + message);

        socket.close();
    }
}