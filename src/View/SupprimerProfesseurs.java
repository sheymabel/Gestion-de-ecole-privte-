package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Model.DAO.Professeur;
import Model.DAO.ProfesseurDao;

public class SupprimerProfesseurs extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton btnAnnuler, btnSupprimer, btnRechercher;
	private JTextField champId;
	private JLabel champNom, champRgf, champRg;
	private JLabel nom, rgf, rg, id, vide;

	public SupprimerProfesseurs() {
		// ID
		setId(creerEtiquette("ID pour mise à jour :"));
		champId = new JTextField();
		getContentPane().add(champId);
		btnRechercher = creerBouton("Rechercher ID", 'R');

		// nom
		setNom(creerEtiquette("Nom : "));
		champNom = etiquetteInfo("");
		getContentPane().add(champNom);
		vide = new JLabel();
		getContentPane().add(vide);

		// rgf
		setRgf(creerEtiquette("RGF : "));
		champRgf = etiquetteInfo("");
		getContentPane().add(champRgf);
		vide = new JLabel();
		getContentPane().add(vide);

		// rg
		setRg(creerEtiquette("RG : "));
		champRg = etiquetteInfo("");
		getContentPane().add(champRg);
		vide = new JLabel();
		getContentPane().add(vide);

		// boutons
		btnSupprimer = creerBouton("Supprimer", 'S');
		btnAnnuler = creerBouton("Annuler", 'A');

		// paramètres de la fenêtre
		setTitle("Supprimer Professeur");
		setSize(550, 300);
		GridLayout gl = new GridLayout(5, 3, 3, 30); // lignes, colonnes, espacement horizontal, espacement vertical
		getContentPane().setBackground(new Color(200, 200, 200));
		getContentPane().setLayout(gl);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// méthode pour créer une étiquette
	private JLabel creerEtiquette(String texte) {
		JLabel etiquette = new JLabel(texte);
		etiquette.setFont(new Font("Times New Roman", Font.BOLD, 18));
		etiquette.setForeground(Color.BLACK);
		etiquette.setHorizontalAlignment(SwingConstants.CENTER);
		add(etiquette);
		return etiquette;
	}

	// méthode pour créer une étiquette d'information
	private JLabel etiquetteInfo(String texte) {
		JLabel etiquette = new JLabel(texte);
		etiquette.setFont(new Font("Times New Roman", Font.BOLD, 24));
		etiquette.setForeground(Color.BLACK);
		etiquette.setHorizontalAlignment(SwingConstants.CENTER);
		add(etiquette);
		return etiquette;
	}

	// méthode pour créer un bouton
	private JButton creerBouton(String texte, char c) {
		JButton bouton = new JButton(texte);
		bouton.setMnemonic(c);
		bouton.addActionListener(this);
		add(bouton);
		return bouton;
	}

	// nettoie les champs
	private void nettoyerChamps() {
		champId.setText(null);
		champNom.setText("");
		champRgf.setText("");
		champRg.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProfesseurDao dao = new ProfesseurDao();
		if (e.getSource() == btnRechercher) {
			int idRecherche = Integer.parseInt(champId.getText());
			for (Professeur p : dao.getProfesseurs()) {
				if (p.getId() == idRecherche) {
					champNom.setText(p.getNome());
					champRgf.setText(p.getRgf());
					champRg.setText(p.getRg());
					break;
				} 
			}
		}
		
		if (e.getSource() == btnSupprimer) {
			int idSuppression = Integer.parseInt(champId.getText());
			for (Professeur p : dao.getProfesseurs()) {
				if (p.getId() == idSuppression) {
					dao.supprimerParId(idSuppression);
					break;
				}
			}
			nettoyerChamps();
		}
		
		if (e.getSource() == btnAnnuler) {
			MenuAppTela menu = new MenuAppTela();
			setVisible(false);
			menu.setVisible(true);
		}
	}

	public JLabel getRgf() {
		return rgf;
	}

	public void setRgf(JLabel rgf) {
		this.rgf = rgf;
	}

	public JLabel getRg() {
		return rg;
	}

	public void setRg(JLabel rg) {
		this.rg = rg;
	}

	public JLabel getNom() {
		return nom;
	}

	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	public JLabel getId() {
		return id;
	}

	public void setId(JLabel id) {
		this.id = id;
	}
}
