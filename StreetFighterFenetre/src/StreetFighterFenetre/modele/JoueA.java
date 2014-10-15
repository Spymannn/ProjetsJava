package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
public class JoueA {
    protected int idJoueur;
    protected int idPartie;
    /**
     * Constructeur par défaut
     */
    public JoueA(){}
    /**
     * Constructeur paramétré
     * @param idJoueur
     * @param idPartie 
     */
    public JoueA(int idJoueur, int idPartie){
        this.idJoueur = idJoueur;
        this.idPartie = idPartie;
    }
    //Getters
    public int getIdJoueur() {
        return idJoueur;
    }

    public int getIdPartie() {
        return idPartie;
    }
    //Setters
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setIdPartie(int idPartie) {
        this.idPartie = idPartie;
    }
    /**
     * redéfinition du toString
     * @return 
     */
    @Override
    public String toString() {
        return "JoueA{" + "idJoueur=" + idJoueur + ", idPartie=" + idPartie + '}';
    }
    
    
    
}
