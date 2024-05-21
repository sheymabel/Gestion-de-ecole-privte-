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

public class MettreAJourProfesseur extends JFrame implements ActionListener {
    private static final long serialVersionUID = -3618544201274551456L;
    private JButton btnMaj, btnAnnuler, btnRechercher;
    private JTextField champNom, champRgf, champRg, champId;
    private JLabel nom, rgf, rg, id, vide;

    public MettreAJourProfesseur() {
        // ID
        setId(creerEtiquette("ID pour mise à jour :"));
        champId = new JTextField();
        getContentPane().add(champId);
        btnRechercher = creerBouton("Rech. ID", 'R');

        // nom
        setNom(creerEtiquette("Nom : "));
        champNom = new JTextField();
        getContentPane().add(champNom);
        vide = new JLabel();
        getContentPane().add(vide);

        // rgf
        setRgf(creerEtiquette(" Registre Général de Fonctionnaire : "));
        champRgf = new JTextField();
        getContentPane().add(champRgf);
        vide = new JLabel();
        getContentPane().add(vide);

        // rg
        setRg(creerEtiquette("Registre Général du professeur : "));
        champRg = new JTextField();
        getContentPane().add(champRg);
        vide = new JLabel();
        getContentPane().add(vide);

        // boutons
        btnMaj = creerBouton("Mettre à jour", 'M');
        btnAnnuler = creerBouton("Annuler", 'A');

        setTitle("Mettre à jour Professeur");
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

    // méthode pour créer un bouton
    private JButton creerBouton(String texte, char c) {
        JButton bouton = new JButton(texte);
        bouton.setMnemonic(c);
        bouton.addActionListener(this);
        add(bouton);
        return bouton;
    }

    // méthode pour nettoyer les champs
    public void nettoyerChamps() {
        champId.setText("");
        champNom.setText("");
        champRgf.setText("");
        champRg.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProfesseurDao dao = new ProfesseurDao();
        if (e.getSource() == btnRechercher) {
            try {
                int id = Integer.parseInt(champId.getText());
                Professeur professeur = dao.getProfesseurParId(id);
                if (professeur != null) {
                    champNom.setText(professeur.getNome());
                    champRgf.setText(professeur.getRgf());
                    champRg.setText(professeur.getRg());
                } else {
                    JOptionPane.showMessageDialog(null, "Professeur non trouvé avec cet ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalide.");
            }
        }

        if (e.getSource() == btnMaj) {
            try {
                int id = Integer.parseInt(champId.getText());
                Professeur nouveau = new Professeur();
                nouveau.setId(id);
                nouveau.setNome(champNom.getText());
                nouveau.setRgf(champRgf.getText());
                nouveau.setRg(champRg.getText());

                if (ValideRG.estRG(nouveau.getRg())) {
                    dao.mettreAJour(nouveau);
                    nettoyerChamps();
                    JOptionPane.showMessageDialog(null, "Professeur mis à jour avec succès.");
                } else {
                    JOptionPane.showMessageDialog(null, "RG invalide.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID invalide.");
            }
        }

        if (e.getSource() == btnAnnuler) {
            MenuAppTela menu = new MenuAppTela();
            setVisible(false);
            menu.setVisible(true);
        }
    }

    public static void main(String[] args) {
        new MettreAJourProfesseur();
    }

    public JLabel getRg() {
        return rg;
    }

    public void setRg(JLabel rg) {
        this.rg = rg;
    }

    public JLabel getId() {
        return id;
    }

    public void setId(JLabel id) {
        this.id = id;
    }

    public JLabel getRgf() {
        return rgf;
    }

    public void setRgf(JLabel rgf) {
        this.rgf = rgf;
    }

    public JLabel getNom() {
        return nom;
    }

    public void setNom(JLabel nom) {
        this.nom = nom;
    }
}
