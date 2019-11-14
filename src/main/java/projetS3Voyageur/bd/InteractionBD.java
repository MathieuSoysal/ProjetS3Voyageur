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

        String idCarte = "89";
        String nbVille = "7";

        InteractionBD.connexion();
      /*  InteractionBD.setRequete("INSERT INTO Carte VALUES ('" + idCarte + "','" + nbVille + "')");*/
        List<String> recup = InteractionBD.recuperationBD("SELECT * FROM Carte");
       /* System.out.println(recup);*/

        /*int i = 0;

        for (String s: recup) {

                recup.get();
        }*/
        System.out.println(InteractionBD.getNbVille(recup, 2));

    }

    public static String getNbVille(List<String> liste, int idC) {

        return liste.get(idC);
    }

    public static Connection connexion() {

        // chargement du pilote JDBC MySql (ajouter dans les dépendance avec le lien en haut MATHIEU).

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.err.println("Le pilote JDBC n'est pas installé \n");
            e.printStackTrace();

        }

        // connexion a la base de données

        System.out.println("Connexion à la base de données");

        try {

            InteractionBD.con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn",
                    "zaidn", "XavierCorbier");

        } catch (SQLException e) {

            System.err.println("Connexion à la base de données impossible");
            e.printStackTrace();

        }

        return InteractionBD.con;

    }

    /**
     * Exécute la requête SQL donnée en paramètre dans la base de données
     * préalablement choisie .
     * 
     * 
     * @près-requis Exécuter la méthode connexion.
     * @param requete {@code String} Requête SQL
     * @return {@code Boolean} vrai si la requête à aboutie sinon faux.
     */
    public static Boolean setRequete(String requete) {

        // insertion d'un enregistrement dans la table client

        try {
            Statement stmt = con.createStatement();
            stmt.execute(requete);
            return true;
        } catch (SQLTimeoutException e) {
            System.err.println(
                    "Connexion à la base de donnée réussie, mais le délai de réponse impartie a été dépassé. ");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.err.println("Impossible de se connecter à la base de données");
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> recuperationBD(String requete) {

        List<String> attributs = new ArrayList<>();

        // creation et execution de la requete

        System.out.println("Création et éxecution de la requête SELECT *");

        try {
            Statement stmt = con.createStatement();
            ResultSet resultats = stmt.executeQuery(requete);

            // parcours des données retournées

            System.out.println("Données récuperé :");

            ResultSetMetaData rsmd = resultats.getMetaData();
            int nbCol = rsmd.getColumnCount();
            boolean suite = resultats.next();

            while (suite) {

                for (int i = 1; i <= nbCol; i++) {

                    attributs.add(resultats.getString(i));
                }

                suite = resultats.next();
            }

            resultats.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors des récupération de données de la base de données");

        }
        return attributs;

    }

}
