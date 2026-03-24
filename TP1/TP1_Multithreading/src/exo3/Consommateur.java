package exo3;

public class Consommateur implements Runnable {

    private final File<Integer> file;

    public Consommateur(File<Integer> file) {
        this.file = file;
    }

    @Override
    public void run() {
        while (true) {
            Integer item = file.retirer();
            if (item == null) break;

            // Consommer (ex: afficher)
            System.out.println("   " + Thread.currentThread().getName()
                    + " consomme : " + item);

            try {
                Thread.sleep(300); // ralentir pour mieux voir
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}