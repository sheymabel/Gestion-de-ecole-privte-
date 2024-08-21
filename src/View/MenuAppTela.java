package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;




public class MenuAppTela extends JFrame implements ActionListener {
    JLabel home;
    JMenuBar barraMenu;
    JMenu cadastro, atualizar, exclusao, leitura, encerrar;
    JMenuItem professor, aluno, attAluno, attProfessor, delAluno, delProfessor, ListProf, ListAluno, sair;

    public MenuAppTela() {
        // composants de la page d'accueil
        home = criarHome("Bienvenue dans le système");

        // barre de menu
        barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);

        // éléments de la barre de menu
        cadastro = new JMenu("Enregistrement");
        cadastro.setMnemonic('E');
        atualizar = new JMenu("Mise à jour");
        atualizar.setMnemonic('M');
        exclusao = new JMenu("Suppression");
        exclusao.setMnemonic('S');
        leitura = new JMenu("Lecture");
        leitura.setMnemonic('L');
        encerrar = new JMenu("Quitter");
        encerrar.setMnemonic('Q');

        barraMenu.add(cadastro);
        barraMenu.add(atualizar);
        barraMenu.add(exclusao);
        barraMenu.add(leitura);
        barraMenu.add(encerrar);

        // création des éléments du menu
        professor = criarItem("Professeur", 'P', cadastro);
        aluno = criarItem("Étudiant", 'E', cadastro);

        attAluno = criarItem("Mettre à jour Étudiant", 'E', atualizar);
        attProfessor = criarItem("Mettre à jour Professeur", 'P', atualizar);

        delAluno = criarItem("Supprimer Étudiant", 'E', exclusao);
        delProfessor = criarItem("Supprimer Professeur", 'P', exclusao);

        ListProf = criarItem("Liste des Professeurs", 'P', leitura);
        ListAluno = criarItem("Liste des Étudiants", 'E', leitura);

        sair = criarItem("Quitter", 'Q', encerrar);

        setTitle("Gestion des ecole privte ");
        setSize(550, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // créer les éléments du menu
    private JMenuItem criarItem(String text, char c, JMenu menu) {
        JMenuItem item = new JMenuItem(text);
        item.setMnemonic(c);
        item.addActionListener(this);
        menu.add(item);
        return item;
    }

    // méthode pour créer la page d'accueil
    private JLabel criarHome(String text) {
        JLabel textoH = new JLabel(text);
        textoH.setVerticalAlignment(SwingConstants.CENTER);
        textoH.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        textoH.setForeground(Color.BLACK);
        textoH.setHorizontalAlignment(SwingConstants.CENTER);
        add(textoH);
        return textoH;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Enregistrement
        if (e.getSource() == professor) {
        	InscriptionProfesseur a = new InscriptionProfesseur();
            a.setVisible(true);
            setVisible(false);
        }

        if (e.getSource() == aluno) {
        	InscriptionEtudiant a = new InscriptionEtudiant();
            a.setVisible(true);
            setVisible(false);
        }

        // Mise à jour
        if (e.getSource() == attAluno) {
        	MettreAJourEtudiant a = new MettreAJourEtudiant();
            a.setVisible(true);
            setVisible(false);
        }

        if (e.getSource() == attProfessor) {
        	MettreAJourProfesseur a = new MettreAJourProfesseur();
            a.setVisible(true);
            setVisible(false);
        }

        // Suppression
        if (e.getSource() == delAluno) {
        	SupprimerEtudiant a = new SupprimerEtudiant();
            a.setVisible(true);
            setVisible(false);
        }

        if (e.getSource() == delProfessor) {
        	SupprimerProfesseurs a = new SupprimerProfesseurs();
            a.setVisible(true);
            setVisible(false);
        }

        // Listes
        if (e.getSource() == ListProf) {
        	ListeProfesseurs a = new ListeProfesseurs();
            a.setVisible(true);
            setVisible(false);
        }

        if (e.getSource() == ListAluno) {
        	ListeEtudiants a = new ListeEtudiants();
            a.setVisible(true);
            setVisible(false);
        }

        // Quitter
        if (e.getSource() == sair) {
            int sair = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?");
            if (sair == 0) {
                System.exit(0);
            }
        }
    }
}