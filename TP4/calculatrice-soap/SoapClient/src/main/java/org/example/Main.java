package org.example;

import com.example.client.ICalculatrice;
import com.example.client.CalculatriceImpService;

public class Main {
    public static void main(String[] args) {
        CalculatriceImpService service = new CalculatriceImpService();
        ICalculatrice port = service.getCalculatriceImpPort();

        double somme = port.add(50, 40);
        double produit = port.multiply(6, 7);
        double difference = port.subtract(20, 8);
        double quotient = port.divide(30, 5);

        System.out.println("La somme est : " + somme);
        System.out.println("Le produit est : " + produit);
        System.out.println("La difference est : " + difference);
        System.out.println("Le quotient est : " + quotient);
    }
}

