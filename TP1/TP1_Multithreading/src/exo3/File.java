package exo3;

import java.util.LinkedList;

public class File<T> {

    private final LinkedList<T> buffer = new LinkedList<>();
    private final int capacite;

    public File(int capacite) {
        this.capacite = capacite;
    }

    public synchronized void deposer(T item) {
        while (buffer.size() == capacite) { 
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        buffer.addLast(item); // FIFO
        System.out.println(Thread.currentThread().getName()
                + " a déposé : " + item + " | taille=" + buffer.size());

        notifyAll(); 
    }

    public synchronized T retirer() {
        while (buffer.isEmpty()) { 
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }

        T item = buffer.removeFirst(); // FIFO
        System.out.println(Thread.currentThread().getName()
                + " a retiré : " + item + " | taille=" + buffer.size());

        notifyAll(); 
        return item;
    }
}