package org.example;

import com.example.banqueclient.BanqueServiceImplService;
import com.example.banqueclient.IBanqueService;
import com.example.banqueclient.Compte;

public class Main {
    public static void main(String[] args) {
        BanqueServiceImplService service = new BanqueServiceImplService();
        IBanqueService port = service.getBanqueServiceImplPort();

        double soldeInitial = port.consulterSolde(1);
        System.out.println("Solde initial : " + soldeInitial);

        double apresDepot = port.deposer(1, 200);
        System.out.println("Apres depot : " + apresDepot);

        double apresRetrait = port.retirer(1, 100);
        System.out.println("Apres retrait : " + apresRetrait);

        Compte compte = port.getCompte(1);
        System.out.println("Compte id : " + compte.getId());
        System.out.println("Compte solde : " + compte.getSolde());
    }
}