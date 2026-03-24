package tp2;

import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 9999);

        Scanner in = new Scanner(s.getInputStream());
        String proverbe = in.nextLine();

        System.out.println("Le proverbe du jour est : " + proverbe);

        in.close();
        s.close();
    }
}