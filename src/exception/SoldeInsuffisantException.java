package exception;

import java.math.BigDecimal;

public class SoldeInsuffisantException extends Exception {

    private final BigDecimal manque;

    public SoldeInsuffisantException(BigDecimal manque) {
        super("Solde insuffisant : il manque " + manque + " EUR");
        this.manque = manque;
    }

    public BigDecimal getManque() {
        return manque;
    }
}