package app;

import exception.MontantInvalideException;
import exception.SoldeInsuffisantException;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Client c = new Client("Dupont", "Marie", LocalDate.of(1990, 4, 12), "12 rue des Lilas",
                "91220", "Brétigny-sur-Orge", "marie@mail.fr", "0612345678",
                TypePiece.CNI, "190491234567");
        System.out.println(c.getNomComplet() + ", " + c.getAge() + " ans");

        CompteEpargne ce = new CompteEpargne("FR002", new BigDecimal("1000.00"),
                LocalDate.now(), c, new BigDecimal("0.02"));
        ce.ajouterInterets();
        System.out.println("Solde épargne : " + ce.getSolde());      // 1020.00
        try {
            ce.retirer(new BigDecimal("2000"));
        } catch (SoldeInsuffisantException | MontantInvalideException e) {
            System.out.println("Erreur : " + e.getMessage());          // il manque 980.00 EUR
        }

        Operation o = new Operation(TypeOperation.DEPOT, new BigDecimal("100.00"),
                LocalDateTime.of(2025, 6, 20, 11, 0), "Dépôt initial");
        System.out.println(o);
    }

}