package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class CompteEpargne extends Compte {

    private BigDecimal tauxInteret;

    public CompteEpargne(String numero, BigDecimal solde, LocalDate dateOuverture,
                         Client client, BigDecimal tauxInteret) {
        super(numero, solde, dateOuverture, client);
        this.tauxInteret = tauxInteret;
    }

    public BigDecimal getTauxInteret() {
        return tauxInteret;
    }

    @Override
    protected void debiter(BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(montant);
        }
        if (montant.compareTo(getSolde()) > 0) {
            throw new SoldeInsuffisantException(montant.subtract(getSolde()));
        }
        setSolde(getSolde().subtract(montant));
    }

    public void ajouterInterets() {
        BigDecimal nouveauSolde = getSolde()
                .multiply(BigDecimal.ONE.add(tauxInteret))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal interets = nouveauSolde.subtract(getSolde());
        setSolde(nouveauSolde);
        if (interets.compareTo(BigDecimal.ZERO) > 0) {
            enregistrer(TypeOperation.INTERETS, interets, "Intérêts");
        }
    }
}