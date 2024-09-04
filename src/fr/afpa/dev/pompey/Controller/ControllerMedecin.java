package fr.afpa.dev.pompey.Controller;

import static fr.afpa.dev.pompey.Modele.Utilitaires.InterfaceModel.AjouterPlaceholder;
import javax.swing.*;

public class ControllerMedecin extends JFrame {
    private JPanel contentPane;
    private JTextField nomTextField;
    private JLabel coordonneeLabel;
    private JLabel adresseLabel;
    private JLabel ContactLabel;
    private JTextField numAgreementTextField;
    private JTextField cpTextField;
    private JTextField prenomTextField;
    private JTextField rueTextField;
    private JTextField villeTextField;
    private JTextField telephoneTextField;
    private JTextField emailTextField;
    private JButton annulerButton;
    private JButton creerButton;


    public ControllerMedecin(){
        setTitle("Medecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);

        AjouterPlaceholder(numAgreementTextField, "numéro d'agreement");
        AjouterPlaceholder(cpTextField, "Code postal");
        AjouterPlaceholder(prenomTextField, "Prénom");
        AjouterPlaceholder(rueTextField, "Rue");
        AjouterPlaceholder(villeTextField, "Ville");
        AjouterPlaceholder(telephoneTextField, "Telephone");
        AjouterPlaceholder(emailTextField, "Email");


    }
}
