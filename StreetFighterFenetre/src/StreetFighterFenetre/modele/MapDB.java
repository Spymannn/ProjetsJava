package StreetFighterFenetre.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Spymannn
 */
public class MapDB extends Map implements CRUD{
    protected static Connection dbConnect = null;
    /**
     * Constructeur par défaut
     */
    public MapDB(){}
    /**
     * Constructeur paramétré
     * @param idMap
     * @param nomMap
     * @param historiqueMap 
     */
    public MapDB(int idMap, String nomMap, String historiqueMap){
        super(idMap,nomMap,historiqueMap);
    }
    /**
     * Constructeur avec seulement l'identifiant
     * @param idMap 
     */
    public MapDB(int idMap){
        super(idMap,"","");
    }
    
    public static void setConnection(Connection nouvdbConnect){
        dbConnect = nouvdbConnect;
    }

    @Override
    public void create() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Lecture par identifiant de map
     * @throws Exception 
     */
    @Override
    public void read() throws Exception {
        String rep="{?=call readMap(?)}";
        
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idMap);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.nomMap = rs.getString("nomMap");
                this.historiqueMap = rs.getString("historiqueMap");
            }
            else
                throw new Exception("Code inconnu");
        } catch(Exception e){
            throw new Exception("Erreur lecture "+e.getMessage());
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
