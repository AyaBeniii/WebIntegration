package tp2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner clavier = new Scanner(System.in);

        System.out.print("Entrez votre pseudo : ");
        String pseudo = clavier.nextLine();
        out.println(pseudo);

        ReceptionThread reception = new ReceptionThread(in);
        reception.start();

        String message;
        while (true) {
            message = clavier.nextLine();
            out.println(message);
        }
    }
}

class ReceptionThread extends Thread {
    private BufferedReader in;

    public ReceptionThread(BufferedReader in) {
        this.in = in;
    }

    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(message);
            }
        } catch (Exception e) {
        }
    }
}