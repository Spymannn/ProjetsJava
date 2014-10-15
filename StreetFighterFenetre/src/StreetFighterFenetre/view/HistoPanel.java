package StreetFighterFenetre.view;
import StreetFighterFenetre.modele.JoueurDB;
import StreetFighterFenetre.modele.MapDB;
import StreetFighterFenetre.modele.PartieDB;
import StreetFighterFenetre.modele.PersonnageDB;
import StreetFighterFenetre.view.Pan;
import java.awt.BorderLayout;
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
 * Panneau pour afficher l'historique des parties
 * pour un joueur en particulier
 */
public class HistoPanel extends Pan{
    private JTable historique;
    private Object[][] donnees;
    private JButton back;
    private ImageIcon backI;
    private Font font;
    /**
     * constructeur paramétré
     * @param ps 
     */
    public HistoPanel(String ps){
        
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        back = new JButton();
        backI = new ImageIcon("img/boutons/back.png");
        back.setIcon(backI);
        font = new Font("Verdana", Font.BOLD, 14);
        
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        PartieDB.setConnection(con);
        PersonnageDB.setConnection(con);
        MapDB.setConnection(con);
        MapDB m1 = null;
        PersonnageDB p1 = null,p2 = null;
        boolean ok = true;
        /**
         * recherche du joueur par pseudo (ps)
         * et d'un arrayList parties qui affiche toutes les infos
         * de partie du joueur choisit
         * et mets toutes les données dans un JTable tant que < 15
         */
        try{
            JoueurDB j1 = JoueurDB.recherchePseudoJoueur(ps);
            ArrayList<PartieDB> parties = PartieDB.afficheInfos(j1.getIdJoueur());

            String[] entetes = {"Pseudo","Map","Date partie","Perso utilisé","Adversaire","Durée","Score"};
            int i = 0;
            int j = parties.size();
            //Condition afin de ne pas devoir trop afficher, l'historique affichera les 15 dernières parties seulement
            //si il y a plus de 15 parties jouées, seules les 15 dernières seront affichées
            if(j>15)
                j=15;
            Object[][] donnees = new Object[j][7];
            while(i<j){
                PartieDB p = new PartieDB(parties.get(i).getIdPartie());
                p.read();
                m1 = new MapDB(p.getIdMap());
                m1.read();
                p1 = new PersonnageDB(p.getIdPersoUtilise());
                p1.read();
                p2 = new PersonnageDB(p.getIdPersoAdverse());
                p2.read();
                
                donnees[i][0] = ps;
                donnees[i][1] = m1.getNomMap();
                donnees[i][2] = p.getDatePartie();
                donnees[i][3] = p1.getNomPerso();
                donnees[i][4] = p2.getNomPerso();
                donnees[i][5] = p.getDebutPartie()+" s";
                donnees[i][6] = p.getScorePartie();
                
                i++;
            }
            historique = new JTable(donnees, entetes);
            historique.getTableHeader().setFont(font);
            historique.getColumnModel().getColumn(0).setPreferredWidth(100);
            historique.getColumnModel().getColumn(1).setPreferredWidth(100);
            historique.getColumnModel().getColumn(2).setPreferredWidth(120);
            historique.getColumnModel().getColumn(3).setPreferredWidth(120);
            historique.getColumnModel().getColumn(4).setPreferredWidth(120);
            historique.getColumnModel().getColumn(5).setPreferredWidth(80);
            historique.getColumnModel().getColumn(6).setPreferredWidth(100);
            historique.getTableHeader().setBackground(Color.black);
            historique.getTableHeader().setForeground(Color.white);
            historique.setBackground(Color.black);
            historique.setForeground(Color.white);
            historique.setEnabled(false);

            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);
            gbc.gridy = 1;
            this.add(historique.getTableHeader(), gbc);
            gbc.gridy = 2;
            this.add(historique, gbc);
            gbc.gridy = 3;
            this.add(back, gbc);
            
           

        }
        /**
         * Gestion de l'exception au cas où il n'y aura pas encore de partie
         * si le joueur est un nouveau arrivé sur le jeu
         * le programme affichera "pas de données"
         */
        catch(Exception e){
            JLabel error = new JLabel();
            ImageIcon errorI = new ImageIcon("img/fondEtLogo/noDonnees.png");
            error.setIcon(errorI);
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            this.add(logo, gbc);
            gbc.gridy = 1;
            this.add(error, gbc);
            gbc.gridy = 2;
            this.add(back, gbc);
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

