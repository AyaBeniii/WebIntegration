package exo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RootTask implements Callable<List<String>> {

    private final int debut;
    private final int fin;

    public RootTask(int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public List<String> call() {
        List<String> res = new ArrayList<>();

        for (int i = debut; i <= fin; i++) {
            double r = Math.sqrt(i);
            res.add("sqrt(" + i + ") = " + r);
        }

        return res;
    }
}