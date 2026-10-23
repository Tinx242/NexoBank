package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;

public interface Viriable {
    void virer(Compte destination, BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException;
}
