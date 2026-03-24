package tp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {

    private int port;
    private String file;
    private List<String> proverbs;

    public Server(int port, String file) {
        this.port = port;
        this.file = file;
        this.proverbs = new ArrayList<>();
    }

    public void load() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String line;

        while ((line = in.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                proverbs.add(line);
            }
        }

        in.close();
    }

    public void start() throws Exception {
        ServerSocket serveur = new ServerSocket(port);
        System.out.println("Serveur multithread démarré sur le port " + port + "...");

        while (true) {
            System.out.println("Le serveur est en attente d'un client...");
            Socket sc = serveur.accept();

            ClientHandler ch = new ClientHandler(sc, proverbs);
            ch.start();
        }
    }

    public static void main(String[] args) throws Exception {
        Server serveur = new Server(9999, "src/proverbes.txt");
        serveur.load();
        serveur.start();
    }
}

class ClientHandler extends Thread {
    private Socket socket;
    private List<String> proverbs;
    private Random r;

    public ClientHandler(Socket socket, List<String> proverbs) {
        this.socket = socket;
        this.proverbs = proverbs;
        this.r = new Random();
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String proverbe = proverbs.get(r.nextInt(proverbs.size()));
            out.println(proverbe);
            socket.close();
        } catch (Exception e) {
        }
    }
}