package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;
import java.util.Scanner;

public class Sender {

    public static void main(String[] args) throws Exception {
        Scanner clavier = new Scanner(System.in);

        System.out.print("Entrez le port du Receiver : ");
        int port = clavier.nextInt();

        String[] proverbes = {
            "Après la pluie, le beau temps.",
            "Petit à petit, l’oiseau fait son nid.",
            "Qui ne tente rien n’a rien.",
            "L’union fait la force.",
            "Mieux vaut tard que jamais."
        };

        Random r = new Random();
        String message = proverbes[r.nextInt(proverbes.length)];

        DatagramSocket socket = new DatagramSocket();
        byte[] data = message.getBytes();

        InetAddress adresse = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(data, data.length, adresse, port);

        socket.send(packet);
        System.out.println("Message envoyé : " + message);

        socket.close();
        clavier.close();
    }
}