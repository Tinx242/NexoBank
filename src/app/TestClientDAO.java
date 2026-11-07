package app;

import dao.ClientDAO;
import model.Client;
import model.TypePiece;

import java.sql.SQLException;
import java.time.LocalDate;

public class TestClientDAO {

    public static void main(String[] args) throws SQLException {
        ClientDAO dao = new ClientDAO();

        Client c = new Client("Dupont", "Marie", LocalDate.of(1990, 4, 12), "12 rue des Lilas",
                "91220", "Brétigny-sur-Orge", "", "0612345678",
                TypePiece.CNI, "TEST" + System.currentTimeMillis());
        dao.ajouter(c);
        System.out.println("Identifiant attribué : " + c.getIdClient());

        for (Client client : dao.trouverTous()) {
            System.out.println(client.getIdClient() + " - " + client.getNomComplet());
        }

        dao.mettreAJourTelephone(c.getIdClient(), "0700000000");
        System.out.println("Téléphone : " + dao.trouverParId(c.getIdClient()).getTelephone());
    }
}