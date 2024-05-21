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

import Model.DAO.Etudiant;
import Model.DAO.EtudiantDao;
import Model.DAO.ValideRG;

public class InscriptionEtudiant extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    JButton btnAjouter, btnAnnuler;
    JTextField champNom, champRa, champRg;
    JLabel nom, ra, rg;

    public InscriptionEtudiant() {
        // nom
        nom = creerEtiquette("Nom: ");
        champNom = new JTextField();
        getContentPane().add(champNom);

        // ra
        ra = creerEtiquette("Registre Académique de l'Étudiant: ");
        champRa = new JTextField();
        getContentPane().add(champRa);

        // rg
        rg = creerEtiquette("Registre Général de l'étudiant");
        champRg = new JTextField();
        getContentPane().add(champRg);

        // boutons
        btnAjouter = creerBouton("Ajouter", 'A');
        btnAnnuler = creerBouton("Annuler", 'C');

        setTitle("Insérer Nouvel Étudiant");
        setSize(550, 300);
        GridLayout gl = new GridLayout(4, 2, 3, 30); // ligne, colonne, espacement horizontal - espacement vertical
        getContentPane().setBackground(new Color(200, 200, 200));
        getContentPane().setLayout(gl);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    private void effacerChamps() {
        champNom.setText("");
        champRa.setText("");
        champRg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            Etudiant nouveau = new Etudiant(champNom.getText(), champRa.getText(), champRg.getText());
            String rg = nouveau.getRg();
            if (ValideRG.valideRG(rg) != null) {
                System.out.printf("%s\n", ValideRG.imprimeRG(rg));
                if (EtudiantDao.inserer(nouveau)) {
                    effacerChamps();
                }
            } else {
                JOptionPane.showMessageDialog(null, "RG invalide");
            }
        }
        if (e.getSource() == btnAnnuler) {
            MenuAppTela menu = new MenuAppTela();
            JOptionPane.showMessageDialog(null, "Enregistrement annulé");
            setVisible(false);
            menu.setVisible(true);
        }

    }

}
