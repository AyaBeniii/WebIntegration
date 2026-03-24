package UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Random;

public class MulticastSender {

    public static void main(String[] args) throws Exception {
        String[] proverbes = {
            "Après la pluie, le beau temps.",
            "Petit à petit, l’oiseau fait son nid.",
            "Qui ne tente rien n’a rien.",
            "L’union fait la force.",
            "Mieux vaut tard que jamais."
        };

        Random r = new Random();
        String message = proverbes[r.nextInt(proverbes.length)];

        InetAddress groupe = InetAddress.getByName("224.0.0.1");
        int port = 8686;

        MulticastSocket socket = new MulticastSocket();
        byte[] data = message.getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, groupe, port);
        socket.send(packet);

        System.out.println("Proverbe envoyé : " + message);

        socket.close();
    }
}