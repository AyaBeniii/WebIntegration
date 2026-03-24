package tp3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RemoteBanque extends UnicastRemoteObject implements IRemoteBanque {

    private ArrayList<Compte> comptes;

    public RemoteBanque() throws RemoteException {
        super();
        comptes = new ArrayList<>();
    }

    public void addCompte(Compte c) {
        comptes.add(c);
    }

    private Compte chercherCompteParId(int id) {
        for (Compte c : comptes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public int getSolde(int id) throws RemoteException {
        Compte c = chercherCompteParId(id);
        if (c == null) {
            return -1; 
        }
        return c.getSolde();
    }

    @Override
    public String getProprietaire(int id) throws RemoteException {
        Compte c = chercherCompteParId(id);
        if (c == null) {
            return "Compte introuvable";
        }
        return c.getNom();
    }

    @Override
    public boolean retrait(int id, int montant) throws RemoteException {
        Compte c = chercherCompteParId(id);

        if (c == null || montant <= 0) {
            return false;
        }

        if (c.getSolde() >= montant) {
            c.setSolde(c.getSolde() - montant);
            return true;
        }

        return false;
    }

    @Override
    public boolean virement(int idSource, int idDestination, int montant) throws RemoteException {
        Compte source = chercherCompteParId(idSource);
        Compte destination = chercherCompteParId(idDestination);

        if (source == null || destination == null || montant <= 0) {
            return false;
        }

        if (source.getSolde() >= montant) {
            source.setSolde(source.getSolde() - montant);
            destination.setSolde(destination.getSolde() + montant);
            return true;
        }

        return false;
    }

    @Override
    public Compte getCompte(int id) throws RemoteException {
        return chercherCompteParId(id);
    }
}