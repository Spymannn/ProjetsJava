package StreetFighterFenetre.view;

import StreetFighterFenetre.modele.JoueurDB;
import StreetFighterFenetre.modele.PartieDB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;
/**
 *
 * @author Spymannn
 * Panneau qui me permet d'afficher les top 10 des scores
 * du jeu
 * tout joueur confondu
 */
public class HighScorePanel extends Pan{
    private JLabel hg;
    private ImageIcon hgI,backI;
    private JButton back;
    private JTextPane score;
    private Font font;
    private Object[][] donneesHighScore;
    private JTable tableauHighScore;
    private JoueurDB player;
    private String[] enTeteTableau;
    
    @SuppressWarnings("empty-statement")
    public HighScorePanel(){
        //Connexion à la base de données
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        PartieDB.setConnection(con);
        
        font = new Font("Verdana", Font.PLAIN, 22);
        
        hgI = new ImageIcon("img/fondEtLogo/highscoreLogo.png");

        backI = new ImageIcon("img/boutons/back.png");
        back = new JButton();
        back.setIcon(backI);
        
        hg = new JLabel();
       
        hg.setIcon(hgI);
        
        /**
         * création d'un arraylist partie 
         * dans lequel j'appelle ma méthode
         * topTenScore() de PartieDB
         * qui va retourner les 10 meilleures scores (ou moins s'il il y en a moins)
         * des parties, tout joueur confondu, et va tout mettre
         * dans un JTable
         */
        try{
            
            ArrayList<PartieDB> parties = PartieDB.topTenScore();
            donneesHighScore = new Object[parties.size()][3];
            enTeteTableau = new String[3];
            enTeteTableau[0] = "Rank";
            enTeteTableau[1] = "Pseudo";
            enTeteTableau[2] = "Score"; 
            
            

            int i = 0;
            for(PartieDB t : parties){
                player = new JoueurDB(t.getIdJoueur());
                player.read();
                donneesHighScore[i][0] = (i+1)+".";
                donneesHighScore[i][1] = player.getPseudoJoueur();
                donneesHighScore[i][2] = t.getScorePartie();
                i++;
            }
            
            tableauHighScore = new JTable(donneesHighScore, enTeteTableau);
            tableauHighScore.getColumnModel().getColumn(0).setPreferredWidth(300);
            tableauHighScore.getColumnModel().getColumn(1).setPreferredWidth(300);
            tableauHighScore.getColumnModel().getColumn(2).setPreferredWidth(300);
            tableauHighScore.setFont(font);
            tableauHighScore.getTableHeader().setFont(font);
            tableauHighScore.getTableHeader().setBackground(Color.black);
            tableauHighScore.getTableHeader().setForeground(Color.white);
            tableauHighScore.setBackground(Color.black);
            tableauHighScore.setForeground(Color.white);
            tableauHighScore.setRowHeight(30);
            tableauHighScore.setEnabled(false);
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(hg, gbc);
            gbc.gridy = 1;
            this.add(tableauHighScore.getTableHeader(), gbc);
            gbc.gridy = 2;
            this.add(tableauHighScore, gbc);
            gbc.gridy = 3;
            this.add(back, gbc);

        }catch(Exception e){
            e.printStackTrace();
        }
  
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.setBorder(null);
        back.setPreferredSize(new Dimension(200, 50));
        
    }

    public JButton getBack() {
        return back;
    }
    
    
}
