package tp3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteBanque extends Remote {
    int getSolde(int id) throws RemoteException;
    String getProprietaire(int id) throws RemoteException;
    boolean retrait(int id, int montant) throws RemoteException;
    boolean virement(int idSource, int idDestination, int montant) throws RemoteException;
    Compte getCompte(int id) throws RemoteException;
}