package StreetFighterFenetre.controller;

import StreetFighterFenetre.controller.Window;
import StreetFighterFenetre.view.LoggerPanel;
import StreetFighterFenetre.view.Pan;
import StreetFighterFenetre.controller.WindowInscription;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author Spymannn
 */
public class EventConnexionPanel implements ActionListener{
    private Window w;
    
    public EventConnexionPanel(Window w){
        this.w = w;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == w.getInscription()){
            w.dispose();
            WindowInscription win = new WindowInscription();
            
        }
        if(e.getSource() == w.getQuitter()){
            System.exit(0);
        }

    }
    
}
