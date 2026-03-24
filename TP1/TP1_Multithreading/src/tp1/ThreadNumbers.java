package tp1;

public class ThreadNumbers implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
        }
    }
}