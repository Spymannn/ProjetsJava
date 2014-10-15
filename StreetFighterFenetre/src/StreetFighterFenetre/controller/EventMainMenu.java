package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.MainMenu;
import StreetFighterFenetre.view.Pan;
import java.awt.event.*;
/**
 *
 * @author Spymannn
 */
public class EventMainMenu implements ActionListener{
    private WindowJeu w;
    private Pan p;
    
    public EventMainMenu(WindowJeu w,Pan p){
        this.w=w;
        this.p=p;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==w.getExitMain()){
            w.dispose();
            Window w = new Window();
        }
        if(e.getSource()==w.getSettingsMain()){
            w.setContentPane(p);
            w.invalidate();
            w.validate();
        }
        if(e.getSource()==w.getStartMain()){
            w.dispose();
            WindowStart win = new WindowStart(w.getPseudoR());
        }
    }    
}
