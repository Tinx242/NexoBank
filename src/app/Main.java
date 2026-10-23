package app;

import model.Client;
import model.TypePiece;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Client c = new Client("Dupont", "Marie", LocalDate.of(1990, 4, 12), "12 rue des Lilas",
                "91220", "Brétigny-sur-Orge", "marie@mail.fr", "0612345678",
                TypePiece.CNI, "190491234567");
        System.out.println(c.getNomComplet() + ", " + c.getAge() + " ans");
    }
}