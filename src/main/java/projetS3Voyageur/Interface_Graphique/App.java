package projetS3Voyageur.Interface_Graphique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    private static String JDBC_CONNECTION_URL = "jdbc:http://gloin:5560/isqlplus/";


    public static void main(String[] args) {

        try {

            CSVLoader loader = new CSVLoader(getCon());

           /* loader.loadCSV("C:\\home/ann2/zaidn/IdeaProjects/ProjetS3Voyageur/Statistiques/DESKTOP-TH823FM/2019-11-01/stats-2019-11-01.csv, "Algo", true);*/

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static Connection getCon() {

        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(JDBC_CONNECTION_URL);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}



