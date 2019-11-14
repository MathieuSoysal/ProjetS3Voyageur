package projetS3Voyageur.Interface_Graphique;

public class Application {


    public static void main(String[] args) {

        String idCarte = "3";
        String nbVille= "1";

        BaseDeDonnee.connection();
        BaseDeDonnee.setRequete("INSERT INTO Carte VALUES ('"+idCarte+"','"+nbVille+"')");
        BaseDeDonnee.affichageBD("SELECT * FROM Carte");




    }

}
