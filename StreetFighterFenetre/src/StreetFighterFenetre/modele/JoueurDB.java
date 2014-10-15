package StreetFighterFenetre.modele;

/**
 *
 * @author Spymannn
 */
import java.util.*;
import java.sql.*;
import java.sql.Date;

public class JoueurDB extends Joueur implements CRUD {

    protected static Connection dbConnect = null;

    /**
     * Constructeur par défaut
     */
    public JoueurDB() {
    }

    /**
     * Constructeur paramétré création
     *
     * @param pseudoJoueur
     * @param nomJoueur
     * @param prenomJoueur
     * @param dateNaissance
     * @param emailJoueur
     * @param motDePasse
     * @param imageJoueur
     */
    public JoueurDB(String pseudoJoueur,
            String nomJoueur, String prenomJoueur,
            Date dateNaissance, String emailJoueur,
            String motDePasse, String imageJoueur) {
        super(0, pseudoJoueur, nomJoueur, prenomJoueur, dateNaissance,
                emailJoueur, motDePasse, imageJoueur);
    }

    /**
     * Constructeur paramétré complet
     *
     * @param idJoueur
     * @param pseudoJoueur
     * @param nomJoueur
     * @param prenomJoueur
     * @param dateNaissance
     * @param emailJoueur
     * @param motDePasse
     * @param imageJoueur
     */
    public JoueurDB(int idJoueur, String pseudoJoueur,
            String nomJoueur, String prenomJoueur,
            Date dateNaissance, String emailJoueur,
            String motDePasse, String imageJoueur) {
        super(idJoueur, pseudoJoueur, nomJoueur, prenomJoueur, dateNaissance,
                emailJoueur, motDePasse, imageJoueur);
    }

    /**
     * Constructeur paramétré recherche
     *
     * @param idJoueur
     */
    public JoueurDB(int idJoueur) {
        super(idJoueur, "", "", "", null, "", "", "");
    }

    /**
     * Méthode static permettant de partager la connexion
     *
     * @param nouvdbConnect
     */
    public static void setConnection(Connection nouvdbConnect) {
        dbConnect = nouvdbConnect;
    }
    /**
     * création d'un joueur
     * @throws Exception 
     */
    public void create() throws Exception {
        CallableStatement cstmt = null;
        try {
            String call = "call createJoueur(?,?,?,?,?,?,?,?)";
            cstmt = dbConnect.prepareCall(call);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, pseudoJoueur);
            cstmt.setString(3, nomJoueur);
            cstmt.setString(4, prenomJoueur);
            cstmt.setDate(5, dateNaissance);
            cstmt.setString(6, emailJoueur);
            cstmt.setString(7, motDePasse);
            cstmt.setString(8, imageJoueur);
            cstmt.executeUpdate();
            this.idJoueur = cstmt.getInt(1);
        } catch (Exception e) {
            throw new Exception("Erreur de création d'un joueur");
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }
    /**
     * lecture d'un joueur par son identifiant
     * @throws Exception 
     */
    @Override
    public void read() throws Exception {
        String rep = "{?=call readJoueur(?)}";

        CallableStatement cstmt = null;
        try {
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setInt(2, idJoueur);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            if (rs.next()) {
                this.pseudoJoueur = rs.getString("pseudoJoueur");
                this.nomJoueur = rs.getString("nomJoueur");
                this.prenomJoueur = rs.getString("prenomJoueur");
                this.dateNaissance = rs.getDate("dateNaissance");
                this.emailJoueur = rs.getString("emailJoueur");
                this.motDePasse = rs.getString("motDePasse");
                this.imageJoueur = rs.getString("imageJoueur");
            } else {
                throw new Exception("Code inconnu");
            }
        } catch (Exception e) {
            throw new Exception("Erreur lecture" + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }
    /**
     * Recherche d'un joueur par son pseudo
     * @param pseudo
     * @return
     * @throws Exception 
     */
    public static JoueurDB recherchePseudoJoueur(String pseudo) throws Exception {
        JoueurDB gamer = new JoueurDB();
        String rep = "{?=call readJoueurPseudo(?)}";

        CallableStatement cstmt = null;
        try {
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
            cstmt.setString(2, pseudo);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            boolean found = false;
            while (rs.next()) {
                found = true;
                int idJ = rs.getInt("idJoueur");
                String pseudoJ = rs.getString("pseudoJoueur");
                String nomJ = rs.getString("nomJoueur");
                String prenomJ = rs.getString("prenomJoueur");
                Date dateN = rs.getDate("dateNaissance");
                String emailJ = rs.getString("emailJoueur");
                String mdpJ = rs.getString("motDePasse");
                String imageJ = rs.getString("imageJoueur");
                gamer = new JoueurDB(idJ,pseudoJ, nomJ, prenomJ, dateN, emailJ, mdpJ, imageJ);
            }
            if (!found) {
                throw new Exception("Pseudo inconnu!");
            } else {
                return gamer;
            }
        } catch (Exception e) {
            throw new Exception("Erreur lecture " + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }
    /**
     * Affiche la liste de tous les joueurs 
     * @return
     * @throws Exception 
     */
    public static ArrayList<JoueurDB> afficheToutJoueur() throws Exception {
        ArrayList<JoueurDB> gamers = new ArrayList<JoueurDB>();
        String rep = "{?=call readJoueur3}";
        CallableStatement cstmt = null;
        try{
            cstmt = dbConnect.prepareCall(rep);
            cstmt.registerOutParameter(1,oracle.jdbc.OracleTypes.CURSOR);
            cstmt.executeQuery();
            ResultSet rs = (ResultSet) cstmt.getObject(1);
            boolean ok = false;
            while(rs.next()){
                ok=true;
                int idJ = rs.getInt("idJoueur");
                String pseudoJ = rs.getString("pseudoJoueur");
                String nomJ = rs.getString("nomJoueur");
                String prenomJ = rs.getString("prenomJoueur");
                Date dateN = rs.getDate("dateNaissance");
                String emailJ = rs.getString("emailJoueur");
                String mdpJ = rs.getString("motDePasse");
                String imageJ = rs.getString("imageJoueur");
                gamers.add(new JoueurDB(idJ,pseudoJ,nomJ,prenomJ,dateN,emailJ,mdpJ,imageJ));
            }
            if(!ok)
                throw new Exception("ID de joueur invalide");
            else
                return gamers;
            
        }
        catch(Exception e){
            throw new Exception("Erreur de lecture "+e.getMessage());
        }
        finally{
            try{
                cstmt.close();
            }
            catch(Exception e){}
        }    

    }
    /**
     * Permet de mettre à jour le mot de passe
     * @throws Exception 
     */
    @Override
    public void update() throws Exception {
        CallableStatement cstmt = null;
        try {
            String rep = "call updateJoueurMdp(?,?)";
            cstmt = dbConnect.prepareCall(rep);
            PreparedStatement p = dbConnect.prepareStatement(rep);
            cstmt.setString(1, motDePasse);
            cstmt.setInt(2, idJoueur);
            cstmt.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur dans la mise à jour " + e.getMessage());
        } finally {
            try {
                cstmt.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
