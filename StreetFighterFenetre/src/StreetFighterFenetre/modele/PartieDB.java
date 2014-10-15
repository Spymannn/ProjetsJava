package StreetFighterFenetre.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Spymannn
 */
public class PartieDB extends Partie implements CRUD{
    protected static Connection dbConnect = null;
    /**
     * Constructeur par défaut
     */
    public PartieDB(){}
    /**
     * Constructeur paramétré complet
     * @param idPartie
     * @param idMap
     * @param datePartie
     * @param scorePartie
     * @param idPersoUtilise
     * @param idJoueur
     * @param idPersoAdverse
     * @param debutPartie 
     */
    public PartieDB(int idPartie, int idMap, Date datePartie, int scorePartie,
            int idPersoUtilise, int idJoueur, int idPersoAdverse,float debutPartie){
        super(idPartie,idMap,datePartie,scorePartie,idPersoUtilise,idJoueur,idPersoAdverse,
                debutPartie);
        
    }
    public PartieDB(int idMap, int scorePartie,
            int idPersoUtilise, int idJoueur, int idPersoAdverse,float debutPartie){
        super(0,idMap,null,scorePartie,idPersoUtilise,idJoueur,idPersoAdverse,
                debutPartie);
        
    }
    /**
     * Constructeur paramétré uniquement avec l'ID
     * @param idPartie 
     */
    public PartieDB(int idPartie){
        super(idPartie,0,null,0,0,0,0,0);
    }
    /**
     * Méthode statique qui permet de partager
     * la connexion entre tous les objets de PartieDB
     * @param nouvdbConnect 
     */
    public static void setConnection(Connection nouvdbConnect){
        dbConnect=nouvdbConnect;
    }
    /**
     * Création d'une partie
     * @throws Exception 
     */
    @Override
    public void create() throws Exception {
        CallableStatement cstmt=null;
        try{
            String rep="call createPartie(?,?,?,?,?,?)";
            cstmt=dbConnect.prepareCall(rep);
            cstmt.setInt(1,idMap);
            cstmt.setInt(2,scorePartie);
            cstmt.setInt(3,idPersoUtilise);
            cstmt.setInt(4,idJoueur);
            cstmt.setInt(5,idPersoAdverse);
            cstmt.setFloat(6,debutPartie);
            cstmt.executeUpdate(); 
        }
        catch(Exception e){
            throw new Exception("Erreur dans la création de partie "+e.getMessage());
            //e.printStackTrace();
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Lecture par identifiant de partie
     * @throws Exception 
     */
    @Override
    public void read() throws Exception {
        String rep="{?=call readPartie1(?)}";
        CallableStatement cstmt=null;
        
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idPartie);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.idMap = rs.getInt("idMap");
                this.datePartie = rs.getDate("datePartie");
                this.scorePartie = rs.getInt("scorePartie");
                this.idPersoUtilise = rs.getInt("idPersoUtilise");
                this.idJoueur = rs.getInt("idJoueur");
                this.idPersoAdverse = rs.getInt("idPersoAdverse");
                this.setDebutPartie(rs.getFloat("dureePartie"));
            }
            else
                throw new Exception("Numéro de partie inconnu");
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }
    }
    /**
     * Affiche toutes les infos d'une partie via identifiant d'un joueur
     * @param idJoueur
     * @return
     * @throws Exception 
     */
    public static ArrayList<PartieDB> afficheInfos(int idJoueur) throws Exception{
        ArrayList<PartieDB> parties = new ArrayList<PartieDB>();
        String rep = "{?=call readPartie2(?)}";
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idJoueur);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            boolean ok = false;
            while(rs.next()){
                ok=true;
                int idP = rs.getInt("idPartie");
                int idM = rs.getInt("idMap");
                Date d = rs.getDate("datePartie");
                int scoreP = rs.getInt("scorePartie");
                int idPU = rs.getInt("idPersoUtilise");
                int idJ = idJoueur;
                int idPA = rs.getInt("idPersoAdverse");
                float dureeP = rs.getFloat("dureePartie");
                parties.add(new PartieDB(idP,idM,d,scoreP,idPU,idJ,idPA,dureeP));
            }
            if(!ok)
                throw new Exception("ID de joueur invalide");
            else
                return parties;
            
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }    
    }
    /**
     * Affiche les scores maximum des parties par joueur
     * @param idJoueur
     * @return
     * @throws Exception 
     */
    public static PartieDB afficheScoreMax(int idJoueur) throws Exception{
        String rep = "{?=call readPartie3(?)}";
        PartieDB p = new PartieDB();
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idJoueur);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            boolean ok = false;
            while(rs.next()){
                ok=true;
                p.setIdPartie(rs.getInt("idPartie"));
                p.setIdMap(rs.getInt("idPartie"));
                p.setDatePartie(rs.getDate("datePartie"));
                p.setScorePartie(rs.getInt("scorePartie"));
                p.setIdPersoUtilise(rs.getInt("idPersoutilise"));
                p.setIdJoueur(idJoueur);
                p.setIdPersoAdverse(rs.getInt("idPersoAdverse"));
                p.setDebutPartie(rs.getFloat("dureePartie")); 
            }
            if(!ok)
                return new PartieDB(0,0,null,0,0,idJoueur,0,0);
            else
                return p;
            
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture score :"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }    
    }
    /**
     * Affiche un top 10 des meilleures scores, tout joueur confondus
     * @return
     * @throws Exception 
     */
    public static ArrayList<PartieDB> topTenScore() throws Exception{
        ArrayList<PartieDB> parties = new ArrayList<PartieDB>();
        String rep = "{?=call readPartie4}";
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            boolean ok = false;
            while (rs.next()) {
                ok = true;
                int idP = rs.getInt("idPartie");
                PartieDB p = new PartieDB(idP);
                p.read();
                parties.add(p);
            }
            if(!ok)
                throw new Exception("Erreur dans la lecture du top 10!");
            else
                return parties;
            
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }    
    }
    @Override
    public void update() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
