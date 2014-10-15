package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
public class ImagePerso {
    protected int idFrame;
    protected int idPerso;
    protected String nomFrame;
    protected String typeImage;
    protected String coteImage;
    /**
     * Constructeur par défaut
     */
    public ImagePerso(){}
    /**
     * Constructeur paramétré
     * @param idFrame
     * @param idPerso
     * @param nomFrame
     * @param typeImage
     * @param coteImage 
     */
    public ImagePerso(int idFrame,int idPerso, String nomFrame,
            String typeImage, String coteImage){
        this.idFrame = idFrame;
        this.idPerso = idPerso;
        this.nomFrame = nomFrame;
        this.typeImage = typeImage;
        this.coteImage = coteImage;
    }
    //Getters
    public int getIdFrame() {
        return idFrame;
    }

    public int getIdPerso() {
        return idPerso;
    }

    public String getNomFrame() {
        return nomFrame;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public String getCoteImage() {
        return coteImage;
    }
    //Setters
    public void setIdFrame(int idFrame) {
        this.idFrame = idFrame;
    }

    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }

    public void setNomFrame(String nomFrame) {
        this.nomFrame = nomFrame;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public void setCoteImage(String coteImage) {
        this.coteImage = coteImage;
    }
    /**
     * Redéfinition du toString
     * @return 
     */
    @Override
    public String toString() {
        return "ImagePerso{" + "idFrame=" + idFrame + ", idPerso=" + idPerso + ", nomFrame=" + nomFrame + ", typeImage=" + typeImage + ", coteImage=" + coteImage + '}';
    }
    
    
    
}
