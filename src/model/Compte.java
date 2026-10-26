package model;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Compte implements Viriable {

    private String numero;
    private BigDecimal solde;
    private LocalDate dateOuverture;
    private Client client;

    private final List<Operation> historique = new ArrayList<>();

    public Compte(String numero, BigDecimal solde, LocalDate dateOuverture, Client client) {
        this.numero = numero;
        this.solde = solde;
        this.dateOuverture = dateOuverture;
        this.client = client;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public LocalDate getDateOuverture() {
        return dateOuverture;
    }

    public Client getClient() {
        return client;
    }

    public void deposer(BigDecimal montant) throws MontantInvalideException {
        if (montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantInvalideException(montant);
        }
        solde = solde.add(montant);
        enregistrer(TypeOperation.DEPOT, montant, "Dépôt");
    }

    public abstract void retirer(BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException;


    @Override
    public void virer(Compte destination, BigDecimal montant)
            throws SoldeInsuffisantException, MontantInvalideException {
        this.retirer(montant);
        destination.deposer(montant);
    }

    protected void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    protected void enregistrer(TypeOperation type, BigDecimal montant, String libelle) {
        historique.add(new Operation(type, montant, LocalDateTime.now(), libelle));
    }

    public List<Operation> getHistorique() {
        return List.copyOf(historique);
    }
}