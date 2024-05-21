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

import Model.DAO.Etudiant;
import Model.DAO.EtudiantDao;

public class SupprimerEtudiant extends JFrame implements ActionListener {
    private JButton btnAnnuler, btnSupprimer, btnRechercher;
    private JTextField champId;
    private JLabel champNom, champRa, champRg;
    private JLabel nom, ra, rg, id, empty;

    public SupprimerEtudiant() {
        // ID
        id = creerEtiquette("ID à supprimer :");
        champId = new JTextField();
        getContentPane().add(champId);
        btnRechercher = creerBouton("Rechercher ID ", 'R');

        // nom
        nom = creerEtiquette("Nom : ");
        champNom = creerLabelInfo("");
        getContentPane().add(champNom);
        empty = new JLabel();
        getContentPane().add(empty);

        // ra
        ra = creerEtiquette("RA de l'étudiant : ");
        champRa = creerLabelInfo("");
        getContentPane().add(champRa);
        empty = new JLabel();
        getContentPane().add(empty);

        // rg
        rg = creerEtiquette("RG : ");
        champRg = creerLabelInfo("");
        getContentPane().add(champRg);
        empty = new JLabel();
        getContentPane().add(empty);

        // boutons
        btnSupprimer = creerBouton("Supprimer", 'S');
        btnAnnuler = creerBouton("Annuler", 'A');

        // Paramètres de la fenêtre
        setTitle("Supprimer Étudiant");
        setSize(550, 300);
        GridLayout gl = new GridLayout(5, 3, 3, 30); // lignes, colonnes, espacement horizontal - espacement vertical
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
    private JLabel creerLabelInfo(String texte) {
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

    // méthode pour nettoyer les champs
    private void viderChamps() {
        champId.setText("");
        champNom.setText("");
        champRa.setText("");
        champRg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	EtudiantDao dao = new EtudiantDao();
        
        if (e.getSource() == btnRechercher) {
            int idRecherche = Integer.parseInt(champId.getText());
            for (Etudiant a : dao.getEtudiants()) {
                if (a.getId() == idRecherche) {
                    champNom.setText(a.getNom());
                    champRa.setText(a.getRa());
                    champRg.setText(a.getRg());
                    break;
                } 
            }
        }

        if (e.getSource() == btnSupprimer) {
            int idSupprimer = Integer.parseInt(champId.getText());
            for (Etudiant a : dao.getEtudiants()) {
                if (a.getId() == idSupprimer) {
                    dao.supprimerID(idSupprimer);
                    break;
                }
            }
            viderChamps();
        }

        if (e.getSource() == btnAnnuler) {
            MenuAppTela a = new MenuAppTela();
            setVisible(false);
            a.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SupprimerEtudiant();
    }
}
