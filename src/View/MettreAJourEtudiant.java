package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class MettreAJourEtudiant extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton btnMettreAJour, btnAnnuler, btnRechercher;
    private JTextField champNom, champRa, champRg, champId;
    private JLabel nom, ra, rg, id, empty;

    public MettreAJourEtudiant() {
        // Initialize components
        initComponents();

        // Setup frame
        setTitle("Mettre à jour Étudiant");
        setSize(550, 300);
        setLayout(new GridLayout(5, 3, 3, 30)); // row, column, hgap, vgap
        getContentPane().setBackground(new Color(200, 200, 200));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initComponents() {
        // ID
        id = creerEtiquette("ID pour mettre à jour :");
        champId = new JTextField();
        add(champId);
        btnRechercher = creerBouton("Rech. ID", 'R');

        // Nom
        nom = creerEtiquette("Nom : ");
        champNom = new JTextField();
        add(champNom);
        add(new JLabel());

        // Registre Académique
        ra = creerEtiquette("Registre Académique de l'Étudiant : ");
        champRa = new JTextField();
        add(champRa);
        add(new JLabel());

        // Registre Général
        rg = creerEtiquette("Registre Général : ");
        champRg = new JTextField();
        add(champRg);
        add(new JLabel());

        // Buttons
        btnMettreAJour = creerBouton("Mettre à jour", 'M');
        btnAnnuler = creerBouton("Annuler", 'A');
    }

    private JLabel creerEtiquette(String texte) {
        JLabel etiquette = new JLabel(texte);
        etiquette.setFont(new Font("Times New Roman", Font.BOLD, 18));
        etiquette.setForeground(Color.BLACK);
        etiquette.setHorizontalAlignment(SwingConstants.CENTER);
        add(etiquette);
        return etiquette;
    }

    private JButton creerBouton(String texte, char c) {
        JButton bouton = new JButton(texte);
        bouton.setMnemonic(c);
        bouton.addActionListener(this);
        add(bouton);
        return bouton;
    }

    private void nettoyerChamps() {
        champId.setText("");
        champNom.setText("");
        champRa.setText("");
        champRg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRechercher) {
            rechercherEtudiant();
        } else if (e.getSource() == btnMettreAJour) {
            mettreAJourEtudiant();
        } else if (e.getSource() == btnAnnuler) {
            annuler();
        }
    }

    private void rechercherEtudiant() {
        Model.DAO.EtudiantDao dao = new Model.DAO.EtudiantDao();
        try {
            int id = Integer.parseInt(champId.getText());
            Model.DAO.Etudiant etudiant = dao.getEtudiantParId(id);
            if (etudiant != null) {
                champNom.setText(etudiant.getNom());
                champRa.setText(etudiant.getRa());
                champRg.setText(etudiant.getRg());
            } else {
                JOptionPane.showMessageDialog(this, "Étudiant introuvable avec cet ID.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide.");
        }
    }

    private void mettreAJourEtudiant() {
        Model.DAO.EtudiantDao dao = new Model.DAO.EtudiantDao();
        try {
            int id = Integer.parseInt(champId.getText());
            String nom = champNom.getText();
            String ra = champRa.getText();
            String rg = champRg.getText();
            

            if (nom.isEmpty() || ra.isEmpty() || rg.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.");
                return;
            }

            if (!Model.DAO.ValideRG.estRG(rg)) {
                JOptionPane.showMessageDialog(this, "RG invalide.");
                return;
            }

            Model.DAO.Etudiant mettreAJourEtudiant = new Model.DAO.Etudiant(id, nom, ra, rg);
            System.out.println(mettreAJourEtudiant);
            dao.mettreAJour(mettreAJourEtudiant);

            nettoyerChamps();
            JOptionPane.showMessageDialog(this, "Étudiant mis à jour avec succès.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalide");
        }
    }

    private void annuler() {
        new MenuAppTela();
        setVisible(false);
    }
    public static void main(String[] args) {
        new MettreAJourEtudiant();
    }
   
}
