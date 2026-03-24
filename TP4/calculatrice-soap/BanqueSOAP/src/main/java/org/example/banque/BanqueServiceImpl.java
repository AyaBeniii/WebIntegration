package org.example.banque;

import java.util.Date;
import javax.jws.WebService;

@WebService(endpointInterface = "org.example.banque.IBanqueService")
public class BanqueServiceImpl implements IBanqueService {

    private Compte compte = new Compte(1, 1000.0, new Date());

    @Override
    public double consulterSolde(int idCompte) {
        if (compte.getId() == idCompte) {
            return compte.getSolde();
        }
        throw new RuntimeException("Compte introuvable");
    }

    @Override
    public double retirer(int idCompte, double montant) {
        if (compte.getId() == idCompte) {
            if (montant > compte.getSolde()) {
                throw new RuntimeException("Solde insuffisant");
            }
            compte.setSolde(compte.getSolde() - montant);
            return compte.getSolde();
        }
        throw new RuntimeException("Compte introuvable");
    }

    @Override
    public double deposer(int idCompte, double montant) {
        if (compte.getId() == idCompte) {
            compte.setSolde(compte.getSolde() + montant);
            return compte.getSolde();
        }
        throw new RuntimeException("Compte introuvable");
    }

    @Override
    public Compte getCompte(int idCompte) {
        if (compte.getId() == idCompte) {
            return compte;
        }
        throw new RuntimeException("Compte introuvable");
    }
}