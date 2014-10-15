package StreetFighterFenetre.view;

import StreetFighterFenetre.controller.WindowFight;
import StreetFighterFenetre.controller.WindowJeu;
import StreetFighterFenetre.modele.ImagePersoDB;
import StreetFighterFenetre.modele.JoueurDB;
import StreetFighterFenetre.modele.MapDB;
import StreetFighterFenetre.modele.PartieDB;
import StreetFighterFenetre.modele.PersonnageDB;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import myconnectionsfenetre.DBConnection;
/*
 * Creation d'une classe Panel qui permet de mettre n'importe quel
 * fond d'écran pour mon panel principal avec la taille que je veux 
 * 
 */

/**
 *
 * @author Spyman
 */
public class CombatPanel extends JPanel{
    private Image fondMap;
    private Image pl1;
    private Image pl2;
    private Image vita1;
    private Image vita2;
    private Rectangle tetePlayer1;
    private Rectangle corpsPlayer1;
    private Rectangle piedsPlayer1;
    private Rectangle tetePlayer2;
    private Rectangle corpsPlayer2;
    private Rectangle piedsPlayer2;
    private int j=1,k=1,u=1,p=1,l=1,m=1;
    private boolean okMouvementSeul=true,okPoing1=false,okPied1=false,okPoing2=false,okPied2=false,position=true;
    /**
     * Position des personnages, J1 = player1, J2 = player2
     * initialisation des paramètres
     */
    private int xTeteJ1,yTeteJ1,xCorpsJ1,yCorpsJ1,xPiedsJ1,yPiedsJ1;
    private int xTeteJ2,yTeteJ2,xCorpsJ2,yCorpsJ2,xPiedsJ2,yPiedsJ2;
    /**
     * Coup de poings des 2 joueurss
     */
    private int xPoing=0,yPoing=0,xPoing2=0,yPoing2=0;
    private Rectangle poing,poing2;
    /**
     * Coup de pieds des 2 joueurs
     */
    private int xPied1=0,yPied1=0,xPied2=0,yPied2=0;
    private Rectangle pied1,pied2;
    
    /**
     * Valeur random des phrases des 2 joueurs + image
     */
    private int rand1=0,rand2=0;
    private Image phrase1;
    
    /**
     * valeur des attaques
     */
    private int valeurAttack1,valeurAttack2;
    //Poings
    private int cpPg1,cpPg2;
    //Pieds
    private int cpPd1,cpPd2;
    //Defense
    private int def1,def2;
    //Vitalite
    private int vitaP1,vitaP2;
    
    private float diminution1,diminution2;
    /**
     * Score de la partie
     */
    private int scorePartie;
    /**
     * Initialisation des positions de vitalité
     */
    private int posYVita2;
    /**
     * Initialisation de la variable seconds2 qui permet d'envoyé 
     * le nombre de milliseconde de la journée à laquelle
     * on commence la partie
     */
    private float seconds2 = 0;
    
    //Instanciation des variables de classes DB
    private PersonnageDB player1,player2;
    private PartieDB partie;
    private MapDB map;
    private JoueurDB joueur;
    private ImagePersoDB player1I,player2I;
    
    //instanciation des variables pseudoR,j1,j2 et map
    private int j1,j2,nMap;
    private String pseudoR;
    private int idGamer;
    
    private WindowFight w;
    
    //Animation début combat
    private Image fight;
    private int fightJ = 0;
    
    //Animation fin de combat
    private Image ko;
    private int koK = 1;

