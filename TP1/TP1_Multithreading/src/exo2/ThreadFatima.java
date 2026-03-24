package exo2;

public class ThreadFatima extends Thread {
    public ThreadFatima(Runnable job) {
        super(job);
        setName("FATIMA");
    }
}
