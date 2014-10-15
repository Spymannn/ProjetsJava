package StreetFighterFenetre.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Spymannn
 */
public class PersonnageDB extends Personnage implements CRUD{
    protected static Connection dbConnect = null;
    /**
     * Constructeur par défaut
     */
    public PersonnageDB(){}   
    /**
     * constructeur paramétrés complet
     * @param idPerso
     * @param nomPerso
     * @param historiquePerso
     * @param attackPoing
     * @param attackPied
     * @param defense
     * @param vitalite 
     */
    public PersonnageDB(int idPerso, String nomPerso, String historiquePerso,
            int attackPoing, int attackPied, int defense, int vitalite){
        super(idPerso,nomPerso,historiquePerso,attackPoing,attackPied,defense,vitalite);
    }
    /**
     * Constructeur avec seulement le paramètre idPerso
     * @param idPerso 
     */
    public PersonnageDB(int idPerso){
        super(idPerso,"","",0,0,0,0);
    }
    /**
     * Méthode static permettant de partager la connexion
     * @param nouvdbConnect 
     */
    public static void setConnection(Connection nouvdbConnect){
        dbConnect = nouvdbConnect;
    }

    @Override
    public void create() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * lecture d'un personnage par identifiant
     * @throws Exception 
     */
    @Override
    public void read() throws Exception {
        String rep="{?=call readPerso(?)}";
        
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idPerso);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.nomPerso = rs.getString("nomPerso");
                this.historiquePerso = rs.getString("historiquePerso");
                this.attackPoing = rs.getInt("attackPoing");
                this.attackPied = rs.getInt("attackPied");
                this.defense = rs.getInt("defense");
                this.vitalite = rs.getInt("vitalite");
            }
            else
                throw new Exception("Code inconnu");
        } catch(Exception e){
            throw new Exception("Erreur lecture"+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            } catch(Exception e){}  
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
