package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static String lireFichier(String nomFichier) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(nomFichier));
        String ligne;
        String corps = "";

        while ((ligne = in.readLine()) != null) {
            corps += ligne;
        }

        in.close();
        return corps;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serveur = new ServerSocket(8000);
        System.out.println("Serveur Web multithread démarré sur le port 8000...");

        while (true) {
            System.out.println("En attente d'un client...");
            Socket client = serveur.accept();

            WebClientHandler t = new WebClientHandler(client);
            t.start();
        }
    }
}

class WebClientHandler extends Thread {
    private Socket client;

    public WebClientHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            String corps = WebServer.lireFichier("srcc/page.html");

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("Content-Length: " + corps.length());
            out.println();
            out.println(corps);

            client.close();
        } catch (Exception e) {
        }
    }
}