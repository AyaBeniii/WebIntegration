package exo2;

public class Compte {
    private int solde = 1000;

    public int getSolde() {
        return solde;
    }

    public void retirer(int montant) {
        solde -= montant;
    }
}