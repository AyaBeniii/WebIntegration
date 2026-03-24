package exo2;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class JobAhmedEtFatima implements Runnable {
    private final Compte compte;
    private final Lock lock = new ReentrantLock();

    public JobAhmedEtFatima(Compte compte) {
        this.compte = compte;
    }

    public void doRetrait(int montant) {
        lock.lock(); 
        try {
            if (compte.getSolde() >= montant) {
                System.out.println(Thread.currentThread().getName()
                        + " veut retirer " + montant
                        + " | Solde avant = " + compte.getSolde());
                try {
                    Thread.sleep(200); // pause cruciale
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                compte.retirer(montant);
                System.out.println(Thread.currentThread().getName()
                        + " a retiré " + montant
                        + " | Solde après = " + compte.getSolde());
            } else {
                System.out.println("Solde insuffisant pour "
                        + Thread.currentThread().getName()
                        + " | Solde = " + compte.getSolde());
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {      
            if (compte.getSolde() < 10) {
                break;
            }
            doRetrait(10);
        }
    }
}