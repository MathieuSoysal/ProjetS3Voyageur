package projetS3Voyageur.Interface_Graphique;

import java.sql.*;

public class Base {

/* pilote jdbc https://fr.osdn.net/projects/sfnet_id2d/downloads/jdbc%20drivers/mysql-connector-java-5.1.15-bin.jar/ */

   /* public boolean connection(){

Connection con = null;

    }*/


    public static void main(java.lang.String[] args) {

       Connection con = null;
        ResultSet resultats = null;
        String requete = "";

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

           System.err.println("Connection à la base de données impossible");;
        }

        //insertion d'un enregistrement dans la table client
        System.out.println("creation d'une requête");

        requete = "INSERT INTO TEST VALUES (34,'François','Paure')";

        try {

            Statement stmt = con.createStatement();
            int nbMaj = stmt.executeUpdate(requete);
            System.out.println("Nombre de mise à jour (UPDATE, DELETE ou INSERT) = "+ nbMaj);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        //creation et execution de la requete
        System.out.println("Création et éxecution de la requête" + "\n");
        requete = "SELECT * FROM TEST";

        try {
            Statement stmt = con.createStatement();
            resultats = stmt.executeQuery(requete);

        } catch (SQLException e) {

            System.err.println("Problème(s) durant l'éxecution");
        }


        //parcours des données retournées

        System.out.println("Données contenu dans la Table : " + "\n");

        try {

            ResultSetMetaData rsmd = resultats.getMetaData();
            int nbCol = rsmd.getColumnCount();
            boolean suite = resultats.next();

            while (suite) {

                for (int i = 1; i <= nbCol; i++)
                    System.out.print(" | " + resultats.getString(i) );
                System.out.println();
                suite = resultats.next();
            }

            resultats.close();
        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        System.out.println("\n" + "Fin");
        System.exit(0);
    }
}
