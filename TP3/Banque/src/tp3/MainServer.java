package tp3;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class MainServer {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            RemoteBanque banque = new RemoteBanque();

            banque.addCompte(new Compte(0, "Aya", 5000));
            banque.addCompte(new Compte(1, "Sara", 3000));
            banque.addCompte(new Compte(2, "Youssef", 7000));

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost:1099/banque", banque);

            System.out.println("Serveur RMI prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}