package StreetFighterFenetre.modele;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Spymannn
 */
public class ImagePersoDB extends ImagePerso implements CRUD{
    protected static Connection dbConnect = null;
    /**
     * Constructeur par défaut
     */
    public ImagePersoDB(){}
    /**
     * Constructeur paramétré complet
     * @param idFrame
     * @param idPerso
     * @param nomFrame
     * @param typeImage
     * @param coteImage 
     */
    public ImagePersoDB(int idFrame,int idPerso, String nomFrame,
            String typeImage, String coteImage){
        super(idFrame,idPerso,nomFrame,typeImage,coteImage);
        
    }
    /**
     * Constructeur paramétré juste avec idFrame
     * @param idFrame 
     */
    public ImagePersoDB(int idFrame){
        super(idFrame,0,"","","");
    }
    
    public static void setConnection(Connection nouvdbConnect){
        dbConnect=nouvdbConnect;
    }
    
    @Override
    public void create() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * lecture des images par identifiants
     * @throws Exception 
     */
    @Override
    public void read() throws Exception {
        String rep="{?=call readImage1(?)}";
        CallableStatement cstmt=null;
        
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idFrame);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            if(rs.next()){
                this.idPerso = rs.getInt("idPerso");
                this.nomFrame = rs.getString("nomFrame");
                this.typeImage = rs.getString("typeImage");
                this.coteImage = rs.getString("coteImage");
            }
            else
                throw new Exception("Numéro d'image inconnu");
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
     * Lecture des images par idPerso
     * @param idPerso
     * @return
     * @throws Exception 
     */
    public static ArrayList<ImagePersoDB> affichesImage(int idPerso) throws Exception{
        ArrayList<ImagePersoDB> images = new ArrayList<ImagePersoDB>();
        String rep="{? = call readImage2(?)}";
        CallableStatement cstmt = null;
        try{
            cstmt=dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2,idPerso);
            cstmt.executeQuery();
            ResultSet rs=(ResultSet)cstmt.getObject(1);
            boolean ok=false;
            while(rs.next()){
                ok=true;
                int idF = rs.getInt("idFrame");
                String nomF = rs.getString("nomFrame");
                String typeI = rs.getString("typeImage");
                String coteI = rs.getString("coteImage");
                images.add(new ImagePersoDB(idF,idPerso,nomF,typeI,coteI));
            }
            if(!ok)
                throw new Exception("Numéro d'ID perso inéxistant");
            else
                return images;
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture : "+e.getMessage());
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
