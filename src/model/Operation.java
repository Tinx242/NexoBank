package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Operation {

    // TODO 1 : 5 attributs privés : idOperation (int), type (TypeOperation),
    //          montant (BigDecimal), date (LocalDateTime), libelle (String)
    private int idOperation;
    private TypeOperation type ;
    private BigDecimal montant;
    private LocalDateTime date;
    private String libelle;

    // TODO 2 : constructeur (type, montant, date, libelle)
    //          sans idOperation : il sera donné par MySQL, comme pour Client
    public Operation(TypeOperation type, BigDecimal montant, LocalDateTime date, String  libelle){
        this.type = type;
        this.date = date;
        this.montant = montant;
        this.libelle = libelle;

    }

    // TODO 3 : getters pour les 5 attributs, et un seul setter : setIdOperation
    //          (une opération passée ne se modifie pas : pas d'autres setters)

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public TypeOperation getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLibelle() {
        return libelle;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    // TODO 4 : toString() qui retourne une ligne lisible, par exemple
    //          "2025-06-20T11:00 | DEPOT | 100.00 | Dépôt initial"
    //          (avec @Override ; pour la date, concaténez simplement date)

    @Override
    public String toString() {
        return date + " | " + type + " | " + montant + " | " + libelle;
    }


}