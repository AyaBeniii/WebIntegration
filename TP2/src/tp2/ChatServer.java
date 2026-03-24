package tp2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    private static List<ClientChatHandler> clients = new ArrayList<>();

    public static void diffuser(String message, ClientChatHandler expediteur) {
        for (ClientChatHandler client : clients) {
            if (client != expediteur) {
                client.out.println(message);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serveur = new ServerSocket(9000);
        System.out.println("Serveur de chat démarré sur le port 9000...");

        while (true) {
            Socket socket = serveur.accept();
            ClientChatHandler client = new ClientChatHandler(socket);
            clients.add(client);
            client.start();
        }
    }

    static class ClientChatHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String pseudo;

        public ClientChatHandler(Socket socket) throws Exception {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }

        public void run() {
            try {
                pseudo = in.readLine();
                diffuser(">>> " + pseudo + " a rejoint le chat.", this);

                String message;
                while ((message = in.readLine()) != null) {
                    diffuser(pseudo + " : " + message, this);
                }

            } catch (Exception e) {
            } finally {
                try {
                    clients.remove(this);
                    diffuser(">>> " + pseudo + " a quitté le chat.", this);
                    socket.close();
                } catch (Exception e) {
                }
            }
        }
    }
}