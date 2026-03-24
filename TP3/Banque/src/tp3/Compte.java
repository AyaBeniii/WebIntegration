package tp3;

import java.io.Serializable;

public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nom;
    private int solde;

    public Compte(int id, String nom, int solde) {
        this.id = id;
        this.nom = nom;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Compte{id=" + id + ", nom='" + nom + "', solde=" + solde + "}";
    }
}