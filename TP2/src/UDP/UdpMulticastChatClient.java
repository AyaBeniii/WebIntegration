package UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Scanner;

public class UdpMulticastChatClient {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage : java UdpMulticastChatClient <pseudo>");
            return;
        }

        String pseudo = args[0];
        String adresseMulticast = "224.0.0.1";
        int port = 8686;

        InetAddress groupe = InetAddress.getByName(adresseMulticast);
        MulticastSocket socket = new MulticastSocket(port);
        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        socket.joinGroup(new InetSocketAddress(groupe, port), ni);

        System.out.println("Connecté au chat multicast avec le pseudo : " + pseudo);
        System.out.println("Tapez vos messages :");

        Thread reception = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                        socket.receive(packet);

                        String message = new String(packet.getData(), 0, packet.getLength());
                        System.out.println(message);
                    }
                } catch (Exception e) {
                }
            }
        });

        Thread envoi = new Thread(new Runnable() {
            public void run() {
                try {
                    Scanner clavier = new Scanner(System.in);

                    while (true) {
                        String texte = clavier.nextLine();
                        String message = pseudo + " : " + texte;

                        byte[] data = message.getBytes();
                        DatagramPacket packet = new DatagramPacket(data, data.length, groupe, port);

                        socket.send(packet);
                    }
                } catch (Exception e) {
                }
            }
        });

        reception.start();
        envoi.start();
    }
}