package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
public class Personnage {
    protected int idPerso;
    protected String nomPerso;
    protected String historiquePerso;
    protected int attackPoing;
    protected int attackPied;
    protected int defense;
    protected int vitalite;
    /**
     * Constructeur par défaut
     */
    public Personnage(){}
    /**
     * Constructeur paramétré
     * @param idPerso
     * @param nomPerso
     * @param historiquePerso
     * @param attackPoing
     * @param attackPied
     * @param defense
     * @param vitalite 
     */
    public Personnage(int idPerso, String nomPerso, String historiquePerso,
            int attackPoing, int attackPied, int defense, int vitalite){
        this.idPerso = idPerso;
        this.nomPerso = nomPerso;
        this.historiquePerso = historiquePerso;
        this.attackPoing = attackPoing;
        this.attackPied = attackPied;
        this.defense = defense;
        this.vitalite = vitalite;
    }

    //Getters
    public int getIdPerso() {
        return idPerso;
    }

    public String getNomPerso() {
        return nomPerso;
    }

    public String getHistoriquePerso() {
        return historiquePerso;
    }

    public int getAttackPoing() {
        return attackPoing;
    }

    public int getAttackPied() {
        return attackPied;
    }

    public int getDefense() {
        return defense;
    }

    public int getVitalite() {
        return vitalite;
    }

    //Setters
    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }

    public void setNomPerso(String nomPerso) {
        this.nomPerso = nomPerso;
    }

    public void setHistoriquePerso(String historiquePerso) {
        this.historiquePerso = historiquePerso;
    }

    public void setAttackPoing(int attackPoing) {
        this.attackPoing = attackPoing;
    }

    public void setAttackPied(int attackPied) {
        this.attackPied = attackPied;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setVitalite(int vitalite) {
        this.vitalite = vitalite;
    }
    /**
     * Redéfinition du toString
     * @return 
     */
    @Override
    public String toString() {
        return "Personnage{" + "idPerso=" + idPerso + ", nomPerso=" + nomPerso + ", historiquePerso=" + historiquePerso + ", attackPoing=" + attackPoing + ", attackPied=" + attackPied + ", defense=" + defense + ", vitalite=" + vitalite + '}';
    }
    
    
}
