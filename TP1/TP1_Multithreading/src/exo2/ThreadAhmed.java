package exo2;

public class ThreadAhmed extends Thread {
    public ThreadAhmed(Runnable job) {
        super(job);
        setName("AHMED");
    }
}