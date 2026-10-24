package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class CompteEpargne extends Compte {

    // TODO 1 : attribut privé tauxInteret (BigDecimal), par exemple 0.02 pour 2 %
    private BigDecimal tauxInteret;

    // TODO 2 : constructeur (numero, solde, dateOuverture, client, tauxInteret)
    //          avec super(...) en première ligne
    public CompteEpargne(String numero, BigDecimal solde, LocalDate dateOuverture, Client client, BigDecimal tauxInteret){
        super(numero, solde, dateOuverture, client);

        this.tauxInteret = tauxInteret;
    }

    // TODO 3 : getter getTauxInteret()

    public BigDecimal getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public void retirer(BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException {
        // TODO 4 : même logique que le compte courant, mais SANS découvert :
        //          le disponible est simplement le solde
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(montant);
        }

        BigDecimal solde = getSolde();

        if (montant.compareTo(solde) > 0) {
            throw new SoldeInsuffisantException(montant.subtract(solde));
        }
        setSolde(getSolde().subtract(montant));
    }

    public void ajouterInterets() {
        BigDecimal nouveauSolde = getSolde()
                .multiply(BigDecimal.ONE.add(tauxInteret))
                .setScale(2, RoundingMode.HALF_UP);
        setSolde(nouveauSolde);
    }
}