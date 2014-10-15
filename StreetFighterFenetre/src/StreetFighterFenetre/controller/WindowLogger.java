
package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.*;
import javax.swing.*;

/**
 *
 * @author Spymannn
 * Fenêtre de login sur le jeu
 */
public class WindowLogger extends JFrame {

    
    protected LoggerPanel loggerPanel;
    protected String pseudoR;
    
    public WindowLogger(String pseudoR) {
        this.pseudoR = pseudoR;
       
        loggerPanel = new LoggerPanel(pseudoR);
        

        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setContentPane(loggerPanel);
        loggerPanel.getConnexion().addActionListener(new EventLoggerPanel(this,loggerPanel));
        loggerPanel.getBack().addActionListener(new EventLoggerPanel(this,loggerPanel));

        this.setIconImage(logoWindow.getImage());
        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 40));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Street Fighter IV");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
