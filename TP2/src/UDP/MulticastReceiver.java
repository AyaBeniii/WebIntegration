package UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver {

    public static void main(String[] args) throws Exception {
        int port = 8686;
        InetAddress groupe = InetAddress.getByName("224.0.0.1");

        MulticastSocket socket = new MulticastSocket(port);
        socket.joinGroup(groupe);

        System.out.println("MulticastReceiver en attente sur 224.0.0.1:8686...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            socket.receive(packet);

            String message = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Proverbe reçu : " + message);
        }
    }
}