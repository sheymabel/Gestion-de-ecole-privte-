package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Connecter {
    private final static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private final static String username = "Hr";
    private final static String password = "Hr";

    private static Connection conn;

    public static Connection getConnexion() {
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("\nConnexion établie avec succès !\n");
        } catch (SQLException e) {
            System.out.println("\nImpossible d'établir la connexion " + e + "\n");
        }
        return conn;
    }

    public static void fermerConnexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("\nConnexion fermée avec succès !");
            }
        } catch (SQLException e) {
            System.out.println("\nImpossible de fermer la connexion " + e + "\n");
            System.exit(1);
        }
    }

    public static void fermerConnexion(Connection conn, PreparedStatement ptmt) {
        try {
            if (ptmt != null) {
                ptmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("\nConnexion fermée avec succès !");
        } catch (SQLException e) {
            System.out.println("\nImpossible de fermer la connexion " + e + "\n");
            System.exit(1);
        }
    }

    public static void fermerConnexion(Connection conn, PreparedStatement ptmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ptmt != null) {
                ptmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            System.out.println("\nConnexion fermée avec succès !");
        } catch (SQLException e) {
            System.out.println("\nImpossible de fermer la connexion " + e + "\n");
            System.exit(1);
        }
    }
}
