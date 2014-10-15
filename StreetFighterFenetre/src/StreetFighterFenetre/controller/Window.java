
package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.*;
import javax.swing.*;

/**
 *
 * @author Spymannn
 * Fenêtre de lancement du PROGRAMME FINAL
 */
public class Window extends JFrame {

    protected MainMenu mainMenu;
    protected ConnexionPanel connexionPanel;
    protected SettingsPanel settingsPanel;
    protected InscriptionPanel inscriptionPanel;
    String pseudoR;
    boolean value = false;
    
    public Window() {
        connexionPanel = new ConnexionPanel(this);

        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setContentPane(connexionPanel);

        

        //Action connexionPanel
        connexionPanel.getInscription().addActionListener(new EventConnexionPanel(this));
        connexionPanel.getQuitter().addActionListener(new EventConnexionPanel(this));
       
        
        /**
         * Mise en place de la fenêtre de base 
         * qui a pour taille, la taille de l'écran
         * qui n'est pas modifiable
         * avec un logo choisit
         * et un titre choisit
         * et qui ferme le programme, si on la ferme
         */
        this.setIconImage(logoWindow.getImage());
        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 40));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Street Fighter IV");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }
    /**
     *Envoie des données de JButton des Panel vers les EVENT
     * @return 
     */
    public JButton getQuitter(){
        return connexionPanel.getQuitter();
    }
    public JButton getStart() {
        return mainMenu.getStart();
    }

    public JButton getSettings() {
        return mainMenu.getSettings();
    }

    public JButton getExit() {
        return mainMenu.getExit();
    }

    public JButton getBackSettings() {
        return settingsPanel.getBack();
    }

    public JButton getInscription(){
        return connexionPanel.getInscription();
    }
    
    public JButton getBackInscription(){
        return inscriptionPanel.getRetour();
    }
}
