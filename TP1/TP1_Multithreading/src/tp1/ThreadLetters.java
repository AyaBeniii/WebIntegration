package tp1;

public class ThreadLetters implements Runnable {

    @Override
    public void run() {
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.print(c + " ");
        }
    }
}
