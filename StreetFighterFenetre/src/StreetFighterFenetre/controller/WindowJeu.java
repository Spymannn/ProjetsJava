
package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.HistoPanel;
import StreetFighterFenetre.view.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Spymannn
 * Fenêtre de connexions aux jeu après s'être identifié
 */
public class WindowJeu extends JFrame{
    
    /**
     * instanciation des Panel utilisé
     */
    
    protected MainMenu mainPanel;
    protected SettingsPanel settingsPanel;
    protected ControlsPanel controlsPanel;
    protected ReglesPanel reglesPanel;
    protected HistoPanel histoPanel;
    protected HighScorePanel highScorePanel;
    protected String pseudoR;
    
    
    public WindowJeu(String pseudoR){
        this.pseudoR = pseudoR;
       
        mainPanel = new MainMenu(pseudoR);
        settingsPanel = new SettingsPanel();
        controlsPanel = new ControlsPanel();
        reglesPanel = new ReglesPanel();
        histoPanel = new HistoPanel(pseudoR);
        highScorePanel = new HighScorePanel();
        

        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setContentPane(mainPanel);
        //Actions mainMenu
        mainPanel.getStart().addActionListener(new EventMainMenu(this, mainPanel));
        mainPanel.getSettings().addActionListener(new EventMainMenu(this,settingsPanel));
        mainPanel.getExit().addActionListener(new EventMainMenu(this, mainPanel));
        
        //Actions settingsPanel
        settingsPanel.getBack().addActionListener(new EventSettingsPanel(this, mainPanel));
        settingsPanel.getControls().addActionListener(new EventSettingsPanel(this, controlsPanel));
        settingsPanel.getRules().addActionListener(new EventSettingsPanel(this, reglesPanel));
        settingsPanel.getHisto().addActionListener(new EventSettingsPanel(this, histoPanel));
        settingsPanel.getHs().addActionListener(new EventSettingsPanel(this, highScorePanel));
        
        //Action controlsPanel
        controlsPanel.getBack().addActionListener(new EventSettingsPanel(this, settingsPanel));
        
        //Action reglesPanel
        reglesPanel.getBack().addActionListener(new EventSettingsPanel(this, settingsPanel));
        
        //Action histoPanel
        histoPanel.getBack().addActionListener(new EventSettingsPanel(this, settingsPanel));
        
        //Action highScorePanel
        highScorePanel.getBack().addActionListener(new EventSettingsPanel(this, settingsPanel));
        
        

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
    public JButton getStartMain(){
        return mainPanel.getStart();
    }
    public JButton getSettingsMain(){
        return mainPanel.getSettings();
    }
    public JButton getExitMain(){
        return mainPanel.getExit();
    }
    public JButton getBackSettings(){
        return settingsPanel.getBack();
    }
    public JButton getControlsSettings(){
        return settingsPanel.getControls();
    }
    public JButton getRules(){
        return settingsPanel.getRules();
    }
    public JButton getHistorique(){
        return settingsPanel.getHisto();
    }
    public JButton getBackControls(){
        return controlsPanel.getBack();
    }
    public JButton getBackRules(){
        return reglesPanel.getBack();
    }
    public JButton getBackHistoPanel(){
        return histoPanel.getBack();
    }
    public String getPseudoR(){
        return pseudoR;
    }
    public JButton getBackHighScorePanel(){
        return highScorePanel.getBack();
    }
    public JButton getHighScorePanel(){
        return settingsPanel.getHs();
    }
}
