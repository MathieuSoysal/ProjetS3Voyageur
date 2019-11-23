package projetS3Voyageur.bd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Point;

public class GestionBD {

    /**
     * Récupère le nombre ville référençais par l'idCarte donnée en paramètre.
     * 
     * @param idCarte {@code String} dont le nombre de ville doit être récupéré.
     * @return {@code Byte} Nbville que l'idCarte référence.
     */
    public static byte getNbVille(String idCarte) {

        InteractionBD.connexion();

        final List<String[]> recuperationBD = InteractionBD.recuperationBD("SELECT nbVille FROM Carte WHERE idCarte = " + idCarte);


        if (recuperationBD.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        return Byte.valueOf((recuperationBD.get(0))[0]);

    }


    /**
     * Renvoie la liste de ville de l'idCarte sous forme d'un tableau de Point
     * 
     * @param idCarte {@code String} L'idCarte dont la liste de villes doit être extrait.
     * @return {@code Point[]} 
     */
    public static Point[] getCarte(String idCarte) {

        InteractionBD.connexion();

        final List<String[]> recuperationXY = InteractionBD.recuperationBD("SELECT V.idVille, X, Y FROM Carte C JOIN Ville V ON C.idCarte = V.idCarte WHERE V.idCarte = '" + idCarte +"' ");

        if (recuperationXY.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        Point[] resultat = new Point[getNbVille(idCarte)];

        for (int i = 0; i < resultat.length; i++) {
            final String[] tuple = recuperationXY.get(i);
            resultat[i] = new Point(Integer.valueOf(tuple[0]),(Integer.valueOf(tuple[1])));
        }

        return resultat;

    }

    //TODO: Faire la méthode d'envoi du parcours (associer les numVille aux idVille)
}