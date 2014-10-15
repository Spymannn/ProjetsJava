package StreetFighterFenetre.view;
import StreetFighterFenetre.modele.PersonnageDB;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;
/**
 *
 * @author Spymannn
 * Panneau qui hérite de PAN
 * et qui permet de choisir son personnage et le personnage adverse
 */
public class ChoixPersoPanel extends Pan{
    private JButton ryuB,dhalsimB,back;
    private ImageIcon ryuI,dhalsimI,j1,j2,backI;
    private JLabel player;
    public int gamer = 1;
    /**
     * Constructeur paramétré avec le type qui est le personnage choisit
     * @param type 
     */
    public ChoixPersoPanel(int type){
        /**
         * Connexion à la  base de données Oracle
         */
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        PersonnageDB.setConnection(con);
        //Création des 2 personnages suivant l'identifiant rentré(type)
        PersonnageDB ryu = new PersonnageDB(1);
        PersonnageDB dhalsim = new PersonnageDB(2);
        //Création d'un logo StreetFighter
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        //Création d'un bouton retour
        back = new JButton();
        backI = new ImageIcon("img/boutons/back.png");
        back.setIcon(backI);
        //création des images de joueurs
        player = new JLabel();
        j1 = new ImageIcon("img/fondEtLogo/player1.png");
        j2 = new ImageIcon("img/fondEtLogo/player2.png");
        
        try{
            if(type==1){
                player.setIcon(j1);
            }
            if(type==2){
                player.setIcon(j2);
            }
            ryu.read();
            dhalsim.read();
            //Créationd es boutons par rapports aux personnages dans la 
            //base de données Oracle
            ryuI = new ImageIcon("img/boutons/"+ryu.getNomPerso()+".png");
            dhalsimI = new ImageIcon("img/boutons/"+dhalsim.getNomPerso()+".png");
            ryuB = new JButton();
            dhalsimB = new JButton();
            //mise en image des boutons avec le nom des personnages
            //qui sont dans la base de données
            ryuB.setIcon(ryuI);
            dhalsimB.setIcon(dhalsimI);
            
            //mise en opaque etc des boutons afin que cela soit beaucoup
            //plus agréable à l'oeil
            ryuB.setOpaque(false);
            ryuB.setContentAreaFilled(false);
            ryuB.setBorderPainted(false);
            ryuB.setBorder(null);
            
            dhalsimB.setOpaque(false);
            dhalsimB.setContentAreaFilled(false);
            dhalsimB.setBorderPainted(false);
            dhalsimB.setBorder(null);
            
            //Création de box afin de placer les boutons    
            Box b1 = Box.createVerticalBox();
            Box b2 = Box.createVerticalBox();
            Box b3 = Box.createHorizontalBox();
            
            //placement 
            b1.add(ryuB);
            b2.add(dhalsimB);
            
            b3.add(b1);
            b3.add(b2);
            
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);
            gbc.gridy = 1;
            this.add(player, gbc);
            gbc.gridy = 2;
            this.add(b3, gbc);
            gbc.gridy = 3;
            this.add(back, gbc);
            
            back.setOpaque(false);
            back.setContentAreaFilled(false);
            back.setBorderPainted(false);
            back.setBorder(null);
            back.setPreferredSize(new Dimension(200, 50));
        }
        catch(Exception e){}  
    }
    /**
     * envoie des JButton
     * @return 
     */
    public JButton getRyuB(){
        return ryuB;
    }
    public JButton getDhalsimB(){
        return dhalsimB;
    }

    public JButton getBack() {
        return back;
    }
    
    
}
