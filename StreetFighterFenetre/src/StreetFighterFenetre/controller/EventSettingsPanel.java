package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.Pan;
import java.awt.event.*;

/**
 *
 * @author Spymannn
 */
public class EventSettingsPanel implements ActionListener{
    private WindowJeu w;
    private Pan p;
    
    public EventSettingsPanel(WindowJeu w,Pan p){
        this.w = w;
        this.p=p;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==w.getBackSettings()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getControlsSettings()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getHighScorePanel()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getBackControls()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
         if(e.getSource()==w.getBackRules()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getBackSettings()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getRules()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getHistorique()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getBackHistoPanel()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getBackHighScorePanel()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
    }
    
}
