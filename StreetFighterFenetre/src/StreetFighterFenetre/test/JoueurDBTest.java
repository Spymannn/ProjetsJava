/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StreetFighterFenetre.test;

import StreetFighterFenetre.modele.*;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import myconnectionsfenetre.DBConnection;

/**
 *
 * @author Spymannn
 */
public class JoueurDBTest {
    
    
    public static void main(String[] args) {
        DBConnection dbc=new DBConnection();
        Connection con = dbc.getConnection();
        if(con==null){
            System.out.println("Connexion impossible");
            System.exit(0);
        }
        JoueurDB.setConnection(con);
        MapDB.setConnection(con);
        PartieDB.setConnection(con);
        PersonnageDB.setConnection(con);
        ImagePersoDB.setConnection(con);
        
        JoueurDB j1 = null,j2 = null;
        MapDB m1 = null;
        PersonnageDB p1 = null;
        PartieDB partie1 = null,partie2 = null;
        ImagePersoDB i1 = null;
        
        
        /*try{
            GregorianCalendar gc = new GregorianCalendar();
            java.sql.Date d1 = new java.sql.Date(gc.getTimeInMillis());
            
            System.out.println("Test ajout d'un joueur");
            System.out.println("======================");
            j1 = new JoueurDB("Spyman5","Sam","Hanini",d1,"test5@live.be","test","chunli");
            j1.create();
            System.out.println("Bon ajout, okay");     
        }
        catch(Exception e){
            System.err.println("Erreur dans l'ajout"+e);       
        }*/
        
        /*try{
            System.out.println("Test lecture d'un joueur");
            System.out.println("========================");
            j1 = new JoueurDB(21);
            j1.read();
            System.out.println("Test 1 : "+j1);
               
        }
        catch(Exception e){
            System.err.println("Erreur dans la lecture : "+e);       
        }*/
        
        /*try{
            System.out.println("Test lecture d'un joueur via pseudo");
            System.out.println("===================================");
            j1 = new JoueurDB();
            System.out.println("Test 1 : "+j2);
            j2 = j1.recherchePseudoJoueur("SpymanImage");
            System.out.println("Test 2 : "+j2);
            
                
        }
        catch(Exception e){
            System.err.println("Erreur dans la lecture : "+e);       
        }*/
        /*try{
            System.out.println("Test lecture de tous les joueurs");
            System.out.println("================================");
            ArrayList<JoueurDB> gamers = JoueurDB.afficheToutJoueur();
            for(JoueurDB g : gamers){
                System.out.println(g);
                System.out.println("Pseudo : "+g.getPseudoJoueur());
            }
            System.out.println("taille : "+gamers.size());
               
        }
        catch(Exception e){
            System.err.println("Erreur dans la lecture : "+e);       
        }*/
        /*try{
            System.out.println("Test d'update du mdp d'un joueur");
            System.out.println("================================");
            j1 = new JoueurDB(1);
            System.out.println("Test 1 : "+j1);
            j1.read();
            System.out.println("Test 2 : "+j1);
            j1.setMotDePasse("testUpdate2");
            j1.update();
            System.out.println("Test 3 : "+j1);
        }
        catch(Exception e){
            System.err.println("MAUVAIS!!! Exception de mise à jour "+e);
        }*/
        /*try{
            System.out.println("Test lecture d'une map");
            System.out.println("========================");
            m1 = new MapDB(1);
            System.out.println("Test 1 : "+m1);
            m1.read();
            System.out.println("Test 2 : "+m1);
        }
        catch(Exception e){
            System.err.println("Erreur dans la lecture : "+e);       
        }*/
        /*try{
            System.out.println("Test lecture d'un personnage");
            System.out.println("============================");
            p1 = new PersonnageDB(2);
            System.out.println("Test 1 : "+p1);
            p1.read();
            System.out.println("Test 2 : "+p1);
        }
        catch(Exception e){
            System.err.println("Erreur dans la lecture : "+e);       
        }*/
        try{
            java.util.GregorianCalendar gc = new GregorianCalendar();
            java.sql.Date d1 = new java.sql.Date(gc.getTimeInMillis());
            float m = gc.get(java.util.Calendar.MINUTE);
            float s = gc.get(java.util.Calendar.SECOND);
            float h = gc.get(java.util.Calendar.HOUR_OF_DAY);
            
            System.out.println("Minute "+m);
            System.out.println("seconde "+s);
            System.out.println("heure "+h);
            //long seconds = System.currentTimeMillis();
            float seconds2 = 0;
            seconds2 =  s+m*60+h*3600;
            System.out.println("Nombre de secondes écoulées : " + seconds2);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            //DateFormat fmt = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.FRANCE);
            //String datefmt = fmt.format(d1);
            //System.out.println(datefmt);
            //test d'ajout
            System.out.println("Test ajout d'une partie, lecture via ID ET lecture via IdJoueur ET top 10");
            System.out.println("========================================================================");
            //partie1 = new PartieDB(1,3000,1,2,2,seconds2);
            //partie1.create();
            //test de lecture 1
            //partie2 = new PartieDB(38);
            //partie2.read();
            //System.out.println(partie2);
            //test de lecture 2
            //ArrayList<PartieDB> parties = PartieDB.afficheInfos(2);
            //for(PartieDB t : parties){
            //    System.out.println(t);
            //}
            //test de lecture 3
            //partie1 = PartieDB.afficheScoreMax(1);
            //System.out.println(partie1);
            //test de top 10 (lecture 4)
            ArrayList<PartieDB> parties = PartieDB.topTenScore();
            for(PartieDB t : parties){
                System.out.println(t);
            }
            
        }
        catch(Exception e){
            System.err.println("Erreur : "+e);
            e.printStackTrace();
        }
        
        /*try{
            System.out.println("Test lecture image par IDFrame");
            System.out.println("==============================");
            i1 = new ImagePersoDB(1);
            i1.read();
            System.out.println(i1);
        }
        catch(Exception e){
            System.err.println("Erreur : "+e);
        }*/
        /*try{
            System.out.println("Test lecture image par IdPerso");
            System.out.println("==============================");
            ArrayList<ImagePersoDB> images = ImagePersoDB.affichesImage(2);
            //System.out.println(images.get(0).getNomFrame()+images.get(0).getTypeImage());
            for(ImagePersoDB i : images){
                System.out.println(i);
                //System.out.println("Test cote : "+i.getCoteImage());
                if(i.getCoteImage()==null)
                    System.out.println("ok c'est null");
            }
        }
        catch(Exception e){
            System.err.println("Erreur : "+e);
        }*/
        
        
        
    }
    
}
