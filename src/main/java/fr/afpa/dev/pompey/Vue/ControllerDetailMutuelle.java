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
        //TODO : Fix this
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

        // Si le nom de la mutuelle est null, afficher "Non renseigné"
        if(mutuelle.getNom() == null){
            NomMutuelle.setText("Non renseigné");
        }else{
            NomMutuelle.setText(mutuelle.getNom());
        }

        // Si l'adresse de la mutuelle est null, afficher "Non renseigné"
        if(mutuelle.getAdresses() != null){
            AdresseMutuelle.setText(mutuelle.getAdresses().getRue());
            DepartementMutuelle.setText(mutuelle.getAdresses().getVille().getRegion().getNom());
            CodepostalMutuelle.setText(mutuelle.getAdresses().getVille().getCp());
            VilleMutuelle.setText(mutuelle.getAdresses().getVille().getNom());
        }else{
            AdresseMutuelle.setText("Non renseigné");
            DepartementMutuelle.setText("Non renseigné");
            CodepostalMutuelle.setText("Non renseigné");
            VilleMutuelle.setText("Non renseigné");
        }

        // Si le téléphone et l'email de la mutuelle sont null, afficher "Non renseigné"
        if(mutuelle.getCoordonnees() != null){
            TelephoneMutuelle.setText(mutuelle.getCoordonnees().getTelephone());
            EmailMutuelle.setText(mutuelle.getCoordonnees().getEmail());
        }else{
            TelephoneMutuelle.setText("Non renseigné");
            EmailMutuelle.setText("Non renseigné");
        }

        // Si le taux de prise en charge de la mutuelle est 0, afficher "Non renseigné"
        if(mutuelle.getTauxDePriseEnCharge() == 0){
            TxMutuelle.setText("Non renseigné");
        }else{
            TxMutuelle.setText(mutuelle.getTauxDePriseEnCharge() + " %");
        }


        // Fermer la fenêtre
        fermerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
