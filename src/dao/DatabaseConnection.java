package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/nexobank";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection c = getConnection()) {
            System.out.println("Connexion réussie à la base " + c.getCatalog());
        } catch (SQLException e) {
            System.out.println("Échec de connexion : " + e.getMessage());
        }
    }
}