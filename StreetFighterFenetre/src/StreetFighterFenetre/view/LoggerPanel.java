package StreetFighterFenetre.view;

/**
 *
 * @author Spymannn
 * pannel de login
 */
import StreetFighterFenetre.modele.JoueurDB;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;

public class LoggerPanel extends Pan{
    
    private JLabel avatar,pseudo,mdp;
    private JPasswordField password;
    private JButton connexion,annuler;
    private ImageIcon avatarI,mdpI,connexionI,annulerI;
    private String pseudoR,passR = "";
    private Font font;
    /**
     * Constructeur paramétré
     * @param ps 
     */
    public LoggerPanel(String ps){
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        //Connexion à la base de données
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        
        mdpI = new ImageIcon("img/boutons/mdp.png");
        connexionI = new ImageIcon("img/boutons/connexion.png");
        annulerI = new ImageIcon("img/boutons/back.png");
        avatar = new JLabel();
        mdp = new JLabel();
        connexion = new JButton();
        annuler = new JButton();
        
        password = new JPasswordField();
        password.setPreferredSize(new Dimension(200,30));
        
        mdp.setIcon(mdpI);
        connexion.setIcon(connexionI);
        annuler.setIcon(annulerI);
        this.pseudoR = ps;
        font = new Font("Verdana", Font.BOLD, 16);
        
        try{
            JoueurDB j1 = JoueurDB.recherchePseudoJoueur(ps);
            pseudo = new JLabel(j1.getPseudoJoueur());
            avatarI = new ImageIcon("img/imagesJoueur/"+j1.getImageJoueur()+".png");
            avatar.setIcon(avatarI);
            passR = j1.getMotDePasse();
            pseudo.setFont(font);
            pseudo.setForeground(new Color(0,0,0));
            
            Box b1 = Box.createHorizontalBox();
            Box b2 = Box.createHorizontalBox();
            Box b3 = Box.createHorizontalBox();
            Box b4 = Box.createHorizontalBox();
            Box b5 = Box.createHorizontalBox();
            Box b6 = Box.createVerticalBox();
            
            b1.add(avatar);
            b2.add(pseudo);
            b3.add(mdp);
            b3.add(password);
            b4.add(connexion);
            b5.add(annuler);
            b6.add(b1);
            b6.add(b2);
            b6.add(b3);
            b6.add(b4);
            b6.add(b5);
 
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);

            gbc.gridy = 1;
            this.add(b6,gbc);
            
            
            
            connexion.setOpaque(false);
            connexion.setContentAreaFilled(false);
            connexion.setBorderPainted(false);
            connexion.setBorder(null);
            connexion.setPreferredSize(new Dimension(200, 50));
            
            annuler.setOpaque(false);
            annuler.setContentAreaFilled(false);
            annuler.setBorderPainted(false);
            annuler.setBorder(null);
            annuler.setPreferredSize(new Dimension(200, 50));
        }
        catch(Exception e){
        }

    }
    public JButton getConnexion(){
        return connexion;
    }
    
    public JButton getBack(){
        return annuler;
    }
    public String getPseudoR(){
        return pseudoR;
    }
    
    public String getPassword(){
        return password.getText();
    }
    public String getPassR(){
        return passR;
    }
    public void setPassword(String passV){
        password.setText(passV);
    }
}