    /**
     * Constructeur paramétré
     * @param pseudoR
     * @param j1
     * @param j2
     * @param nMap
     * @param w 
     */
    public CombatPanel(String pseudoR,int j1,int j2,int nMap,WindowFight w){
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        PersonnageDB.setConnection(con);
        PartieDB.setConnection(con);
        MapDB.setConnection(con);
        JoueurDB.setConnection(con);
        ImagePersoDB.setConnection(con);
        
        scorePartie = 0;
        this.w = w;
        
        this.j1 = j1;
        this.j2 = j2;
        this.pseudoR = pseudoR;
        this.nMap = nMap;
        
        player1 = new PersonnageDB(j1);
        player2 = new PersonnageDB(j2);
        map = new MapDB(nMap);
        try{
            idGamer = JoueurDB.recherchePseudoJoueur(pseudoR).getIdJoueur();
        }
        catch(Exception e){}
        /**
         * Création d'un nombre de seconds DES LE DEBUT
         * de partie d'un combat
         * qui sera entré dans la table Partie
         * afin de calculer le temps en secondes
         * de la partie
         */
        java.util.GregorianCalendar gc = new GregorianCalendar();
        java.sql.Date d1 = new java.sql.Date(gc.getTimeInMillis());
        float m = gc.get(java.util.Calendar.MINUTE);
        float s = gc.get(java.util.Calendar.SECOND);
        float h = gc.get(java.util.Calendar.HOUR_OF_DAY);
        
        seconds2 =  s+m*60+h*3600;
        
    }
    /**
     * méthode qui dessines toutes les images, fond, vitalité, personnages
     * ET mouvements
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
           
        try{
            
            player1.read();
            player2.read();
            map.read();
            //récupération du nom de toutes les images 
            //pour les personnages, inclus dans la bd
            ArrayList<ImagePersoDB> tab1 = ImagePersoDB.affichesImage(player1.getIdPerso());
            ArrayList<ImagePersoDB> tab2 = ImagePersoDB.affichesImage(player2.getIdPerso());

            cpPg1 = player1.getAttackPoing();
            cpPg2 = player2.getAttackPoing();

            cpPd1 = player1.getAttackPied();
            cpPd2 = player2.getAttackPied();

            def1 = player1.getDefense();
            def2 = player2.getDefense();

            //initialisation des données de placement 1 fois au début
            //et position = false après la 1ére fois
            //afin que les positions ne reste pas bloquées
            //à leur position de base
            if (position) {
                xTeteJ1 = 300;
                yTeteJ1 = this.getHeight() - 225;
                xCorpsJ1 = 275;
                yCorpsJ1 = yTeteJ1 + 30;
                xPiedsJ1 = 265;
                yPiedsJ1 = yTeteJ1 + 140;

                xTeteJ2 = this.getWidth() - 300;
                yTeteJ2 = this.getHeight() - 225;
                xCorpsJ2 = xTeteJ2 - 25;
                yCorpsJ2 = yTeteJ2 + 30;
                xPiedsJ2 = xTeteJ2 + 5;
                yPiedsJ2 = yTeteJ2 + 110;

                //Initialisation des coups de poings
                poing = new Rectangle(xPoing, yPoing, 10, 10);
                poing2 = new Rectangle(xPoing2, yPoing2, 10, 10);
                //Initialisation des coups de pieds
                pied1 = new Rectangle(xPied1, yPied1, 15, 15);
                pied2 = new Rectangle(xPied2, yPied2, 15, 15);
                //Initialisation des corps
                tetePlayer1 = new Rectangle(xTeteJ1, yTeteJ1, 30, 30);
                corpsPlayer1 = new Rectangle(xCorpsJ1, yCorpsJ1, 50, 100);
                piedsPlayer1 = new Rectangle(xPiedsJ1, yPiedsJ1, 80, 50);
                tetePlayer2 = new Rectangle(xTeteJ2, yTeteJ2, 30, 35);
                corpsPlayer2 = new Rectangle(xCorpsJ2, yCorpsJ2, 75, 80);
                piedsPlayer2 = new Rectangle(xPiedsJ2, yPiedsJ2, 80, 50);

                //Initialisation de vitalite2
                posYVita2 = this.getWidth() - 530;
                
                vitaP1 = player1.getVitalite();
                vitaP2 = player2.getVitalite();
                valeurAttack1 = 0;
                valeurAttack2 = 0;
                diminution1 = 0;
                diminution2 = 0;
                

                position = false;
            }
            //Fond de la map avec le choix effectué par l'utilisateur
            fondMap = ImageIO.read(new File("img/map/"+map.getNomMap()+".png"));
            //vitalité des 2 personnages
            vita1 = ImageIO.read(new File("img/combat/vitaJ1.png"));
            vita2 = ImageIO.read(new File("img/combat/vitaJ2.png"));
            /**
             * Images des personnages, suivant s'il ils donnent des coups de poings, des coups de pieds
             * s'ils sont du côté gauche, ou du côté droit, ou s'ils restent sur places.
             */
            if(!okPoing1 && !okPied1)
                pl1 = ImageIO.read(new File("img/combat/"+tab1.get(0).getNomFrame()+tab1.get(0).getTypeImage()+String.valueOf(j)+".png"));
            if(okPoing1)
                pl1 = ImageIO.read(new File("img/combat/"+tab1.get(1).getNomFrame()+tab1.get(1).getTypeImage()+String.valueOf(u)+".png"));
            if(okPied1)
                pl1 = ImageIO.read(new File("img/combat/"+tab1.get(2).getNomFrame()+tab1.get(2).getTypeImage()+String.valueOf(p)+".png"));
            if(!okPoing2 && !okPied2)
                pl2 = ImageIO.read(new File("img/combat/"+tab2.get(3).getNomFrame()+tab2.get(3).getTypeImage()+"Back"+String.valueOf(k)+".png"));
            if(okPoing2)
                pl2 = ImageIO.read(new File("img/combat/"+tab2.get(4).getNomFrame()+tab2.get(4).getTypeImage()+"Back"+String.valueOf(l)+".png"));
            if(okPied2)
                pl2 = ImageIO.read(new File("img/combat/"+tab2.get(5).getNomFrame()+tab2.get(5).getTypeImage()+"Back"+String.valueOf(m)+".png"));
            //Positionnement des x et y par rapport à la fenêtre
            
                    
            g.drawImage(fondMap,0,0,this.getWidth(),this.getHeight(),this);
            g.drawImage(pl1,(xTeteJ1-45),(yTeteJ1-10),pl1.getWidth(null),pl1.getHeight(null),this);
            g.drawImage(pl2,(xTeteJ2-65),(yTeteJ2-10),pl2.getWidth(null),pl2.getHeight(null),this);
            
