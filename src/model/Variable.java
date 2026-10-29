package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;

public interface Variable {
    void virer(Compte destination, BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException;
}
