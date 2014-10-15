package StreetFighterFenetre.view;
import StreetFighterFenetre.controller.WindowLogger;
import StreetFighterFenetre.modele.JoueurDB;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.*;
import myconnectionsfenetre.DBConnection;
/**
 *
 * @author Spymannn
 * Panel de connexion
 */
public class ConnexionPanel extends Pan{
    private JButton connect,inscription,quitter;
    private JTextField login,password;
    private JLabel log,pass;
    protected ArrayList<JoueurDB> gamers;
    private ArrayList<String> nomImageGamers = new ArrayList<String>();
    private ArrayList<String> pseudoGamers = new ArrayList<String>();
    private ImageIcon[] images;
    private int taille;
    private ImageIcon connexion, insc,quitterI;
    private JComboBox listeJoueur = null;
    private Integer[] intArray;
    private final Window w;
    /**
     * Constructeur paramétré
     * @param w 
     */
    public ConnexionPanel(Window w){
        this.w = w;
        ImageIcon logoo = new ImageIcon("img\\logo.png");
        JLabel logo = new JLabel();
        logo.setIcon(logoo);
        quitterI = new ImageIcon("img/boutons/quitter.png");
        quitter = new JButton();
        quitter.setIcon(quitterI);
        //Connexion à la base de données
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        //Ici, on met dans un ArrayList les images et les pseudos des joueurs
        //qu'on va récupérer afin de pouvoir les insérers dans le combobox
        try {
            gamers = JoueurDB.afficheToutJoueur();
            for(JoueurDB g : gamers){
                //System.out.println(g.getImageJoueur());
                //System.out.println(g.getPseudoJoueur());
                nomImageGamers.add(g.getImageJoueur());
                pseudoGamers.add(g.getPseudoJoueur());
            }
            //System.out.println("taille 1 : "+gamers.size());
            taille = gamers.size();
        } catch (Exception ex) {
            System.out.println("bug : "+ex.getMessage());
        }
        //création d'un tableau d'image avec toutes les images des joueurs
        // et création d'un tableau avec leur numéro
        //On insère le tout dans un combobox
        images = new ImageIcon[taille];
        Integer[] intArray = new Integer[taille];
        for (int i = 0; i < taille; i++) {
            intArray[i] = i;
            images[i] = new ImageIcon("img/imagesJoueur/" + nomImageGamers.get(i) + ".png");
            if (images[i] != null) {
                images[i].setDescription(pseudoGamers.get(i));
            }
        }
        
        //création du combobox
        listeJoueur = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(200, 150));
        listeJoueur.setRenderer(renderer);
        listeJoueur.setMaximumRowCount(3);
        listeJoueur.revalidate();
        listeJoueur.repaint();
        
        connect = new JButton();
        inscription = new JButton();
        
        connexion = new ImageIcon("img/boutons/connexion.png");
        insc = new ImageIcon("img/boutons/inscription.png");
        
        connect.setOpaque(false);
        connect.setContentAreaFilled(false);
        connect.setBorderPainted(false);
        connect.setBorder(null);
        connect.setPreferredSize(new Dimension(200,50));
        
        inscription.setOpaque(false);
        inscription.setContentAreaFilled(false);
        inscription.setBorderPainted(false);
        inscription.setBorder(null);
        inscription.setPreferredSize(new Dimension(200,50));
        
        quitter.setOpaque(false);
        quitter.setContentAreaFilled(false);
        quitter.setBorderPainted(false);
        quitter.setBorder(null);
        quitter.setPreferredSize(new Dimension(200,50));
        
        connect.setIcon(connexion);
        inscription.setIcon(insc);
 
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Box b1 = Box.createHorizontalBox();

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(logo,gbc);
        gbc.gridx = 0;
        gbc.gridy = -1;
        gbc.gridheight = 20;
        gbc.gridwidth = 200;
        if(taille >0)
            this.add(listeJoueur,gbc);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridy = -2;
        if(taille > 0 )
            this.add(connect,gbc);
        this.add(inscription,gbc);
        gbc.gridy = -3;
        this.add(quitter, gbc);
        
        connect.addActionListener(new Envoie());

    }
    public JButton getQuitter(){
        return quitter;
    }
    public JButton getInscription(){
        return inscription;
    }
    class ComboBoxRenderer extends JLabel
            implements ListCellRenderer {

        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /* Cette méthode retrouve l'image et le texte 
         correspondant au valeur selectionnée et retournes le label, 
         le modifie et affiches le texte et l'image
         */
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

            //met à jour l'icon et le texte.
            ImageIcon icon = images[selectedIndex];
            String pet = pseudoGamers.get(selectedIndex);
            setIcon(icon);
            if (icon != null) {
                setText(pet);
                setFont(list.getFont());
            } else {
                setUhOhText(pet + " (pas d'image disponible)",
                        list.getFont());
            }
            return this;
        }

        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) {
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }
    /**
     * ActionListener qui permet d'envoyé les données
     */
    public class Envoie implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == connect) {
                w.dispose();
                WindowLogger wl = new WindowLogger(pseudoGamers.get(listeJoueur.getSelectedIndex()));
            }
        }
    }
    
    
}
