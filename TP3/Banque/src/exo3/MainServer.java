package exo3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    public static void main(String[] args) {
        try {
            IRemoteVente service = new RemoteVente();

            service.ajouterVente(new Vente(1, "PC", "Nord", 12000, "2026-03-10"));
            service.ajouterVente(new Vente(2, "Souris", "Sud", 200, "2026-03-11"));
            service.ajouterVente(new Vente(3, "PC", "Nord", 13000, "2026-03-12"));
            service.ajouterVente(new Vente(4, "Clavier", "Est", 500, "2026-03-12"));
            service.ajouterVente(new Vente(5, "PC", "Sud", 12500, "2026-03-13"));

            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Registry créé sur le port 1099");
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(1099);
                System.out.println("Registry déjà actif sur le port 1099");
            }

            registry.rebind("venteService", service);

            System.out.println("Serveur RMI Exercice 3 démarré");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}