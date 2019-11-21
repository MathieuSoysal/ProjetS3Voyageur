package projetS3Voyageur.bd;

import java.util.List;

public class GestionBD {

    /**
     * Récupère le nombre ville référençais par l'idCarte donnée en paramètre.
     * 
     * @param idCarte {@code String} dont le nombre de ville doit être récupéré.
     * @return {@code Byte} Nbville que l'idCarte référence.
     */
    public static byte getNbVille(String idCarte) {
        InteractionBD.connexion();

        List<String[]> recuperationBD = InteractionBD
                .recuperationBD("SELECT nbVille FROM Carte WHERE idCarte = " + idCarte);

        if (recuperationBD.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        return Byte.valueOf((recuperationBD.get(0))[0]);

    }
}