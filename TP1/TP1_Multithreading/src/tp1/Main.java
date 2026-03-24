package tp1;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new ThreadNumbers());
        Thread t2 = new Thread(new ThreadLetters());

        t1.start();

        t1.join(); 
        t2.start();
    }
}