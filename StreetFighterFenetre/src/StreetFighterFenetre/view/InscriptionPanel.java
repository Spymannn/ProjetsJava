package StreetFighterFenetre.view;


import StreetFighterFenetre.controller.Window;
import StreetFighterFenetre.controller.WindowInscription;
import StreetFighterFenetre.modele.JoueurDB;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;

/**
 * Panneau d'inscription au jeu
 * @author Spymannn
 */

public class InscriptionPanel extends Pan{
    
    private JLabel pseudo,mdp,nom,prenom,email,dateN,img;
    private JTextField pseudoL,nomL,prenomL,emailL,dateNL;
    private JPasswordField mdpL;
    private ImageIcon pseudoI,mdpI,nomI,prenomI,emailI,dateNI,imgI,validerI,
            retourI;
    //private JComboBox avatars;
    private JButton valider,retour;
    private ImageIcon[] images;
    //création de toutes les avatars que le joueur peut choisir
    private String[] avatars = {"petitAkuma","petitBalrog","petitBlanka",
            "petitChunli","petitDarkhadou","petitDhalsim","petitKen",
            "petitMakoto","petitRyu","petitSakura","petitZangief"};
    private String[] avatarsNom = {"akuma","balrog","blanka",
            "chunli","darkhadou","dhalsim","ken",
            "makoto","ryu","sakura","zangief"};
    private JComboBox avatarsList;
    private ComboBoxRenderer renderer= new ComboBoxRenderer();
    private WindowInscription wi;

    public InscriptionPanel(WindowInscription wi){
        this.wi = wi;
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        logo.setMaximumSize(new Dimension(700,362));
        
        
        pseudoI = new ImageIcon("img/boutons/pseudo.png");
        mdpI = new ImageIcon("img/boutons/mdp.png");
        nomI = new ImageIcon("img/boutons/nom.png");
        prenomI = new ImageIcon("img/boutons/prenom.png");
        emailI = new ImageIcon("img/boutons/email.png");
        dateNI = new ImageIcon("img/boutons/dateN.png");
        imgI = new ImageIcon("img/boutons/avatar.png");
        
        validerI = new ImageIcon("img/boutons/valider.png");
        retourI = new ImageIcon("img/boutons/back.png");
        
        valider = new JButton();
        retour = new JButton();
        
        pseudoL = new JTextField();
        mdpL = new JPasswordField();
        nomL = new JTextField();
        prenomL = new JTextField();
        emailL = new JTextField();
        dateNL = new JTextField("DD/MM/YYYY");
        
        
        
        pseudo = new JLabel();
        mdp = new JLabel();
        nom = new JLabel();
        prenom = new JLabel();
        email = new JLabel();
        dateN = new JLabel();
        img = new JLabel();
        //intégration de toutes les avatars dans un tableau d'image
        images = new ImageIcon[avatars.length];
        Integer[] tabInt = new Integer[avatars.length];
        for (int i = 0; i < avatars.length; i++) {
            tabInt[i] = new Integer(i);
            images[i] = new ImageIcon("img/imagesJoueur/" + avatars[i] + ".png");
            if (images[i] != null) {
                images[i].setDescription(avatarsNom[i]);
            }
        }

        //Création du JComboBox
        avatarsList = new JComboBox(tabInt);
        renderer.setPreferredSize(new Dimension(65, 65));
        avatarsList.setRenderer(renderer);
        avatarsList.setMaximumRowCount(5);
        avatarsList.setMaximumSize(new Dimension(100,80));

        
        pseudo.setIcon(pseudoI);
        mdp.setIcon(mdpI);
        nom.setIcon(nomI);
        prenom.setIcon(prenomI);
        email.setIcon(emailI);
        dateN.setIcon(dateNI);
        img.setIcon(imgI);
        
        //positionnement
        Box b = Box.createHorizontalBox();
        Box b1 = Box.createHorizontalBox(); 
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();
        Box b5 = Box.createHorizontalBox();
        Box b6 = Box.createVerticalBox();
         
        pseudoL.setPreferredSize(new Dimension(200,30));
        mdpL.setPreferredSize(new Dimension(200,30));
        prenomL.setPreferredSize(new Dimension(200,30));
        nomL.setPreferredSize(new Dimension(200,30));
        emailL.setPreferredSize(new Dimension(200,30));
        dateNL.setPreferredSize(new Dimension(200,30));
        
        valider.setOpaque(false);
        valider.setContentAreaFilled(false);
        valider.setBorderPainted(false);
        valider.setBorder(null);
        valider.setPreferredSize(new Dimension(200,50));
        
        retour.setOpaque(false);
        retour.setContentAreaFilled(false);
        retour.setBorderPainted(false);
        retour.setBorder(null);
        retour.setPreferredSize(new Dimension(200,50));
        
        valider.setIcon(validerI);
        retour.setIcon(retourI);
        
        //Positionnement 
        b1.add(pseudo);
        b1.add(pseudoL);
        b1.add(mdp);
        b1.add(mdpL);
        b2.add(prenom);
        b2.add(prenomL);
        b2.add(nom);
        b2.add(nomL);
        b3.add(email);
        b3.add(emailL);
        b3.add(dateN);
        b3.add(dateNL);
        b4.add(img);
        b4.add(avatarsList);
        b5.add(valider);
        b5.add(retour);
        
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
        this.add(b6, gbc);
        
        valider.addActionListener(new Validation());
        
    }
    //Commentaires : voir ConnexionPanel
    class ComboBoxRenderer extends JLabel
            implements ListCellRenderer {

        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            int selectedIndex = ((Integer) value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            ImageIcon icon = images[selectedIndex];
            setIcon(icon);

            return this;
        }

        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }

    public JButton getValider() {
        return valider;
    }

    public JButton getRetour() {
        return retour;
    }
    
    //Listener de validation d'inscription
    class Validation implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == valider) {
                try{
                    JoueurDB j1 = null;
                    //Récupération de la date d'anniversaire
                    GregorianCalendar gc = new GregorianCalendar(
                            Integer.parseInt(dateNL.getText().substring(6)),
                            Integer.parseInt(dateNL.getText().substring(3,5))-1,
                            Integer.parseInt(dateNL.getText().substring(0,2)));
                    java.sql.Date d1 = new java.sql.Date(gc.getTimeInMillis());
                    
                    
                    j1 = new JoueurDB(pseudoL.getText(),prenomL.getText(), 
                            nomL.getText(), d1,emailL.getText(),
                            mdpL.getText(),avatarsNom[Integer.parseInt(avatarsList.getSelectedItem().toString())]);
                    j1.create();
                    pseudoL.setText("");
                    prenomL.setText("");
                    nomL.setText("");
                    emailL.setText("");
                    mdpL.setText("");
                    dateNL.setText("");
                    
                    
                    String msg = "Inscription validée\nFélicitations !\nBon jeu !";
                    JOptionPane.showMessageDialog(null,msg, 
                            "Inscription validée",1,
                            new ImageIcon("img/imagesJoueur/"+ 
                                    avatars[Integer.parseInt(avatarsList.getSelectedItem().toString())]+".png"));
                    wi.dispose();
                    Window w = new Window();
                }
                catch(Exception exc){
                    JOptionPane.showMessageDialog(null,"Erreur lors de l'inscription", 
                            "Inscription validée",1,
                            new ImageIcon("img/imagesJoueur/error.png"));
                }
                
            }
        }
    }
}
