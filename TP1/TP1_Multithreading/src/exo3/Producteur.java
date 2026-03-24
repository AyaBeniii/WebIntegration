package exo3;

import java.util.Random;

public class Producteur implements Runnable {

    private final File<Integer> file;
    private final Random random = new Random();

    public Producteur(File<Integer> file) {
        this.file = file;
    }

    @Override
    public void run() {
        while (true) {
            int produit = random.nextInt(100); 
            file.deposer(produit);

            try {
                Thread.sleep(200); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
