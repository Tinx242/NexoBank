package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CompteCourant extends Compte {

    private BigDecimal decouvertAutorise;

    public CompteCourant(String numero, BigDecimal solde, LocalDate dateOuverture,
                         Client client, BigDecimal decouvertAutorise) {
        super(numero, solde, dateOuverture, client);
        this.decouvertAutorise = decouvertAutorise;
    }

    public BigDecimal getDecouvertAutorise() {
        return decouvertAutorise;
    }

    @Override
    public void retirer(BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(montant);
        }

        BigDecimal disponible = getSolde().add(decouvertAutorise);

        if (montant.compareTo(disponible) > 0) {
            throw new SoldeInsuffisantException(montant.subtract(disponible));
        }
        setSolde(getSolde().subtract(montant));
    }
}