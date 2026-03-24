package exo3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ClientRMI {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IRemoteVente service = (IRemoteVente) registry.lookup("venteService");

            System.out.println("=== Toutes les ventes ===");
            List<Vente> toutes = service.toutesLesVentes();
            for (Vente v : toutes) {
                System.out.println(v);
            }

            System.out.println("\nChiffre d'affaires total : " + service.chiffreAffairesTotal());

            System.out.println("Chiffre d'affaires région Nord : " + service.chiffreAffairesParRegion("Nord"));

            System.out.println("Produit le plus vendu : " + service.produitLePlusVendu());

            System.out.println("\nVentes supérieures à 1000 :");
            List<Vente> grossesVentes = service.ventesSuperieuresA(1000);
            for (Vente v : grossesVentes) {
                System.out.println(v);
            }

            System.out.println("\nAjout d'une nouvelle vente...");
            service.ajouterVente(new Vente(6, "Ecran", "Ouest", 2200, "2026-03-18"));

            System.out.println("Nouveau chiffre d'affaires total : " + service.chiffreAffairesTotal());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}