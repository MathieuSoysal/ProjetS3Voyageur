package projetS3Voyageur.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionBD {

    private static Connection con = null;

    /*
     * pilote jdbc
     * https://fr.osdn.net/projects/sfnet_id2d/downloads/jdbc%20drivers/mysql-connector-java-5.1.15-bin.jar/
     */

    public static void main(String[] args) {

        String idCarte = "61";
        String nbVille = "50";

        InteractionBD.connexion();

        InteractionBD.setRequete("INSERT INTO Carte VALUES ('" + idCarte + "','" + nbVille + "')");

        List<String[]> recup = InteractionBD.recuperationBD("SELECT * FROM Carte");

        for (String[] s : recup) {
            System.out.println("|");
            for (String str : s) {
                System.out.print(str + "|");
            }

        }

    }

    /**
     * Cette méthode permet de connecter la Class à la base de données, elle doit
     * être effectuée avant toute opération avec celle-ci.
     * 
     * @return {@code Boolean} Retourne vrai si la connexion à réussi sinon faux.
     */
    public static Boolean connexion() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn", "zaidn",
                    "XavierCorbier");

            return true;

        } catch (SQLException e) {

            problemeConnexion(e);
            return false;

        } catch (ClassNotFoundException e) {

            System.err.println("Le pilote JDBC n'est pas installé \n");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Exécute la requête SQL donnée en paramètre dans la base de données
     * préalablement choisie .
     * 
     * 
     * @près-requis Exécuter en amont la méthode connexion().
     * @param requete {@code String} Requête SQL
     * @return {@code Boolean} vrai si la requête à aboutie sinon faux.
     */
    public static Boolean setRequete(String requete) {

        // TODO: à voir si on implémente la connection directement ici

        try {
            con.createStatement().execute(requete);
            return true;

        } catch (SQLTimeoutException e) {
            delaiDepasse(e);
            return false;
        } catch (SQLException e) {
            problemeConnexion(e);
            return false;
        }
    }

    /**
     * Renvoie un {@code List<String[]>} possédant le(s) résultat(s) de la requête
     * SQl donnée en paramètre.
     * 
     * @près-requis Exécuter en amont la méthode connexion()
     * 
     * @param requete {@code String} Requête SQL dont le(s) résultat(s) doivent être
     *                récupérés
     * 
     * @return {@code List<String[]>} Résultat de la requête SQL
     */
    public static List<String[]> recuperationBD(String requete) {

        try {
            List<String[]> resultatMethode = new ArrayList<>();
            ResultSet resultatsRequete = con.createStatement().executeQuery(requete);

            final int nbCol = resultatsRequete.getMetaData().getColumnCount();

            String[] tuple = new String[nbCol];
            while (resultatsRequete.next()) {

                for (int i = 1; i <= nbCol; i++) {
                    tuple[i - 1] = resultatsRequete.getString(i);
                }
                resultatMethode.add(tuple.clone());
            }

            resultatsRequete.close();

            return resultatMethode;

        } catch (SQLTimeoutException e) {
            delaiDepasse(e);
            return null;
        } catch (SQLException e) {
            problemeConnexion(e);
            return null;
        }

    }

    // #region Gestion des exceptions

    private static void delaiDepasse(SQLTimeoutException e) {
        System.err
                .println("Connexion à la base de donnée réussie, mais le délai de réponse impartie a été dépassé. \n");
        e.printStackTrace();
    }

    private static void problemeConnexion(SQLException e) {
        System.err.println("Impossible de se connecter à la base de données \n");
        e.printStackTrace();
    }

    // #endregion Gestion des excepitons

}