package projetS3Voyageur.Interface_Graphique;

/*package projetS3Voyageur.Interface_Graphique;*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    private Connection con ;

    public Connect(){
        this.con = null;
    }

    public Connection connection(){



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

            this.con = DriverManager.getConnection("jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn", "zaidn", "XavierCorbier");

        } catch (SQLException e) {

            System.err.println("Connection à la base de données impossible");

        }

        return this.con;

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

}
