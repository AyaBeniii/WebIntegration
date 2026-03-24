package exo2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) {
        try {
            IRemoteFibo obj = new RemoteFibo();

            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registry RMI créé sur le port 1099");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Registry RMI déjà lancé sur le port 1099");
            }

            registry.rebind("fibo", obj);

            System.out.println("Serveur RMI Fibonacci démarré");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}