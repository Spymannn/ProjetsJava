package StreetFighterFenetre.view;

import StreetFighterFenetre.modele.MapDB;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;
/**
 *
 * @author Spymannn
 * panneau du choix du terrain
 */
public class MapPanel extends Pan{
    private JButton rue,mall,dhalsimStage,ryuStage,back;
    private ImageIcon rueI,mallI,dhalsimStageI,ryuStageI,choixMapI,backI;
    private JLabel choixMap;
    
    
    public MapPanel(){
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        MapDB.setConnection(con);
        MapDB m1 = new MapDB(2);
        MapDB m2 = new MapDB(1);
        MapDB m3 = new MapDB(3);
        MapDB m4 = new MapDB(4);
        
        rue = new JButton();
        mall = new JButton();
        dhalsimStage = new JButton();
        ryuStage = new JButton();
        choixMap = new JLabel();
        
        choixMapI = new ImageIcon("img/fondEtLogo/choixMap.png");
        
        choixMap.setIcon(choixMapI);
        
        back = new JButton();
        backI = new ImageIcon("img/boutons/back.png");
        back.setIcon(backI);
        
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        
        try{
            m1.read();
            m2.read();
            m3.read();
            m4.read();
            rueI = new ImageIcon("img/map/"+m1.getNomMap()+"P.png");
            mallI = new ImageIcon("img/map/"+m2.getNomMap()+"P.png");
            dhalsimStageI = new ImageIcon("img/map/"+m3.getNomMap()+"P.png");
            ryuStageI = new ImageIcon("img/map/"+m4.getNomMap()+"P.png");
            
            rue.setIcon(rueI);
            mall.setIcon(mallI);
            dhalsimStage.setIcon(dhalsimStageI);
            ryuStage.setIcon(ryuStageI);
            
            rue.setOpaque(false);
            rue.setContentAreaFilled(false);
            rue.setBorderPainted(false);
            rue.setBorder(null);
            
            mall.setOpaque(false);
            mall.setContentAreaFilled(false);
            mall.setBorderPainted(false);
            mall.setBorder(null);
            
            dhalsimStage.setOpaque(false);
            dhalsimStage.setContentAreaFilled(false);
            dhalsimStage.setBorderPainted(false);
            dhalsimStage.setBorder(null);
            
            ryuStage.setOpaque(false);
            ryuStage.setContentAreaFilled(false);
            ryuStage.setBorderPainted(false);
            ryuStage.setBorder(null);


            
            Box b1 = Box.createVerticalBox();
            Box b2 = Box.createVerticalBox();
            Box b3 = Box.createVerticalBox();
            Box b4 = Box.createVerticalBox();
            Box b5 = Box.createHorizontalBox();
            
            
            b1.add(rue);
            b2.add(mall);
            b3.add(dhalsimStage);
            b4.add(ryuStage);
            
            b5.add(b1);
            b5.add(b2);
            b5.add(b3);
            b5.add(b4);
            
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);
            gbc.gridy = 1;
            this.add(choixMap, gbc);
            gbc.gridy = 2;
            this.add(b5, gbc);
            gbc.gridy = 3;
            this.add(back, gbc);
            back.setOpaque(false);
            back.setContentAreaFilled(false);
            back.setBorderPainted(false);
            back.setBorder(null);
            back.setPreferredSize(new Dimension(200, 50));
        }
        catch(Exception e){
        
        }
    }

    public JButton getRue() {
        return rue;
    }

    public JButton getMall() {
        return mall;
    }

    public JButton getDhalsimStage() {
        return dhalsimStage;
    }

    public JButton getRyuStage() {
        return ryuStage;
    }

    public JButton getBack() {
        return back;
    }
    
    
}
