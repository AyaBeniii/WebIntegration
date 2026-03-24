package tp2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClientGUI extends JFrame {

    private JTextArea zoneMessages;
    private JTextField champMessage;
    private JButton boutonEnvoyer;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ChatClientGUI() throws Exception {
        setTitle("Client Chat");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        zoneMessages = new JTextArea();
        zoneMessages.setEditable(false);

        champMessage = new JTextField();
        boutonEnvoyer = new JButton("Envoyer");

        add(new JScrollPane(zoneMessages), BorderLayout.CENTER);
        add(champMessage, BorderLayout.SOUTH);
        add(boutonEnvoyer, BorderLayout.EAST);

        setVisible(true);

        socket = new Socket("localhost", 9000);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        String pseudo = JOptionPane.showInputDialog(this, "Entrez votre pseudo :");
        out.println(pseudo);

        boutonEnvoyer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                envoyerMessage();
            }
        });

        champMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                envoyerMessage();
            }
        });

        Thread reception = new Thread() {
            public void run() {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        zoneMessages.append(message + "\n");
                    }
                } catch (Exception e) {
                }
            }
        };
        reception.start();
    }

    public void envoyerMessage() {
        String message = champMessage.getText();
        if (!message.isEmpty()) {
            out.println(message);
            champMessage.setText("");
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatClientGUI();
    }
}