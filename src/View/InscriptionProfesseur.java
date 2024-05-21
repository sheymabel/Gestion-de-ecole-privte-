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

import Model.DAO.Professeur;
import Model.DAO.ProfesseurDao;
import Model.DAO.ValideRG;

public class InscriptionProfesseur extends JFrame implements ActionListener {
    private static final long serialVersionUID = -7274754506975440908L;
    JButton btnAjouter, btnAnnuler;
    JTextField champNom, champRgf, champRg;
    JLabel nom, rgf, rg;

    public InscriptionProfesseur() {
        // nom
        nom = creerEtiquette("Nom : ");
        champNom = new JTextField();
        getContentPane().add(champNom);

        // rgf
        rgf = creerEtiquette("Registre Général de Fonctionnaire du professeur : ");
        champRgf = new JTextField();
        getContentPane().add(champRgf);

        // rg
        rg = creerEtiquette("Registre Général du professeur : ");
        champRg = new JTextField();
        getContentPane().add(champRg);

        // boutons
        btnAjouter = creerBouton("Ajouter", 'A');
        btnAnnuler = creerBouton("Annuler", 'C');

        setTitle("Ajouter Nouveau Professeur");
        setSize(550, 300);
        GridLayout gl = new GridLayout(4, 2, 3, 30); // lignes, colonnes, espacement horizontal, espacement vertical
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

    // méthode pour créer un bouton
    private JButton creerBouton(String texte, char c) {
        JButton bouton = new JButton(texte);
        bouton.setMnemonic(c);
        bouton.addActionListener(this);
        add(bouton);
        return bouton;
    }

    private void nettoyerChamps() {
        champNom.setText("");
        champRgf.setText("");
        champRg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            Professeur nouveau = new Professeur(champNom.getText(), champRgf.getText(), champRg.getText());
            String rg = nouveau.getRg();
            if (ValideRG.estRG(rg)) {
                // Debug: Print the values being inserted
                System.out.printf("Inserting Professeur: Nom=%s, RGF=%s, RG=%s\n", nouveau.getNome(), nouveau.getRgf(), nouveau.getRg());
                if (ProfesseurDao.inserer(nouveau)) {
                    nettoyerChamps();
                    JOptionPane.showMessageDialog(null, "Professeur ajouté avec succès");
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du professeur");
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "RG invalide");
            }
        }

        if (e.getSource() == btnAnnuler) {
            // Make sure MenuAppTela is correctly implemented
            MenuAppTela menu = new MenuAppTela();
            JOptionPane.showMessageDialog(null, "Inscription annulée");
            setVisible(false);
            menu.setVisible(true);
        }
    }

}
