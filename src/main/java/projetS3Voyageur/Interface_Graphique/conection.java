package projetS3Voyageur.Interface_Graphique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class conection {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver O.K.");

            String url = "jdbc:mysql://webinfo.iutmontp.univ-montp2.fr:3306/zaidn";
            String user = "zaidn";
            String passwd = "XavierCorbier";

            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connexion effective !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
