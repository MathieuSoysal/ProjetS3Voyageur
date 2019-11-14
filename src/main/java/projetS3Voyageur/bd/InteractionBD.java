package projetS3Voyageur.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionBD {

    private static Connection con = null;
    private static ResultSet resultats = null;

    /*
     * pilote jdbc
     * https://fr.osdn.net/projects/sfnet_id2d/downloads/jdbc%20drivers/mysql-connector-java-5.1.15-bin.jar/
     */

    public static void main(String[] args) {

        String idCarte = "89";
        String nbVille = "7";

        InteractionBD.connection();
      /*  InteractionBD.setRequete("INSERT INTO Carte VALUES ('" + idCarte + "','" + nbVille + "')");*/
        List<String> recup = InteractionBD.recuperationBD("SELECT * FROM Carte");
       /* System.out.println(recup);*/

        /*int i = 0;

        for (String s: recup) {

                recup.get();
        }*/
        System.out.println(InteractionBD.getNbVille(recup, 2));


    }

    public static String getNbVille(List<String> liste, int idC){


        return liste.get(idC);
    }

    public static Connection connection() {

        // chargement du pilote JDBC MySql (ajouter dans les dépendance avec le lien en haut MATHIEU).

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.err.println("Le pilote JDBC n'est pas installé \n");
            e.printStackTrace();

        }

        // connection a la base de données

        System.out.println("Connexion à la base de données");

        try {

            InteractionBD.con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn",
                    "zaidn", "XavierCorbier");

        } catch (SQLException e) {

            System.err.println("Connection à la base de données impossible");
            e.printStackTrace();

        }

        return InteractionBD.con;

    }

    public static String setRequete(String r) {

        // insertion d'un enregistrement dans la table client
        System.out.println("Création de la requête");

        try {
            Statement stmt = con.createStatement();
            int nbMaj = stmt.executeUpdate(r);
            System.out.println("Nombre de mise à jour = " + nbMaj + "\n");

        } catch (SQLException e) {
            System.err.println("Erreur(s) lors de l'interaction avec la base de données");
            e.printStackTrace();
        }
        return r;
    }

    public static List<String> recuperationBD(String re) {


        List<String> attributs = new ArrayList<>();

        // creation et execution de la requete

        System.out.println("Création et éxecution de la requête SELECT *");

        try {
            Statement stmt = con.createStatement();
            resultats = stmt.executeQuery(re);

        } catch (SQLException e) {

            System.err.println("Problème durant l'éxecution");
        }

        // parcours des données retournées

        System.out.println("Données récuperé :");

        try {

            ResultSetMetaData rsmd = resultats.getMetaData();
            int nbCol = rsmd.getColumnCount();
            boolean suite = resultats.next();

            while (suite) {

                for (int i = 1; i <= nbCol; i++){

                    attributs.add(resultats.getString(i));
                }

                suite = resultats.next();
            }

            resultats.close();
        } catch (SQLException e) {
            System.err.println("Erreur lors des récupération de données de la base de données");
            System.err.println(e.getMessage());
        }
        return attributs;

    }

}
