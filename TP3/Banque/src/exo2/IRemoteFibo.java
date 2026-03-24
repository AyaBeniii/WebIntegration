package exo2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteFibo extends Remote {
    long fibonacci(int n) throws RemoteException;
}