package org.example.banque;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IBanqueService {

    @WebMethod
    double consulterSolde(@WebParam(name = "idCompte") int idCompte);

    @WebMethod
    double retirer(@WebParam(name = "idCompte") int idCompte,
                    @WebParam(name = "montant") double montant);

    @WebMethod
    double deposer(@WebParam(name = "idCompte") int idCompte,
                   @WebParam(name = "montant") double montant);

    @WebMethod
    Compte getCompte(@WebParam(name = "idCompte") int idCompte);
}