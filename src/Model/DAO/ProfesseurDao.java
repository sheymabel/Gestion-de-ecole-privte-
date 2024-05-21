package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProfesseurDao {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";  // URL de connexion Oracle
    private static final String USER = "HR";
    private static final String PASSWORD = "Hr";
    private final static String INSERTION = "INSERT INTO professeur (nom, rgf, rg) VALUES (?, ?, ?)";
    private final String DELETE = "DELETE FROM professeur WHERE id = ?";
    private final String UPDATE = "UPDATE professeur SET nom = ?, rgf = ?, rg = ? WHERE id = ?";
    private final String SELECTALL = "SELECT * FROM professeur";
    private final static String SELECTID = "SELECT * FROM professeur WHERE id = ?";

    public static boolean inserer(Professeur professeur) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(INSERTION)) {
            
            stmt.setString(1, professeur.getNome());
            stmt.setString(2, professeur.getRgf());
            stmt.setString(3, professeur.getRg());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void supprimerParId(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstm = conn.prepareStatement(DELETE)) {
            
            pstm.setInt(1, id);
            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Professeur supprimé avec succès.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Échec de la suppression du professeur: " + e.getMessage());
        }
    }

    public void mettreAJour(Professeur professeur) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstm = conn.prepareStatement(UPDATE)) {
            
            pstm.setString(1, professeur.getNome());
            pstm.setString(2, professeur.getRgf());
            pstm.setString(3, professeur.getRg());
            pstm.setInt(4, professeur.getId());

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Professeur mis à jour avec succès.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Échec de la mise à jour du professeur: " + e.getMessage());
        }
    }

    public List<Professeur> getProfesseurs() {
        List<Professeur> listeProfesseurs = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstm = conn.prepareStatement(SELECTALL);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Professeur professeur = new Professeur();
                professeur.setId(rs.getInt("id"));
                professeur.setNome(rs.getString("nom"));
                professeur.setRgf(rs.getString("rgf"));
                professeur.setRg(rs.getString("rg"));
                listeProfesseurs.add(professeur);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la liste des professeurs: " + e.getMessage());
        }
        return listeProfesseurs;
    }

    public static Professeur getProfesseurParId(int id) {
        Professeur professeur = new Professeur();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstm = conn.prepareStatement(SELECTID)) {
             
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    professeur.setId(rs.getInt("id"));
                    professeur.setNome(rs.getString("nom"));
                    professeur.setRgf(rs.getString("rgf"));
                    professeur.setRg(rs.getString("rg"));
                }
            }

            JOptionPane.showMessageDialog(null, "Professeur récupéré avec succès.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération du professeur: " + e.getMessage());
        }
        return professeur;
    }
}
