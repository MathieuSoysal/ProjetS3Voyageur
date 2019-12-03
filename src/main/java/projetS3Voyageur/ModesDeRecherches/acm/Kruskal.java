package projetS3Voyageur.ModesDeRecherches.acm;


import java.util.LinkedList;
import java.util.Queue;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    public static void main(String[] args) {
        Kruskal k = new Kruskal();
        for (Byte[] arete : k.genereArbre(new Pays(10))) {
            System.out.println(String.format("[ %s , %s ]", arete[0], arete[1]));
        }

    }

    /**
     * Génère et renvoie l'arbre minimum recouvrant du graphe/Pays donné en
     * paramètre.
     * 
     * @param pays {@code Pays} représente le graphe où l'arbre minimum recouvrant
     *             doit être trouvé.
     * 
     * @return {@code Queue<Byte[]>} Retourne la liste des arêtes classées par poids
     *         avec un ordre croissant.
     */
    public Queue<Byte[]> genereArbre(final Pays pays) {
        final int TAILLE = pays.getNombreDeVilles();
        final int OVERFLOW = (1 << (TAILLE)) - 1;
        int noeudVisites;

        Queue<Byte[]> arbre = new LinkedList<>();

        int[] listeAdjacence = new int[TAILLE];

        while (listeAdjacence[0] < OVERFLOW) {
            double poidsAreteMin = Double.MAX_VALUE;
            Byte[] arete = new Byte[2];

            noeudVisites = 0;
            byte numVillei = 0, numVillej = 0;

            for (int i = recupereNoeudNonConnecte(1, noeudVisites); i < (OVERFLOW >> 1); i = recupereNoeudNonConnecte(
                    i << 1, noeudVisites)) {

                noeudVisites |= i;
                numVillei = (byte) Math.getExponent(i);

                for (int j = recupereNoeudNonConnecte(1, noeudVisites); j < OVERFLOW; j = recupereNoeudNonConnecte(
                        j << 1, noeudVisites)) {

                    numVillej = (byte) Math.getExponent(j);

                    final double poidsArete = pays.getDistanceEntreVilles(numVillei, numVillej);

                    if (poidsArete < poidsAreteMin
                            && (((listeAdjacence[numVillei] & listeAdjacence[numVillej]) & (i | j)) != (i | j))) {

                        poidsAreteMin = poidsArete;
                        arete[EXTREMITE_X] = numVillei;
                        arete[EXTREMITE_Y] = numVillej;

                    }
                }
            }

            arbre.offer(arete.clone());

            actualiseListeAdjacence(OVERFLOW, listeAdjacence, arete);
        }
        return arbre;
    }

    /**
     * Actualise chacune des villes de la {@code int[]} listeAdjacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * @param OVERFLOW       {@code int} représente un noeud voisin à tous les
     *                       autres noeuds du graphe.
     * @param listeAdjacence {@code int[]} la liste d'ajacence à actualiser.
     * @param arete          {@code Byte[]} La nouvelle arête à ajouter dans la
     *                       liste d'adjacence.
     */
    private static void actualiseListeAdjacence(final int OVERFLOW, int[] listeAdjacence, final Byte[] arete) {

        final int extremitesArete = (1 << arete[EXTREMITE_X]) | (1 << arete[EXTREMITE_Y]);
        final int sommetsConnectee = listeAdjacence[arete[EXTREMITE_X]] | listeAdjacence[arete[EXTREMITE_Y]]
                | extremitesArete;
        final int noeudsNonConnectes = sommetsConnectee ^ OVERFLOW;

        for (int i = recupereNoeudNonConnecte(1, noeudsNonConnectes); i < OVERFLOW; i = recupereNoeudNonConnecte(i << 1,
                noeudsNonConnectes)) {
            listeAdjacence[Math.getExponent(i)] = sommetsConnectee;
        }

    }

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle fait passer la ville actuelle à une ville non
     * visitée.
     * 
     * @param villeActuelle Chaque bit du int représente une ville seul l'un des
     *                      bits est à 1, elle représente la ville actuelle
     * 
     * @param noeudVisites  Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private static/* TODO: mit en static temporairement */ int recupereNoeudNonConnecte(int villeActuelle,
            final int noeudVisites) {
        villeActuelle += noeudVisites;
        return villeActuelle ^ (villeActuelle & noeudVisites);
    }
    // TODO: Pensais à crée une class pour l'utilisation binaire

}