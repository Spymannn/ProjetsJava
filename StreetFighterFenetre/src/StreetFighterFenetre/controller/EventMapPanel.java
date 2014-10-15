package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.Pan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Spymannn
 */
public class EventMapPanel implements ActionListener{
    private WindowStart w;
    private Pan p;
    
    public EventMapPanel(WindowStart w,Pan p){
        this.w = w;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == w.getRyuBChoix2()) {
            w.setContentPane(p);
            w.invalidate();
            w.validate();
            w.player2 = 1;
        }
        if (e.getSource() == w.getDhalsimBChoix2()) {
            w.setContentPane(p);
            w.invalidate();
            w.validate();
            w.player2 = 2;
        }
        /**
         * Envoie des données publics de pseudoR, player1, player2 et map
         * vers la fenêtre de combat, à partir de la fenêtre de choix
         * des personnages, et du terrain
         */
        if(e.getSource() == w.getMall()){
            w.map = 1;
            WindowFight f = new WindowFight(w.pseudoR,w.player1,w.player2,w.map);
            w.dispose();
        }
        if(e.getSource() == w.getRue()){
            w.map = 2;
            WindowFight f = new WindowFight(w.pseudoR,w.player1,w.player2,w.map);
            w.dispose();
        }
        if(e.getSource() == w.getDhalsimStage()){
            w.map = 3;
            WindowFight f = new WindowFight(w.pseudoR,w.player1,w.player2,w.map);
            w.dispose();
        }
        if(e.getSource() == w.getRyuStage()){
            w.map = 4;
            WindowFight f = new WindowFight(w.pseudoR,w.player1,w.player2,w.map);
            w.dispose();
        }
        if(e.getSource() == w.getBackPanel()){
            w.dispose();
            WindowJeu j = new WindowJeu(w.pseudoR);
        }
    }
}
