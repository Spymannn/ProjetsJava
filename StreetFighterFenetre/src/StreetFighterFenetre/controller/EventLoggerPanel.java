package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.ConnexionPanel;
import StreetFighterFenetre.view.LoggerPanel;
import StreetFighterFenetre.view.Pan;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Spymannn
 * Classe qui gérer la connexion lorsqu'on clique sur connexion
 */
public class EventLoggerPanel implements ActionListener{
    private WindowLogger w;
    private LoggerPanel p;
    
    public EventLoggerPanel(WindowLogger w,LoggerPanel p){
        this.w = w;
        this.p = p;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == p.getBack()){
            w.dispose();
            Window win = new Window();
        }
        /**
         * Vérification du mot de passe, lors de la connexion
        */
        if(e.getSource() == p.getConnexion()){
            if(!p.getPassword().equals(p.getPassR())){
                p.setPassword("");
                JOptionPane.showMessageDialog(null,"Mauvais mot de passe", 
                            "Mot de passe invalide !",1,
                            new ImageIcon("img/imagesJoueur/error.png"));
            }
            else{
                JOptionPane.showMessageDialog(null,"Connexion validée",
                        "Mot de passe valide !",1,new ImageIcon("img/imagesJoueur/validation1.png"));
                w.dispose();
                WindowJeu j = new WindowJeu(p.getPseudoR());
            }
        }
    }
   
    
}
