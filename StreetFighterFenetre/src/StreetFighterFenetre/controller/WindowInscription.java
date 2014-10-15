
package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.InscriptionPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Spymannn
 * Fenêtre d'inscription au jeu
 */
public class WindowInscription extends JFrame {

    
    protected InscriptionPanel inscriptionPanel;
    
    
    public WindowInscription() {
       
        inscriptionPanel = new InscriptionPanel(this);
        

        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setContentPane(inscriptionPanel);
        

        inscriptionPanel.getRetour().addActionListener(new BackInscri(this));

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
    public JButton getRetour(){
        return inscriptionPanel.getRetour();
    }   
}
class BackInscri implements ActionListener {
        private final WindowInscription w;
        public BackInscri(WindowInscription w){
            this.w = w;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == w.getRetour()) {
                w.dispose();
                Window ww = new Window();
            }
        }
}

