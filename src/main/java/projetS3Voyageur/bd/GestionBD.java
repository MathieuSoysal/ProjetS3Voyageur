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
    public Point[] getCarte(final String idCarte) {

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

    /**
     * Insert un tuple dans la table Parcours avec comme éléments les variables en
     * paramètre de la méthode.
     *
     * @param idCarte     {@code String} #idCarte
     * @param nomAlgo     {@code Stirng} nomAlgo
     * @param fini        {@code boolean} isFinished
     * @param distance    {@code String} cost
     * @param ordreVilles {@code String} ordreVilles
     */
    public void envoieParcours(final String idCarte, final String nomAlgo, final boolean fini, String ordreVilles,
                               final String distance) {

        insertParcours(idCarte, nomAlgo, fini, distance, convertieNumVersId(ordreVilles));
    }

    /**
     * Insert un tuple dans la table Parcours avec comme éléments les variables en
     * paramètre de la méthode.
     *
     * @param idCarte     {@code String} #idCarte
     * @param nomAlgo     {@code Stirng} nomAlgo
     * @param fini        {@code boolean} isFinished
     * @param distance    {@code String} cost
     * @param ordreVilles {@code byte[]} ordreVilles
     */
    public void envoieParcours(final String idCarte, final String nomAlgo, final boolean fini, final byte[] ordreVilles,
                               final String distance) {

        insertParcours(idCarte, nomAlgo, fini, distance, convertieNumVersId(ordreVilles));
    }

    // #region Outils

    /**
     * Insert un tuple dans la table Parcours avec comme éléments les variables en
     * paramètre de la méthode.
     *
     * @param idCarte     {@code String} #idCarte
     * @param nomAlgo     {@code Stirng} nomAlgo
     * @param fini        {@code boolean} isFinished
     * @param distance    {@code String} cost
     * @param ordreVilles {@code String} ordreVilles
     */
    private void insertParcours(final String idCarte, final String nomAlgo, final boolean fini, final String distance,
                                String ordreVilles) {

        InteractionBD.connexion();
        InteractionBD.setRequete(String.format(
                "INSERT INTO Parcours Set idCarte = '%s', nomAlgo = '%s', isFinished = '%s', ordreVilles = '%s', cost = '%s' ;",
                idCarte, nomAlgo, ((fini) ? 1 : 0), ordreVilles, distance));
    }

    /**
     * Convertie les numéros de ville présant dans un String vers leur idVille
     * correspondant.
     *
     * @param ordreVilles {@code String} Contient l'ordre des numéro de ville
     * @return {@code String} Contient l'ordre des idVille
     */
    private static String convertieNumVersId(String ordreVilles) {

        InteractionBD.connexion();

        final String[] listeIdVille = ordreVilles.split(">");

        ordreVilles = repertoireIdVille.get(Byte.valueOf(listeIdVille[0]));

        for (int i = 1; i < listeIdVille.length; i++) {
            ordreVilles += ">" + repertoireIdVille.get(Byte.valueOf(listeIdVille[i]));
        }

        return ordreVilles;
    }

    /**
     * Convertie les numéro de ville présent au sein du tableau de byte vers leur
     * idVille corresepondant.
     *
     * Pour renvoyer une chaine de caractères contenant l'ordre des idVille.
     *
     * @param ordreVilles_p {@code byte[]} tableau de {@code byte} contenant l'ordre
     *                      des numVille
     *
     * @return {@code String} Retourne une chaine de caractères contenant l'ordre
     *         des idVille
     */
    private static String convertieNumVersId(final byte[] ordreVilles_p) {

        InteractionBD.connexion();

        String ordreVilles = repertoireIdVille.get(ordreVilles_p[0]);

        for (byte i = 1; i < ordreVilles_p.length; i++) {
            ordreVilles += ">" + repertoireIdVille.get(ordreVilles_p[i]);
        }

        return ordreVilles;
    }
    // #endregion Outils

    // TODO: Je pense qu'il est mieux que ça soit le client qui convertit les
    // numVille en idVille afin d'alléger les calculs serveur
}