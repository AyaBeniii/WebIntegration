package exo3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRemoteVente extends Remote {
    void ajouterVente(Vente vente) throws RemoteException;

    double chiffreAffairesTotal() throws RemoteException;

    double chiffreAffairesParRegion(String region) throws RemoteException;

    String produitLePlusVendu() throws RemoteException;

    List<Vente> ventesSuperieuresA(double montant) throws RemoteException;

    List<Vente> toutesLesVentes() throws RemoteException;
}