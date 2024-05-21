package View;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.DAO.Professeur;
import Model.DAO.ProfesseurDao;

public class ListeProfesseurs extends JFrame {
    private static final long serialVersionUID = -1810485101841046862L;
    private JPanel panelPrincipal, panelBoutons;
    private JTable table;
    private JScrollPane barreDefilement;
    private JButton btRetour;
    private DefaultTableModel modele = new DefaultTableModel();

    public ListeProfesseurs() {
        table = new JTable(modele);
        modele.addColumn("ID");
        modele.addColumn("Nom");
        modele.addColumn("RGF");
        modele.addColumn("RG");
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        rechercher(modele);

        btRetour = new JButton("Retour");
        panelBoutons = new JPanel();
        barreDefilement = new JScrollPane(table);
        panelPrincipal = new JPanel();

        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(BorderLayout.CENTER, barreDefilement);
        panelBoutons.add(btRetour);
        panelPrincipal.add(BorderLayout.SOUTH, panelBoutons);
        btRetour.addActionListener(new BtRetourListener());

        getContentPane().add(panelPrincipal);
        setTitle("Liste des Professeurs");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void rechercher(DefaultTableModel modele) {
        modele.setNumRows(0);
        ProfesseurDao dao = new ProfesseurDao();

        for (Professeur p : dao.getProfesseurs()) {
            modele.addRow(new Object[] { p.getId(), p.getNome(), p.getRgf(), p.getRg() });
        }
    }

    private class BtRetourListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btRetour) {
                MenuAppTela menu = new MenuAppTela();
                setVisible(false);
                menu.setVisible(true);
            }
        }
    }
}