            //Me permet de dessiner la barre de vie qui diminue à chaque coup donné
            g.drawImage(vita1,30,15,vita1.getWidth(null)-(int)((diminution2/player1.getVitalite())*500),vita1.getHeight(null),this);
            g.drawImage(vita2,posYVita2+(int)((diminution1/player2.getVitalite())*500),15,vita2.getWidth(null)-(int)((diminution1/player2.getVitalite())*500),vita2.getHeight(null),this);
            /**************/
            /**
             * TEST
             */
            //System.out.println("j = "+j+" k = "+k);
            //test des rectangles en rouge pour les collisions
            //g.setColor(Color.red);
            //Player 1
            //g.drawRect(xTeteJ1,yTeteJ1,30,30);//tete Ryu
            //g.drawRect(xCorpsJ1,yCorpsJ1,50,100);//corps Ryu
            //g.drawRect(xPiedsJ1,yPiedsJ1,80,50);//pieds Ryu
            //g.setColor(Color.YELLOW);
            //Player 2
            //g.drawRect(xTeteJ2,yTeteJ2,30,35);//tete Akuma
            //g.drawRect(xCorpsJ2,yCorpsJ2,75,80);//corps Akuma
            //g.drawRect(xPiedsJ2,yPiedsJ2,80,50);//pieds Akuma
            

