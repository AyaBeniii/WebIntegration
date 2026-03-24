package org.example.banque;

import javax.xml.ws.Endpoint;

public class ServeurBanqueSOAP {
    public static void main(String[] args) {
        String url = "http://localhost:9001/banque";
        System.out.println("demarrage du serveur banque...");
        Endpoint.publish(url, new BanqueServiceImpl());
        System.out.println("service banque demarre sur " + url);
        System.out.println("wsdl disponible sur " + url + "?wsdl");
    }
}
