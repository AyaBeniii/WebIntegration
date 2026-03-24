package tp3;

import java.rmi.Naming;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            System.setProperty("java.security.policy", "security.policy");

            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }

            IRemoteBanque b = (IRemoteBanque) Naming.lookup("rmi://localhost:1099/banque");

            System.out.println("Solde du compte 0 : " + b.getSolde(0));
            System.out.println("Propriétaire du compte 1 : " + b.getProprietaire(1));

            boolean ok = b.retrait(0, 500);
            System.out.println("Retrait effectué ? " + ok);
            System.out.println("Nouveau solde du compte 0 : " + b.getSolde(0));

            boolean vir = b.virement(0, 1, 1000);
            System.out.println("Virement effectué ? " + vir);

            System.out.println("Solde compte 0 : " + b.getSolde(0));
            System.out.println("Solde compte 1 : " + b.getSolde(1));

            Compte c = b.getCompte(2);
            System.out.println("Objet compte 2 : " + c);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}