            /**
             * IF qui me permettent 
             * de mettre en mouvement les personnages
             * suivant coup de poings, coup de pied OU mouvement de base
             */
            if(okMouvementSeul){
                this.setK();
                this.setJ();
                repaint();
                pause();
            }
            if(okPoing1){
                this.setU();
                repaint();
                pause();
            }
            if(okPied1){
                this.setP();
                repaint();
                pause();
            }
            if(okPoing2){
                this.setL();
                repaint();
                pause();
            }
            if(okPied2){
                this.setM();
                repaint();
                pause();
            }
            /**
             * TEST !!!!!!!!!!!!!!!!!!!!!
             */
            /**
             * Ici, ça ne doit pas être un random mais plutôt un 
             * if valeurVita < que intel et if ce random, on 
             * met cette phrase
             */
            //rand1 = (int)(Math.random()*10)+1;
            //rand1=1;
            /*if(rand1==1){
                phrase1 = ImageIO.read(new File("images\\phrases\\phrase1.png"));
                g.drawImage(phrase1,xTeteJ1-65,yTeteJ1-65, this);
            }
            if(rand1==2){
                phrase1 = ImageIO.read(new File("images\\phrases\\phrase2.png"));
                g.drawImage(phrase1,xTeteJ1-65,yTeteJ1-65, this);
            }
            if(rand1==3){
                phrase1 = ImageIO.read(new File("images\\phrases\\phrase3.png"));
                g.drawImage(phrase1,xTeteJ1-65,yTeteJ1-65, this);
            }*/
            /**
             * permet d'afficher un message au début de combat pour dire FIGHT
             */
            if(fightJ==0){
                fight = ImageIO.read(new File("img/combat/fight.png"));
                g.drawImage(fight,(int)this.getWidth()/3,(int)this.getHeight()/3,fight.getWidth(null),fight.getHeight(null),this);
                pause();
                fightJ = 1;
            }
            /**
             * permet d'afficher un message en FIN de combat pour dire KO
             */
            if(koK==0){
                ko = ImageIO.read(new File("img/combat/ko.png"));
                g.drawImage(ko,(int)this.getWidth()/3,(int)this.getHeight()/3,fight.getWidth(null),fight.getHeight(null),this);
                pause();
                pause();
                pause();
                pause();
            }

        }
	catch(IOException e){
            e.printStackTrace();
	}
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * méthode de mouvements
     */
    public void setJ(){
        this.j++;
        if(this.j==9)
            this.j=1;
    }
    public void setK(){
        this.k++;
        if(this.k==9)
            this.k=1;
    }
    public void setU(){
        this.u++;
        if(this.u==6){
            this.u=1;
            okPoing1=false;
        }
    }
    public void setP(){
        this.p++;
        if(this.p==5){
            this.p=1;
            okPied1=false;
        }
    }
    public void setL(){
        this.l++;
        if(this.l==6){
            this.l=1;
            okPoing2=false;
        }
    }
    public void setM(){
        this.m++;
        if(this.m==5){
            this.m=1;
            okPied2=false;
        }
    }
    /**
     * Méthode qui me permettent de mettre une localisation
     * pour les coups de pieds du joueur 1
     * @param x
     * @param y 
     */
    public void piedsJ1(int x,int y){
        //System.out.println("Avant pied Ryu "+pied1.getLocation());
        xPied1 = x +125;
        yPied1 = y - 25;
        pied1.setLocation(xPied1, yPied1);
        this.verifeToucheJ1(pied1,1750,cpPd1);
        //System.out.println("Apres pied Ryu : "+pied1.getLocation());
        okPied1 = true;
    }
    /**
     * Méthode qui me permettent de mettre une localisation
     * pour les coups de poings du joueur 1
     * @param x
     * @param y 
     */
    public void poingsJ1(int x, int y) {
        //System.out.println("Avant : "+poing.getLocation()); 
        xPoing = x + 115;
        yPoing = y +5;
        poing.setLocation(xPoing, yPoing);
        this.verifeToucheJ1(poing,2000,cpPg1);
        //System.out.println("Après : " + poing.getLocation());
        okPoing1 = true;
    }
    /**
     * Méthode qui me permettent de mettre une localisation
     * pour les coups de poings du joueur 2
     * @param x
     * @param y 
     */
    public void poingsJ2(int x, int y){
        //System.out.println("Akuma attack : "+poing2.getLocation());
        xPoing2 = x - 30;
        yPoing2 = y - 5;
        poing2.setLocation(xPoing2, yPoing2);
        this.verifeToucheJ2(poing2,cpPg2);
        okPoing2 = true;
    }
    /**
     * Méthode qui me permettent de mettre une localisation
     * pour les coups de pieds du joueur 2
     * @param x
     * @param y 
     */
    public void piedsJ2(int x,int y){
        xPied2 = x - 40;
        yPied2 = y + 20;
        pied2.setLocation(xPied2,yPied2);
        this.verifeToucheJ2(pied2,cpPd2);
        okPied2 = true;
    }
    /**
     * Méthode qui me permet de bouger le personnage 1 vers la droite
     */
    public void bougeJ1Droite(){
        
        if(xPiedsJ1+80<xPiedsJ2){   
            xCorpsJ1+=10;
            xTeteJ1+=10;
            xPiedsJ1+=10;
            tetePlayer1.setLocation(xTeteJ1,yTeteJ1);
            corpsPlayer1.setLocation(xCorpsJ1,yCorpsJ1);
            piedsPlayer1.setLocation(xPiedsJ1,yPiedsJ1);
        }
   
    }
    /**
     * Méthode qui me permet de bouger le personnage 1 vers la gauche
     */
    public void bougeJ1Gauche(){

        xCorpsJ1-=10;
        xTeteJ1-=10;
        xPiedsJ1-=10;
        tetePlayer1.setLocation(xTeteJ1,yTeteJ1);
        corpsPlayer1.setLocation(xCorpsJ1,yCorpsJ1);
        piedsPlayer1.setLocation(xPiedsJ1,yPiedsJ1);

        
    }
    /************************************/
    /**
     * Méthode qui me permet de bouger le joueur 2 vers la droite
     */
    public void bougeJ2Droite(){

        
        xCorpsJ2+=10;
        xTeteJ2+=10;
        xPiedsJ2+=10;
        tetePlayer2.setLocation(xTeteJ2,yTeteJ2);
        corpsPlayer2.setLocation(xCorpsJ2,yCorpsJ2);
        piedsPlayer2.setLocation(xPiedsJ2,yPiedsJ2);
        
  
        
    }
    /**
     * Méthode qui me permet de bouger le joueur 2 vers la gauche
     */
    public void bougeJ2Gauche(){

        if(xPiedsJ2>xPiedsJ1+80){
            xCorpsJ2-=10;
            xTeteJ2-=10;
            xPiedsJ2-=10;
            tetePlayer2.setLocation(xTeteJ2,yTeteJ2);
            corpsPlayer2.setLocation(xCorpsJ2,yCorpsJ2);
            piedsPlayer2.setLocation(xPiedsJ2,yPiedsJ2);
        }

    }
  
    /*********************************/
    /**
     * verification si un coup de poing OU un coup de pied
     * du joueur 1 a touché le joueur 2
     * @param r
     * @param nbre
     * @param valeurAttack 
     */
   public void verifeToucheJ1(Rectangle r,int nbre,int valeurAttack){
       if(tetePlayer2.intersects(r) || corpsPlayer2.intersects(r) || piedsPlayer2.intersects(r)){
           //Me permet de diminuer la vitalité
           //valeurAttack1 = valeurAttack;
           
           if(valeurAttack>=vitaP2){
               valeurAttack = vitaP2+1;
               
           }
           diminution1+=valeurAttack;
           vitaP2-=valeurAttack;
 
           scorePartie+=nbre;

           
           if(vitaP2<0){
               koK = 0;
               JOptionPane.showMessageDialog(null,"Vous avez gagné\nPoints : "+scorePartie,"YOU WIN !",0,new ImageIcon("img/combat/win.png"));
               this.enable(false);
               
               try{
                   partie = new PartieDB(nMap,scorePartie,j1,idGamer,j2,seconds2);
                   partie.create();
               }catch(Exception e){
                   e.printStackTrace();
               }
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               w.dispose();
               WindowJeu j = new WindowJeu(pseudoR);
               
           }
           
       }
   }

   /**
     * verification si un coup de poing OU un coup de pied
     * du joueur 2 a touché le joueur 1
     * @param r
     * @param valeurAttack 
     */
    public void verifeToucheJ2(Rectangle r,int valeurAttack){
       if(tetePlayer1.intersects(r) || corpsPlayer1.intersects(r) || piedsPlayer1.intersects(r)){
           if(valeurAttack>=vitaP1){
               valeurAttack = vitaP1+1;
               
           }
           diminution2+=valeurAttack;
           vitaP1-=valeurAttack;
           
           if(vitaP1<0){
               koK = 0;
               JOptionPane.showMessageDialog(null,"Vous avez perdu\nPoints : "+scorePartie,"YOU LOSE !",0,new ImageIcon("img/combat/lose.png"));
               this.enable(false);
               try{
                   partie = new PartieDB(nMap,scorePartie,j1,idGamer,j2,seconds2);
                   partie.create();
               }catch(Exception e){
                   e.printStackTrace();
               }
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               w.dispose();
               WindowJeu j = new WindowJeu(pseudoR);
           }
       
       }
   }
   
    public int getxTeteJ1() {
        return xTeteJ1;
    }

    public int getyTeteJ1() {
        return yTeteJ1;
    }

    public int getxCorpsJ1() {
        return xCorpsJ1;
    }

    public int getyCorpsJ1() {
        return yCorpsJ1;
    }

    public int getxPiedsJ1() {
        return xPiedsJ1;
    }

    public int getyPiedsJ1() {
        return yPiedsJ1;
    }

    public int getxCorpsJ2() {
        return xCorpsJ2;
    }

    public int getyCorpsJ2() {
        return yCorpsJ2;
    } 
    /**
     * méthode qui me permet de faire une pause
     */
    private void pause() {
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}