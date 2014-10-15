package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.CombatPanel;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Spyman
 * Fenêtre de combat final 
 */
public class WindowFight extends JFrame implements KeyListener {

    //En dessous la fenêtre et les panels, et labels
    private CombatPanel combatPanel;
    private String pseudoR;
    
    /**
     * constructeur paramétré de la fenêtre de combat final
     * @param pseudoR
     * @param j1
     * @param j2
     * @param nMap 
     */
    public WindowFight(String pseudoR,int j1,int j2,int nMap) {
        this.pseudoR = pseudoR;
        combatPanel = new CombatPanel(pseudoR,j1,j2,nMap,this);
        /**
         * ajout de KeyListener sur la fenêtre afin de pouvoir
         * récupérer les touches du clavier
         * qui sont focus sur la fenêtre
         */
        this.addKeyListener(this);
        this.requestFocusInWindow();

        this.add(combatPanel);
        
        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setIconImage(logoWindow.getImage());
        this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight() - 40));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Street Fighter IV");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
        
    }
    /**
     * Permet de reprendre les événements du clavier 
     * sur la fenêtre de jeu
     * si un événement est  = à une certaine touche, réaliser une action
     * @param event 
     */
    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.VK_SPACE){
            combatPanel.poingsJ1(combatPanel.getxCorpsJ1(),combatPanel.getyCorpsJ1());
        }
        if(event.getKeyCode()==KeyEvent.VK_E){
            combatPanel.piedsJ1(combatPanel.getxCorpsJ1(),combatPanel.getyCorpsJ1());
        }
        if(event.getKeyCode()==KeyEvent.VK_D){
            combatPanel.bougeJ1Droite();
        }
        if(event.getKeyCode()==KeyEvent.VK_Q){ 
            combatPanel.bougeJ1Gauche();
        }
        if(event.getKeyCode()==KeyEvent.VK_K){
            combatPanel.bougeJ2Gauche();
        }
        if(event.getKeyCode()==KeyEvent.VK_M){ 
            combatPanel.bougeJ2Droite();
        } 
        if(event.getKeyCode()==KeyEvent.VK_O){
            combatPanel.poingsJ2(combatPanel.getxCorpsJ2(),combatPanel.getyCorpsJ2());
        }
        if(event.getKeyCode()==KeyEvent.VK_P){
            combatPanel.piedsJ2(combatPanel.getxCorpsJ2(), combatPanel.getyCorpsJ2());
        }
        /**
         * Permet de quitter le jeu
         */
        if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
            int i = JOptionPane.showConfirmDialog(null,"êtes-vous sûr ?","Quitter le jeu",JOptionPane.YES_NO_OPTION,0,new ImageIcon("img/fondEtLogo/crossQuitter.png"));
            if(i==0){
                try {
                       Thread.sleep(2000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                this.dispose();
                WindowJeu j = new WindowJeu(pseudoR);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
    }

    @Override
    public void keyTyped(KeyEvent event) {
        //System.out.println("Code touche tapée : " + event.getKeyCode() + " - caractère touche tapée : " + event.getKeyChar());
        
    }
}
