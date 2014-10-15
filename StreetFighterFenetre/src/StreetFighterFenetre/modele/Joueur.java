package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
import java.sql.Date;

public class Joueur {
    protected int idJoueur;
    protected String pseudoJoueur;
    protected String nomJoueur;
    protected String prenomJoueur;
    protected Date dateNaissance;
    protected String emailJoueur;
    protected String motDePasse;
    protected String imageJoueur;
    /**
     * Constructeur par défaut
     */
    public Joueur(){}
    /**
     * Constructeur paramétré
     * @param idJoueur
     * @param pseudoJoueur
     * @param nomJoueur
     * @param prenomJoueur
     * @param dateNaissance
     * @param emailJoueur
     * @param motDePasse 
     * @param imageJoueur
     */
    public Joueur(int idJoueur, String pseudoJoueur,
            String nomJoueur, String prenomJoueur,
            Date dateNaissance, String emailJoueur,
            String motDePasse,String imageJoueur){
        this.idJoueur = idJoueur;
        this.pseudoJoueur = pseudoJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.dateNaissance = dateNaissance;
        this.emailJoueur = emailJoueur;
        this.motDePasse = motDePasse;
        this.imageJoueur = imageJoueur;
    }

    //Getters
    public int getIdJoueur() {
        return idJoueur;
    }

    public String getPseudoJoueur() {
        return pseudoJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getEmailJoueur() {
        return emailJoueur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getImageJoueur() {
        return imageJoueur;
    }
    

    //Setters
    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setPseudoJoueur(String pseudoJoueur) {
        this.pseudoJoueur = pseudoJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setEmailJoueur(String emailJoueur) {
        this.emailJoueur = emailJoueur;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setImageJoueur(String imageJoueur) {
        this.imageJoueur = imageJoueur;
    }
    
    /**
     * redéfinition du toString
     * @return 
     */
    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", pseudoJoueur=" + 
                pseudoJoueur + ", nomJoueur=" + nomJoueur + ", prenomJoueur=" 
                + prenomJoueur + ", dateNaissance=" + dateNaissance + 
                ", emailJoueur=" + emailJoueur + ", motDePasse=" + motDePasse + 
                ",imageJoueur=" + imageJoueur + "}";
    }
    
    
    
}
