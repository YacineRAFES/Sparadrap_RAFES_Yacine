package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Mutuelle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public ControllerDetailMutuelle(Mutuelle mutuelle) {
        setTitle("Détail Mutuelle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        // Remplir les champs de texte
        NomMutuelle.setText(mutuelle.getNom());
        AdresseMutuelle.setText(mutuelle.getAdresse());
        CodepostalMutuelle.setText(mutuelle.getCodePostal());
        VilleMutuelle.setText(mutuelle.getVille());
        TelephoneMutuelle.setText(mutuelle.getTelephone());
        EmailMutuelle.setText(mutuelle.getEmail());
        DepartementMutuelle.setText(mutuelle.getDepartement());
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
