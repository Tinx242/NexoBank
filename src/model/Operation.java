package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {

    private int idOperation;
    private TypeOperation type;
    private BigDecimal montant;
    private LocalDateTime date;
    private String libelle;

    public Operation(TypeOperation type, BigDecimal montant, LocalDateTime date, String libelle) {
        this.type = type;
        this.montant = montant;
        this.date = date;
        this.libelle = libelle;
    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public TypeOperation getType() {
        return type;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String toString() {
        return date + " | " + type + " | " + montant + " | " + libelle;
    }
}