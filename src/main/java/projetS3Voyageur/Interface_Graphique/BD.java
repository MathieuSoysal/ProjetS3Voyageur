package projetS3Voyageur.Interface_Graphique;


import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BD {

    public static void main(String[] args) throws SQLException {

        try{
            Class.forName("com.mysql.jdbc.Driver"); // pilote JDBC mySQL

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }


        String BDurl = "jdbc:mysql:zaidn";

        /* con = DriverManager.getConnection(BDurl);*/

        Connection con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr", "zaidn", "XavierCorbier");

        /*String url = "jdbc:odbc:factures";*/



        ResultSet résultats = null;
        String requete = "INSERT INTO TEST VALUES (3,'client 3','prenom 3')";

        try {

            Statement stmt = con.createStatement(); // envoie les requêtes
            résultats = stmt.executeQuery(requete);
            int nbUpdate = stmt.executeUpdate(requete);// nombre de lignes mis à jour
            System.out.println(nbUpdate + " lignes viennent d'être ajoutée à la table");// affichage du nombre de lignes mis à jour

        } catch (SQLException e) {

            e.printStackTrace();
        }







    }



}
