package exception;

import java.math.BigDecimal;

public class MontantInvalideException extends Exception {

    public MontantInvalideException(BigDecimal montant) {
        super("Montant invalide : " + montant + " (doit être strictement positif)");
    }
}

