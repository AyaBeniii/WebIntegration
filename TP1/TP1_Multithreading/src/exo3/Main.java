package exo3;

public class Main {

    public static void main(String[] args) {

        File<Integer> file = new File<>(5); 

        // 2 producteurs
        Thread p1 = new Thread(new Producteur(file), "Producteur-1");
        Thread p2 = new Thread(new Producteur(file), "Producteur-2");

        // 2 consommateurs
        Thread c1 = new Thread(new Consommateur(file), "Consommateur-1");
        Thread c2 = new Thread(new Consommateur(file), "Consommateur-2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
