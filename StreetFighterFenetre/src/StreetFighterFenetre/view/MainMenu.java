package StreetFighterFenetre.view;
import StreetFighterFenetre.modele.JoueurDB;
import StreetFighterFenetre.modele.PartieDB;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import myconnectionsfenetre.DBConnection;

/**
 *
 * @author Spymannn
 * Menu principal après s'être connecté
 * 
 */
public class MainMenu extends Pan{
    private JButton start,settings,exit;
    private JLabel logo,avatar,pseudo,score;
    private ImageIcon avatarI;
    private Font font;
    
    public MainMenu(String ps){
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        PartieDB.setConnection(con);
        
        ImageIcon starter = new ImageIcon("img\\boutons\\start2.png");
        ImageIcon sett = new ImageIcon("img\\boutons\\settings2.png");
        ImageIcon quit = new ImageIcon("img\\boutons\\exit2.png");
        ImageIcon logoo = new ImageIcon("img\\logo.png");
 
        start = new JButton();
        settings = new JButton();
        exit = new JButton();
        
        logo = new JLabel();
        avatar = new JLabel();
        pseudo = new JLabel();
        score = new JLabel();
        font = new Font("Verdana", Font.BOLD, 16);
 
        try{
            JoueurDB j1 = JoueurDB.recherchePseudoJoueur(ps);
            PartieDB p1 = PartieDB.afficheScoreMax(j1.getIdJoueur());
            avatarI = new ImageIcon("img/imagesJoueur/"+j1.getImageJoueur()+".png");
            avatar.setIcon(avatarI);
            score.setText("Score maximum obtenu : "+p1.getScorePartie());
            pseudo.setText("Bonjour "+ps);
            
            score.setFont(font);
            pseudo.setFont(font);
            score.setForeground(new Color(0,0,0));
            pseudo.setForeground(new Color(0,0,0));
            
            
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);
            gbc.gridy = 1;
            this.add(pseudo, gbc);
            gbc.gridy = 2;
            this.add(avatar,gbc);
            gbc.gridy = 3;
            this.add(score, gbc);
            
            gbc.gridy = 4;
            this.add(start, gbc);

            gbc.gridy = 5;
            this.add(settings, gbc);
            gbc.gridy = 6;
            this.add(exit, gbc);
            gbc.gridy = 1;
            gbc.gridx = 2;
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
 
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);
        start.setBorder(null);
        start.setPreferredSize(new Dimension(200,50));
        
        settings.setOpaque(false);
        settings.setContentAreaFilled(false);
        settings.setBorderPainted(false);
        settings.setBorder(null);
        settings.setPreferredSize(new Dimension(200,50));
        
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setBorder(null);
        exit.setPreferredSize(new Dimension(200,50));
        
        start.setIcon(starter);
        settings.setIcon(sett);
        exit.setIcon(quit);
        
        logo.setIcon(logoo);
    }

    public JButton getStart() {
        return start;
    }

    public JButton getSettings() {
        return settings;
    }

    public JButton getExit() {
        return exit;
    }
    
    
}
