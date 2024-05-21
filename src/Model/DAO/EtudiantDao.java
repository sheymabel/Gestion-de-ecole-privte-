package Model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class EtudiantDao {
	private final static String INSERT = "INSERT INTO etudiant (nom, ra, rg) VALUES (?,?,?)";
	private final String DELETE = "DELETE FROM etudiant WHERE id=?";
	private final String UPDATE = "UPDATE etudiant SET nom = ?, ra = ?, rg = ? WHERE id = ?";
	private final String SELECTALL = "SELECT * FROM etudiant";
	private final static String SELECTID = "SELECT * FROM etudiant WHERE id=?";

	public static boolean inserer(Etudiant etudiant) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = Connecter.getConnexion();
			pstm = conn.prepareStatement(INSERT);

			pstm.setString(1, etudiant.getNom());
			pstm.setString(2, etudiant.getRa());
			pstm.setString(3, etudiant.getRg());

			pstm.executeUpdate();
			pstm.close();
			Connecter.fermerConnexion();
			JOptionPane.showMessageDialog(null, "Étudiant enregistré avec succès.");
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Échec de l'enregistrement de l'étudiant " + "ERREUR: " + e.getMessage());
		}
		return false;
	}

	public void supprimerID(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = Connecter.getConnexion();
			pstm = conn.prepareStatement(DELETE);
			pstm.setInt(1, id);

			pstm.executeUpdate();
			pstm.close();
			Connecter.fermerConnexion();
			JOptionPane.showMessageDialog(null, "Étudiant supprimé avec succès.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Échec de la suppression de l'étudiant " + "ERREUR: " + e.getMessage());
		}
	}

	public void mettreAJour(Etudiant etudiant) {
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = Connecter.getConnexion();
			pstm = conn.prepareStatement(UPDATE);
			pstm.setString(1, etudiant.getNom());
			pstm.setString(2, etudiant.getRa());
			pstm.setString(3, etudiant.getRg());
			pstm.setInt(4, etudiant.getId());
			pstm.executeUpdate();
			pstm.close();
			Connecter.fermerConnexion();
			JOptionPane.showMessageDialog(null, "Étudiant mis à jour avec succès.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Échec de la mise à jour de l'étudiant " + "ERREUR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Etudiant> getEtudiants() {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		ArrayList<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
		try {
			conn = Connecter.getConnexion();
			pstm = conn.prepareStatement(SELECTALL);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Etudiant etudiant = new Etudiant();

				etudiant.setId(rs.getInt("id"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setRa(rs.getString("ra"));
				etudiant.setRg(rs.getString("rg"));
				listeEtudiants.add(etudiant);
			}
			pstm.close();
			Connecter.fermerConnexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de la liste des étudiants " + e.getMessage());
		}
		return listeEtudiants;
	}

	public static Etudiant getEtudiantParId(int id) {
		Connection conn;
		PreparedStatement pstm;
		ResultSet rs;
		Etudiant etudiant = new Etudiant();
		try {
			conn = Connecter.getConnexion();
			pstm = conn.prepareStatement(SELECTID);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if(rs.next()) {
				etudiant.setId(rs.getInt("id"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setRa(rs.getString("ra"));
				etudiant.setRg(rs.getString("rg"));
			}

			pstm.close();
			Connecter.fermerConnexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erreur lors de la récupération de l'étudiant " + e.getMessage());
		}

		return etudiant;
	}

	
}
