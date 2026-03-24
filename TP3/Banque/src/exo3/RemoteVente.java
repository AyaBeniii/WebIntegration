package exo3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteVente extends UnicastRemoteObject implements IRemoteVente {

    private List<Vente> ventes;

    public RemoteVente() throws RemoteException {
        super();
        ventes = new ArrayList<>();
    }

    @Override
    public void ajouterVente(Vente vente) throws RemoteException {
        if (vente != null) {
            ventes.add(vente);
        }
    }

    @Override
    public double chiffreAffairesTotal() throws RemoteException {
        double total = 0;
        for (Vente v : ventes) {
            total += v.getAmount();
        }
        return total;
    }

    @Override
    public double chiffreAffairesParRegion(String region) throws RemoteException {
        double total = 0;
        for (Vente v : ventes) {
            if (v.getRegion().equalsIgnoreCase(region)) {
                total += v.getAmount();
            }
        }
        return total;
    }

    @Override
    public String produitLePlusVendu() throws RemoteException {
        if (ventes.isEmpty()) {
            return "Aucune vente";
        }

        Map<String, Integer> compteur = new HashMap<>();

        for (Vente v : ventes) {
            String produit = v.getProduct();
            compteur.put(produit, compteur.getOrDefault(produit, 0) + 1);
        }

        String meilleurProduit = null;
        int max = 0;

        for (Map.Entry<String, Integer> entry : compteur.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                meilleurProduit = entry.getKey();
            }
        }

        return meilleurProduit;
    }

    @Override
    public List<Vente> ventesSuperieuresA(double montant) throws RemoteException {
        List<Vente> resultat = new ArrayList<>();

        for (Vente v : ventes) {
            if (v.getAmount() > montant) {
                resultat.add(v);
            }
        }

        return resultat;
    }

    @Override
    public List<Vente> toutesLesVentes() throws RemoteException {
        return new ArrayList<>(ventes);
    }
}