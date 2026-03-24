package exo6;

import java.util.*;
import java.util.concurrent.*;

public class TravailEnParallele {

    private static final int MAX = 5000;
    private static final int CHUNK = 100;     
    private static final int N_THREADS = 5;

    public static void main(String[] args) throws Exception {

        sequential();

        parallelRunnable();

        parallelCallableOrdered();
    }

    // 1) Version séquentielle
    private static void sequential() {
        System.out.println("\n===== 1) SEQUENTIEL =====");
        long start = System.currentTimeMillis();

        for (int i = 0; i <= MAX; i++) {
            double r = Math.sqrt(i);
            // affichage
            System.out.println("sqrt(" + i + ") = " + r);
        }

        long end = System.currentTimeMillis();
        System.out.println("Temps séquentiel = " + (end - start) + " ms");
    }

    // 2-4) ExecutorService + Runnable (affichage pas forcément dans l'ordre) + shutdown
    private static void parallelRunnable() throws InterruptedException {
        System.out.println("\n===== 2-4) PARALLELE Runnable (ordre NON garanti) =====");

        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        long start = System.currentTimeMillis();

        for (int debut = 0; debut <= MAX; debut += CHUNK) {

            final int d = debut; // copie finale
            final int f = Math.min(debut + CHUNK - 1, MAX);

            Runnable task = () -> {
                for (int i = d; i <= f; i++) {
                    double r = Math.sqrt(i);
                    System.out.println(Thread.currentThread().getName()
                            + " -> sqrt(" + i + ") = " + r);
                }
            };

            executor.submit(task);
        }

        // 4) shutdown
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("Temps parallèle (Runnable) = " + (end - start) + " ms");
    }

    // 5-6) Callable + Future + affichage dans l'ordre + temps
    private static void parallelCallableOrdered() throws Exception {
        System.out.println("\n===== 5-6) PARALLELE Callable/Future (ordre GARANTI) =====");

        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        long start = System.currentTimeMillis();

        // collection de futures
        List<Future<List<String>>> futures = new ArrayList<>();

        for (int debut = 0; debut <= MAX; debut += CHUNK) {
            int fin = Math.min(debut + CHUNK - 1, MAX);

            // RootTask = Callable séparée (voir fichier RootTask.java)
            Future<List<String>> f = executor.submit(new RootTask(debut, fin));
            futures.add(f);
        }

        // shutdown (plus de tâches)
        executor.shutdown();

        // Affichage DANS L'ORDRE des racines croissantes :
        // on parcourt futures dans le même ordre qu'on les a ajoutées (debut 0..)
        for (Future<List<String>> f : futures) {
            List<String> lignes = f.get(); // récupère résultat
            for (String line : lignes) {
                System.out.println(line);
            }
        }

        executor.awaitTermination(1, TimeUnit.MINUTES);

        long end = System.currentTimeMillis();
        System.out.println("Temps parallèle (Callable) = " + (end - start) + " ms");
    }
}