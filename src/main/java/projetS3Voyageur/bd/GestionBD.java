package projetS3Voyageur.bd;

import java.util.HashMap;
import java.util.List;

import java.awt.Point;

public class GestionBD {

    private static HashMap<Byte, String> repertoireIdVille = new HashMap<>();

    /**
     * Récupère le nombre ville référençais par l'idCarte donnée en paramètre.
     * 
     * @param idCarte {@code String} dont le nombre de ville doit être récupéré.
     * @return {@code Byte} Nbville que l'idCarte référence.
     */
    public static byte getNbVille(final String idCarte) {

        InteractionBD.connexion();

        final List<String[]> recuperationBD = InteractionBD
                .recuperationBD("SELECT nbVille FROM Carte WHERE idCarte = " + idCarte);

        if (recuperationBD.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        return Byte.valueOf((recuperationBD.get(0))[0]);

    }

    /**
     * Renvoie la liste de ville de l'idCarte sous forme d'un tableau de Point
     * 
     * @param idCarte {@code String} L'idCarte dont la liste de villes doit être
     *                extrait.
     * @return {@code Point[]}
     */
    public static Point[] getCarte(final String idCarte) {

        InteractionBD.connexion();

        final List<String[]> recuperationXY = InteractionBD.recuperationBD(
                "SELECT  X, Y , V.idVille FROM Carte C JOIN Ville V ON C.idCarte = V.idCarte WHERE V.idCarte = '"
                        + idCarte + "' ");

        if (recuperationXY.isEmpty()) {
            System.err.println("La requête n'a retourné aucun résultat");
            // TODO: crée une exception
        }

        final Point[] resultat = new Point[getNbVille(idCarte)];

        for (byte i = 0; i < resultat.length; i++) {
            final String[] tuple = recuperationXY.get(i);
            resultat[i] = new Point(Integer.valueOf(tuple[0]), (Integer.valueOf(tuple[1])));
            repertoireIdVille.put(i, tuple[2]);
        }

        return resultat;

    }

    public static void envoieParcours(String ordreVille) {

        InteractionBD.connexion();

        final String[] listeIdVille = ordreVille.split(">");

        ordreVille = repertoireIdVille.get(Byte.valueOf(listeIdVille[0]));

        for (int i = 1; i < listeIdVille.length; i++) {
            ordreVille += ">" + repertoireIdVille.get(Byte.valueOf(listeIdVille[i]));
        }

        InteractionBD.setRequete("Requête pour ajouter la liste" + ordreVille);
    }

    public static void envoieParcours(final byte[] ordreVilles_p) {

        InteractionBD.connexion();

        String ordreVilles = repertoireIdVille.get(ordreVilles_p[0]);

        for (byte i = 1; i < ordreVilles_p.length; i++) {
            ordreVilles += ">" + repertoireIdVille.get(ordreVilles_p[i]);
        }

        InteractionBD.setRequete("Requête pour ajouter la liste" + ordreVilles);
    }



    // TODO: Je pense qu'il est mieux que ça soit le client qui convertit les numVille en idVille afin d'alléger les calculs serveur
}