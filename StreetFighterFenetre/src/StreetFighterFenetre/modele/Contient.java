package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
public class Contient {
    protected int idPartie;
    protected int idPerso;
    
    /**
     * Constructeur par défaut
     */
    public Contient(){};
    /**
     * Constructeur paramétré
     * @param idPartie
     * @param idPerso 
     */
    public Contient(int idPartie,int idPerso){
        this.idPartie = idPartie;
        this.idPerso = idPerso;
    }
    /**
     * Getter idPartie
     * @return 
     */
    public int getIdPartie() {
        return idPartie;
    }
    /**
     * Getter idPerso
     * @return 
     */
    public int getIdPerso() {
        return idPerso;
    }
    /**
     * Setter idPartie
     * @param idPartie 
     */
    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }
    /**
     * Setter idPerso
     * @param idPerso 
     */
    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }
    /**
     * toString redéfini
     * @return 
     */
    @Override
    public String toString() {
        return "Contient : " + "\nidPartie: " + idPartie + "\nidPerso:" + idPerso;
    }   
}
