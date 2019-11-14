package projetS3Voyageur.Interface_Graphique;

import java.sql.*;

public class BaseDeDonnee {

    private static Connection con = null;
    private static ResultSet résultats = null;
    private static String requete = "";

    /* pilote jdbc https://fr.osdn.net/projects/sfnet_id2d/downloads/jdbc%20drivers/mysql-connector-java-5.1.15-bin.jar/ */



    public static Connection connection() {




        // chargement du pilote

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.err.println("le pilote JDBC n'est pas installé \n");
        }

        //connection a la base de données

        System.out.println("connexion à la base de données");


        try {

            /*String DBurl = "jdbc:mysql:zaidn";
            con = DriverManager.getConnection(DBurl);*/

            BaseDeDonnee.con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn", "zaidn", "XavierCorbier");

        } catch (SQLException e) {

            System.err.println("Connection à la base de données impossible");

        }

        return BaseDeDonnee.con;

    }/*dd*/

    public static String setRequete(String r) {

        //insertion d'un enregistrement dans la table client
        System.out.println("creation enregistrement");


        try {

            Statement stmt = con.createStatement();
            int nbMaj = stmt.executeUpdate(r);
            System.out.println("Nombre de mise à jour = " + nbMaj + "\n");

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return r;
    }

    public static void affichageBD(String re){

        //creation et execution de la requete

        System.out.println("Creation et execution de la requête");


        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(re);

        } catch(SQLException e) {

            System.err.println("Problème durant l'éxecution");
        }

        //parcours des données retournées

        System.out.println("parcours des données retournées");

        try {

            ResultSetMetaData rsmd = résultats.getMetaData();
            int nbCol = rsmd.getColumnCount();
            boolean suite = résultats.next();

            while (suite) {

                for (int i = 1; i <= nbCol; i++)
                    System.out.print(résultats.getString(i) + " ");
                System.out.println();
                suite = résultats.next();
            }

            résultats.close();
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }
        System.out.println("Fin");
        System.exit(0);

    }


}

