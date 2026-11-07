package dao;

import model.Client;
import model.TypePiece;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public void ajouter(Client client) throws SQLException {
        String sql = "INSERT INTO client (nom_client, prenom_client, date_naissance_client, "
                + "adresse_client, code_postal_client, ville_client, email_client, "
                + "telephone_client, type_piece_client, numero_piece_client) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setDate(3, Date.valueOf(client.getDateNaissance()));
            ps.setString(4, client.getAdresse());
            ps.setString(5, client.getCodePostal());
            ps.setString(6, client.getVille());
            ps.setString(7, videEnNull(client.getEmail()));
            ps.setString(8, videEnNull(client.getTelephone()));
            ps.setString(9, client.getTypePiece().name());
            ps.setString(10, client.getNumeroPiece());
            ps.executeUpdate();

            try (ResultSet cles = ps.getGeneratedKeys()) {
                if (cles.next()) {
                    client.setIdClient(cles.getInt(1));
                }
            }
        }
    }

    public Client trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id_client = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return construireClient(rs);
                }
            }
        }
        return null;
    }

    public List<Client> trouverTous() throws SQLException {
        String sql = "SELECT * FROM client ORDER BY nom_client";
        List<Client> clients = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clients.add(construireClient(rs));
            }
        }
        return clients;
    }

    public void mettreAJourTelephone(int id, String telephone) throws SQLException {
        String sql = "UPDATE client SET telephone_client = ? WHERE id_client = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, videEnNull(telephone));
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private Client construireClient(ResultSet rs) throws SQLException {
        Client client = new Client(
                rs.getString("nom_client"),
                rs.getString("prenom_client"),
                rs.getDate("date_naissance_client").toLocalDate(),
                rs.getString("adresse_client"),
                rs.getString("code_postal_client"),
                rs.getString("ville_client"),
                rs.getString("email_client"),
                rs.getString("telephone_client"),
                TypePiece.valueOf(rs.getString("type_piece_client")),
                rs.getString("numero_piece_client"));
        client.setIdClient(rs.getInt("id_client"));
        return client;
    }

    private String videEnNull(String texte) {
        return (texte == null || texte.isBlank()) ? null : texte;
    }
}