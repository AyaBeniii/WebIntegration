package exo2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteFibo extends UnicastRemoteObject implements IRemoteFibo {

    public RemoteFibo() throws RemoteException {
        super();
    }

    @Override
    public long fibonacci(int n) throws RemoteException {
        if (n < 0) {
            throw new RemoteException("n doit être positif");
        }

        if (n == 0) return 0;
        if (n == 1) return 1;

        long a = 0;
        long b = 1;

        for (int i = 2; i <= n; i++) {
            long c = a + b;
            a = b;
            b = c;
        }

        return b;
    }
}