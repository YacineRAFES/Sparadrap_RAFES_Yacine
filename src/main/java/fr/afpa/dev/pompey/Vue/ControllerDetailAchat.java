package fr.afpa.dev.pompey.Vue;

import fr.afpa.dev.pompey.Exception.SaisieException;
import fr.afpa.dev.pompey.Modele.*;
import fr.afpa.dev.pompey.Modele.DAO.*;
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
    private ClientDAO clientDAO;
    private MedecinDAO medecinDAO;
    private MutuelleDAO mutuelleDAO;

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
        clientDAO = new ClientDAO();
        medecinDAO = new MedecinDAO();
        mutuelleDAO = new MutuelleDAO();

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
            AchatDirect achatDirect = achatDirectDAO.find(id);
            Client client = clientDAO.find(achatDirect.getClient().getId());
            typeAchatLabel.setText("Achat sans ordonnance");
            nomLabel.setText("Nom : "+ client.getNom());
            prenomLabel.setText("Prénom : "+ client.getPrenom());
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
            //Je récupère les informations du client et medecin par l'ID Ordonnance
            Ordonnances ordonnances = ordonnanceDAO.find(id);
            Client client = clientDAO.find(ordonnances.getClient().getId());
            Medecin medecin = medecinDAO.find(ordonnances.getMedecin().getId());
            Mutuelle mutuelle = mutuelleDAO.find(client.getMutuelle().getId());

            typeAchatLabel.setText("Achat avec ordonnance");
            nomLabel.setText("Nom : " + client.getNom());
            prenomLabel.setText("Prénom : " + medecin.getPrenom());
            medecinLabel.setText("Medecin : " + medecin.getNom() + " " + medecin.getPrenom());

            // Je récupère la listes des médicaments par l'ID Demande
            List<Demande> demandes = demandeDAO.findAllByOrdonnance(id);
            double prixtotal = 0;
            //Je calcule la totalité des prix en récupérant les prix des médicaments
            for (int i = 1; i < demandes.size(); i++) {
                prixtotal += demandes.get(i).getMedicament().getPrix() * demandes.get(i).getQuantite();
            }

            //prixtotal après le taux de prise en charge
            double prixTotalApresLaCharge = prixtotal - (prixtotal * mutuelle.getTauxDePriseEnCharge());
            prixTotalLabel.setText("Prix total : " + prixtotal + " Prix total après la charge : " + prixTotalApresLaCharge + " €");
        }
    }
}
