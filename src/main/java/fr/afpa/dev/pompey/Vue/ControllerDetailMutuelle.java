package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Modele.DAO.MutuelleDAO;
import fr.afpa.dev.pompey.Modele.Mutuelle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe ControllerDetailMutuelle est le contrôleur de la fenêtre de détail de la mutuelle
 */
public class ControllerDetailMutuelle extends JFrame {
    private JPanel contentPane;
    private JLabel NomMutuelle;
    private JLabel AdresseMutuelle;
    private JLabel CodepostalMutuelle;
    private JLabel VilleMutuelle;
    private JLabel TelephoneMutuelle;
    private JLabel EmailMutuelle;
    private JLabel DepartementMutuelle;
    private JLabel TxMutuelle;
    private JButton fermerButton;

    private MutuelleDAO mutuelleDAO;

    /**
     * Constructeur de la classe ControllerDetailMutuelle
     */
    public ControllerDetailMutuelle(int id) {
        Mutuelle mutuelle = mutuelleDAO.find(id);
        //Initialisation des DAO
        mutuelleDAO = new MutuelleDAO();

        setTitle("Détail Mutuelle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir les champs de texte

        NomMutuelle.setText(mutuelle.getNom());
        AdresseMutuelle.setText(mutuelle.getAdresses().getRue());
        CodepostalMutuelle.setText(String.valueOf(mutuelle.getAdresses().getVille().getCp()));
        VilleMutuelle.setText(mutuelle.getAdresses().getVille().getNom());
        TelephoneMutuelle.setText(mutuelle.getCoordonnees().getTelephone());
        EmailMutuelle.setText(mutuelle.getCoordonnees().getEmail());
        TxMutuelle.setText(mutuelle.getTauxDePriseEnCharge() + " %");

        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
