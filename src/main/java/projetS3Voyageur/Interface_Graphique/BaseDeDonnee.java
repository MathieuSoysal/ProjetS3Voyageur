package projetS3Voyageur.Interface_Graphique;

import java.sql.*;

public class BaseDeDonnee {
    private boolean boo = false;

    /* pilote jdbc https://fr.osdn.net/projects/sfnet_id2d/downloads/jdbc%20drivers/mysql-connector-java-5.1.15-bin.jar/ */

    public static Connection connection(){

        Connection con = null;


        // chargement du pilote

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.err.println("Impossible de charger le pilote jdbc:odbc\n");
        }

        //connection a la base de données

        System.out.println("connexion à la base de données");


        try {

            /*String DBurl = "jdbc:mysql:zaidn";
            con = DriverManager.getConnection(DBurl);*/

            con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn", "zaidn", "XavierCorbier");

        } catch (SQLException e) {

            System.err.println("Connection à la base de données impossible");

        }

        return con;

    }
    public static boolean testConnection(Connection c){
        boolean boo = false;
        if(c != null){
            boo = false;
        }
        else{
            boo = true;
        }
        return boo;
    }


    public static void main(java.lang.String[] args) {

        Connection con = null;
        ResultSet résultats = null;
        String requete = "";
        if(connection() != null){

        }


        con = connection();



        //insertion d'un enregistrement dans la table client
        System.out.println("creation enregistrement");

        requete = "INSERT INTO TEST VALUES (10,'Soysal','Mathieu')";

        try {

            Statement stmt = con.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println("nombre de mise à jour = "+ nbMaj);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        //creation et execution de la requete
        System.out.println("creation et execution de la requête");
        requete = "SELECT * FROM TEST";

        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);

        } catch (SQLException e) {

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

