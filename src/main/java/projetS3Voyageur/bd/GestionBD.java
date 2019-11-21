package projetS3Voyageur.bd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionBD {

    /**
     * Récupère le nombre ville référençais par l'idCarte donnée en paramètre.
     * 
     * @param idCarte {@code String} dont le nombre de ville doit être récupéré.
     * @return {@code Byte} Nbville que l'idCarte référence.
     */
    public static byte getNbVille(String idCarte) {
        InteractionBD.connexion();

        List<String[]> recuperationBD = InteractionBD.recuperationBD("SELECT nbVille FROM Carte WHERE idCarte = " + idCarte);


        if (recuperationBD.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        return Byte.valueOf((recuperationBD.get(0))[0]);

    }

    /**
     * Récupère les villes contenu par l'idCarte donnée en paramètre.
     *
     * @param idCarte {@code String} dont le nombre de ville doit être récupéré.
     * @return {@code Byte} Nbville que l'idCarte référence.
     */

    public static byte getXY(String idCarte) {
        InteractionBD.connexion();

        List<String[]> recuperationXY = InteractionBD.recuperationBD("SELECT V.idVille, X, Y FROM Carte C JOIN Ville V ON C.idCarte = V.idCarte WHERE V.idCarte = '" + idCarte +"' ");

        Map<Integer, String> x = new HashMap<>();

        /*for (int i: x) {

            x.put(10, "1");
        }
*/


        if (recuperationXY.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        return Byte.valueOf((recuperationXY.get(0))[0]);

    }
}