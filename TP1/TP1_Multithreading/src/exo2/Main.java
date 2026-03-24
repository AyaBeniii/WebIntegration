package exo2;

public class Main {
    public static void main(String[] args) {

        Compte compteCommun = new Compte();

        JobAhmedEtFatima job = new JobAhmedEtFatima(compteCommun);

        Thread ahmed = new ThreadAhmed(job);
        Thread fatima = new ThreadFatima(job);

        ahmed.start();
        fatima.start();
    }
}