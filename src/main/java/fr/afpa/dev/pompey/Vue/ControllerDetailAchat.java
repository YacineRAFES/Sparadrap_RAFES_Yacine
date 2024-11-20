package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.AchatDirectDAO;
import fr.afpa.dev.pompey.Modele.DAO.CommandeDAO;
import fr.afpa.dev.pompey.Modele.DAO.DemandeDAO;
import fr.afpa.dev.pompey.Modele.DAO.OrdonnancesDAO;
import fr.afpa.dev.pompey.Modele.Tables.ListeMedicamentDetailAchat;
import fr.afpa.dev.pompey.Utilitaires.Fenetre;

import javax.swing.*;
import java.util.List;

public class ControllerDetailAchat extends JFrame {
    private String[][] oldData;
    private JPanel contentPane;
    private JLabel titreLabel;
    private JTable listeDeMedicamentTable;
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel medecinLabel;
    private JLabel ListedeMedicamentLabel;
    private JScrollPane scrollPane;
    private JButton validerButton;
    private JLabel typeAchatLabel;
    private JLabel dateAchatLabel;
    private JLabel prixTotalLabel;

    private OrdonnancesDAO ordonnanceDAO;
    private AchatDirectDAO achatDirectDAO;
    private CommandeDAO commandeDAO;
    private DemandeDAO demandeDAO;

    /**
     * Constructeur de la classe ControllerDetailAchat
     *
     * @throws SaisieException
     */
    public ControllerDetailAchat(int id, int type){
        achatDirectDAO = new AchatDirectDAO();
        ordonnanceDAO = new OrdonnancesDAO();
        commandeDAO = new CommandeDAO();
        demandeDAO = new DemandeDAO();
        setTitle("Détail d'Achat");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // Positionnement de la fenêtre
        this.setLocationRelativeTo(null);

        validerButton.addActionListener(e -> {
            this.dispose();
        });

        if(type == 0){
            //Je récupère les informations du client par l'ID AchatDirect
            Client InfoClient = achatDirectDAO.find(id).getClient();
            typeAchatLabel.setText("Achat sans ordonnance");
            nomLabel.setText("Nom : "+ InfoClient.getNom());
            prenomLabel.setText("Prénom : "+ InfoClient.getPrenom());
            medecinLabel.setText("");
            // Je récupère la listes des médicaments par l'ID AchatDirect
            List<Commande> commandes = commandeDAO.findAllByAchatDirect(id);
            double prixtotal = 0;
            //Je calcule la totalité des prix en récupérant les prix des médicaments
            for (int i = 1; i < commandes.size(); i++) {
                prixtotal += commandes.get(i).getMedicament().getPrix() * commandes.get(i).getQuantite();
            }
            prixTotalLabel.setText("Prix total : " + prixtotal + " €");

        }else if(type == 1) {
            //Je récupère les informations du client par l'ID Ordonnance
            Client InfoClient = ordonnanceDAO.find(id).getClient();
            typeAchatLabel.setText("Achat avec ordonnance");
            nomLabel.setText("Nom : " + InfoClient.getNom());
            prenomLabel.setText("Prénom : " + InfoClient.getPrenom());
            Medecin InfoMedecin = ordonnanceDAO.find(id).getMedecin();
            medecinLabel.setText("Medecin : " + InfoMedecin.getNom() + " " + InfoMedecin.getPrenom());

            // Je récupère la listes des médicaments par l'ID Demande
            List<Demande> demandes = demandeDAO.findAllByOrdonnance(id);
            double prixtotal = 0;
            //Je calcule la totalité des prix en récupérant les prix des médicaments
            for (int i = 1; i < demandes.size(); i++) {
                prixtotal += demandes.get(i).getMedicament().getPrix() * demandes.get(i).getQuantite();
            }

            //prixtotal après le taux de prise en charge
            double prixTotalApresLaCharge = prixtotal - (prixtotal * InfoClient.getMutuelle().getTauxDePriseEnCharge());
            prixTotalLabel.setText("Prix total : " + prixtotal + " Prix total après la charge : " + prixTotalApresLaCharge + " €");
        }
    }
}
