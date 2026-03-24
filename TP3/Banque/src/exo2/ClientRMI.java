package exo2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientRMI {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IRemoteFibo fibo = (IRemoteFibo) registry.lookup("fibo");

            Scanner sc = new Scanner(System.in);
            System.out.print("Entrer n : ");
            int n = sc.nextInt();

            long resultat = fibo.fibonacci(n);
            System.out.println("Fibonacci(" + n + ") = " + resultat);

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}