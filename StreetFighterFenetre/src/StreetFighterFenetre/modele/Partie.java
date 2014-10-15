package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
import java.sql.Date;
public class Partie {
    protected int idPartie;
    protected int idMap;
    protected Date datePartie;
    protected int scorePartie;
    protected int idPersoUtilise;
    protected int idJoueur;
    protected int idPersoAdverse;
    protected float debutPartie;
    /**
     * Constructeur par défaut
     */
    public Partie(){}
    /**
     * Constructeur paramétré
     * @param idPartie
     * @param idMap
     * @param datePartie
     * @param scorePartie
     * @param idPersoUtilise
     * @param idJoueur
     * @param idPersoAdverse
     * @param debutPartie 
     */
    public Partie(int idPartie, int idMap, Date datePartie, int scorePartie,
            int idPersoUtilise, int idJoueur, int idPersoAdverse,float debutPartie){
        this.idPartie = idPartie;
        this.idMap = idMap;
        this.datePartie = datePartie;
        this.scorePartie = scorePartie;
        this.idPersoUtilise = idPersoUtilise;
        this.idJoueur = idJoueur;
        this.idPersoAdverse = idPersoAdverse;
        this.debutPartie = debutPartie;
    }

    //Getters
    public int getIdPartie() {
        return idPartie;
    }

    public int getIdMap() {
        return idMap;
    }

    public Date getDatePartie() {
        return datePartie;
    }

    public int getScorePartie() {
        return scorePartie;
    }

    public int getIdPersoUtilise() {
        return idPersoUtilise;
    }

    public int getIdPersoAdverse() {
        return idPersoAdverse;
    }

    public float getDebutPartie() {
        return debutPartie;
    }
    
    public int getIdJoueur(){
        return idJoueur;
    }

    //Setters
    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }

    public void setIdMap(int idMap) {
        this.idMap = idMap;
    }

    public void setDatePartie(Date datePartie) {
        this.datePartie = datePartie;
    }

    public void setScorePartie(int scorePartie) {
        this.scorePartie = scorePartie;
    }

    public void setIdPersoUtilise(int idPersoUtilise) {
        this.idPersoUtilise = idPersoUtilise;
    }

    public void setIdPersoAdverse(int idPersoAdverse) {
        this.idPersoAdverse = idPersoAdverse;
    }

    public void setDebutPartie(float debutPartie) {
        this.debutPartie = debutPartie;
    }
    
    public void setIdJoueur(int idJoueur){
        this.idJoueur = idJoueur;
    }
    /**
     * Redéfinition du toString
     * @return 
     */
    @Override
    public String toString() {
        return "Partie{" + "idPartie=" + idPartie + ", idMap=" + idMap + ", datePartie=" + datePartie + ", scorePartie=" + scorePartie + ", idPersoUtilise=" + idPersoUtilise + ",idJoueur"+ idJoueur+", idPersoAdverse=" + idPersoAdverse + ", debutPartie=" + debutPartie + '}';
    }  
}
