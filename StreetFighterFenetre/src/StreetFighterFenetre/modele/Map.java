package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
public class Map {
    protected int idMap;
    protected String nomMap;
    protected String historiqueMap;
    /**
     * Constructeur par défaut
     */
    public Map(){}
    /**
     * Constructeur paramétré
     * @param idMap
     * @param nomMap
     * @param historiqueMap 
     */
    public Map(int idMap, String nomMap, String historiqueMap){
        this.idMap = idMap;
        this.nomMap = nomMap;
        this.historiqueMap = historiqueMap;
    }

    //Getters
    public int getIdMap() {
        return idMap;
    }

    public String getNomMap() {
        return nomMap;
    }

    public String getHistoriqueMap() {
        return historiqueMap;
    }
    
    //Setters
    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public void setNomMap(String nomMap) {
        this.nomMap = nomMap;
    }

    public void setHistoriqueMap(String historiqueMap) {
        this.historiqueMap = historiqueMap;
    }
    /**
     * Redéfinition du toString
     * @return 
     */        
    @Override
    public String toString() {
        return "Map{" + "idMap=" + idMap + ", nomMap=" + nomMap + ", historiqueMap=" + historiqueMap + '}';
    }   
}
