package fr.afpa.dev.pompey.Controller;

import fr.afpa.dev.pompey.Modele.Medecin;

import javax.swing.*;

public class ControllerDetailMedecin extends JFrame {
    private JPanel contentPane;

    public ControllerDetailMedecin(Medecin idmedecin) {
        setTitle("Détail Médecin");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(contentPane);
        this.setResizable(false);
        this.pack();

        // le positionnement de la fenetre
        this.setLocationRelativeTo(null);
    }
}
