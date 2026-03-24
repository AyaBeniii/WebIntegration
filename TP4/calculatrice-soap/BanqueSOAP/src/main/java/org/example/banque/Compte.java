package org.example.banque;

import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

public class Compte {
    private int id;
    private double solde;
    private Date dateCreation;

    public Compte() {
    }

    public Compte(int id, double solde, Date dateCreation) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @XmlTransient
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}