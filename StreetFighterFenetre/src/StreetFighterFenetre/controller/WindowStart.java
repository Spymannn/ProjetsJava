
package StreetFighterFenetre.controller;

import StreetFighterFenetre.view.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author Spymannn
 * Fenêtre choix des personnages et du terrain
 */
public class WindowStart extends JFrame{

    protected ChoixPersoPanel choixPersoPanel,choixPersoPanel2;
    protected MapPanel mapPanel;
    public String pseudoR;
    public int player1,player2,map;
    
    
    public WindowStart(String pseudoR){
        
        choixPersoPanel = new ChoixPersoPanel(1);
        choixPersoPanel2 = new ChoixPersoPanel(2);
        mapPanel = new MapPanel(); 
        
        this.pseudoR = pseudoR;
       

        ImageIcon logoWindow = new ImageIcon("img\\logoWindow.png");
        this.setContentPane(choixPersoPanel);
        
        //Action p1
        choixPersoPanel.getRyuB().addActionListener(new EventChoixPersoPanel(this, choixPersoPanel2));
        choixPersoPanel.getDhalsimB().addActionListener(new EventChoixPersoPanel(this, choixPersoPanel2));
        choixPersoPanel.getBack().addActionListener(new EventChoixPersoPanel(this, null));
        //Action p2
        choixPersoPanel2.getRyuB().addActionListener(new EventMapPanel(this, mapPanel));
        choixPersoPanel2.getDhalsimB().addActionListener(new EventMapPanel(this, mapPanel));
        choixPersoPanel2.getBack().addActionListener(new EventChoixPersoPanel(this, null));
        
        
        //Action map
        mapPanel.getMall().addActionListener(new EventMapPanel(this, null));
        mapPanel.getRue().addActionListener(new EventMapPanel(this, null));
        mapPanel.getDhalsimStage().addActionListener(new EventMapPanel(this, null));
        mapPanel.getRyuStage().addActionListener(new EventMapPanel(this, null));
        mapPanel.getBack().addActionListener(new EventMapPanel(this,null));
        

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
    public JButton getRyuBChoix(){
        return choixPersoPanel.getRyuB();
    }
    public JButton getDhalsimBChoix(){
        return choixPersoPanel.getDhalsimB();
    }
    public JButton getRyuBChoix2(){
        return choixPersoPanel2.getRyuB();
    }
    public JButton getDhalsimBChoix2(){
        return choixPersoPanel2.getDhalsimB();
    }
    public JButton getRue() {
        return mapPanel.getRue();
    }
    public JButton getBackChoix1(){
        return choixPersoPanel.getBack();
    }
    public JButton getBackChoix2(){
        return choixPersoPanel2.getBack();
    }
    public JButton getMall() {
        return mapPanel.getMall();
    }

    public JButton getDhalsimStage() {
        return mapPanel.getDhalsimStage();
    }

    public JButton getRyuStage() {
        return mapPanel.getRyuStage();
    }
    
    public JButton getBackPanel(){
        return mapPanel.getBack();
    }
}
