package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.ChoixPersoPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Spymannn
 * Classe pour gérer les événements (cliques sur boutons)
 * de la classe ChoixPersoPanel
 */
public class EventChoixPersoPanel implements ActionListener{
    private WindowStart w;
    private ChoixPersoPanel p;
    /**
     * Constructeur paramétré
     * @param w
     * @param p 
     */
    public EventChoixPersoPanel(WindowStart w,ChoixPersoPanel p){
        this.w = w;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * changement de panel suivant le bouton dans lequel on clique
         * et instanciation d'une variable public de la classe WindowStart
         * player1
         */
        if (e.getSource() == w.getRyuBChoix()) {
            w.setContentPane(p);
            w.invalidate();
            w.validate();
            w.player1 = 1;
        }
        if (e.getSource() == w.getDhalsimBChoix()) {
            w.setContentPane(p);
            w.invalidate();
            w.validate();
            w.player1 = 2;
        }
        /**
         * changement de fenêtre suivant le bouton dans lequel on clique
         * et envoit du pseudo du joueur 
         * via une variable public de la variable WindowStart
         */
        if(e.getSource() == w.getBackChoix1()){
            w.dispose();
            WindowJeu j = new WindowJeu(w.pseudoR);
        }
        if(e.getSource() == w.getBackChoix2()){
            w.dispose();
            WindowJeu j = new WindowJeu(w.pseudoR);
        }
    }
}
