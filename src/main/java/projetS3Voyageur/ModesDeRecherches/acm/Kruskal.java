package projetS3Voyageur.ModesDeRecherches.acm;

import java.util.LinkedList;
import java.util.Queue;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    public static void main(String[] args) {
        Kruskal k = new Kruskal();
        byte i = 0;
        for (int adjacents : k.genereArbre(new Pays(10))) {
            System.out.print(String.format("\n Noeud n°%s connectés : ", i));

            int noeudVisite = (1 << i++) | (adjacents ^ ((1 << 10) - 1));

            for (int j = getNoeudNonConnecte(1, noeudVisite); j < ((1 << 10) - 1); j = getNoeudNonConnecte(j << 1,
                    noeudVisite)) {
                System.out.print(Math.getExponent(j) + " ");
                noeudVisite |= j;

            }
        }

    }

    /**
     * Génère et renvoie l'arbre minimum recouvrant du graphe/Pays donné en
     * paramètre.
     * 
     * @param pays {@code Pays} représente le graphe où l'arbre minimum recouvrant
     *             doit être trouvé.
     * 
     * @return {@code int[]} Retourne la liste d'adjacence des noeuds du graphe.
     */
    public int[] genereArbre(final Pays pays) {
        final int TAILLE = pays.getNombreDeVilles();
        final int OVERFLOW = (1 << (TAILLE)) - 1;
        int noeudVisites;

        int[] noeudsConnecte = new int[TAILLE], listeAdjacence = new int[TAILLE];

        while (noeudsConnecte[0] < OVERFLOW) {
            double poidsAreteMin = Double.MAX_VALUE;
            Byte[] adjacents = new Byte[2];

            noeudVisites = 0;
            byte numVillei = 0, numVillej = 0;

            for (int i = getNoeudNonConnecte(1, noeudVisites); i < (OVERFLOW >> 1); i = getNoeudNonConnecte(i << 1,
                    noeudVisites)) {

                noeudVisites |= i;
                numVillei = (byte) Math.getExponent(i);

                for (int j = getNoeudNonConnecte(1, noeudVisites); j < OVERFLOW; j = getNoeudNonConnecte(j << 1,
                        noeudVisites)) {

                    numVillej = (byte) Math.getExponent(j);

                    final double poidsArete = pays.getDistanceEntreVilles(numVillei, numVillej);

                    if (poidsArete < poidsAreteMin
                            && (((noeudsConnecte[numVillei] & noeudsConnecte[numVillej]) & (i | j)) != (i | j))) {

                        poidsAreteMin = poidsArete;
                        adjacents[EXTREMITE_X] = numVillei;
                        adjacents[EXTREMITE_Y] = numVillej;

                    }
                }
            }

            listeAdjacence[adjacents[EXTREMITE_X]] |= 1 << adjacents[EXTREMITE_Y];
            listeAdjacence[adjacents[EXTREMITE_Y]] |= 1 << adjacents[EXTREMITE_X];

            actualiseNoeudsConnecte(OVERFLOW, noeudsConnecte, adjacents);
        }
        return listeAdjacence;
    }

    /**
     * Actualise chacune des villes de la {@code int[]} liste d'ajacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * @param OVERFLOW       {@code int} représente un noeud voisin à tous les
     *                       autres noeuds du graphe.
     * @param noeudsConnecte {@code int[]} la liste d'ajacence à actualiser.
     * @param adjacents      {@code Byte[]} La nouvelle arête à ajouter dans la
     *                       liste d'adjacence.
     */
    private static void actualiseNoeudsConnecte(final int OVERFLOW, int[] noeudsConnecte, final Byte[] adjacents) {

        final int extremitesArete = (1 << adjacents[EXTREMITE_X]) | (1 << adjacents[EXTREMITE_Y]);
        final int sommetsConnectee = noeudsConnecte[adjacents[EXTREMITE_X]] | noeudsConnecte[adjacents[EXTREMITE_Y]]
                | extremitesArete;
        final int noeudsNonConnectes = sommetsConnectee ^ OVERFLOW;

        for (int i = getNoeudNonConnecte(1, noeudsNonConnectes); i < OVERFLOW; i = getNoeudNonConnecte(i << 1,
                noeudsNonConnectes)) {
            noeudsConnecte[Math.getExponent(i)] = sommetsConnectee;
        }

    }

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle fait passer la ville actuelle à une ville non
     * visitée.
     * 
     * @param noeudActuel Chaque bit du int représente une ville seul l'un des
     *                      bits est à 1, elle représente la ville actuelle
     * 
     * @param noeudVisites  Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private static/* TODO: mit en static temporairement */ int getNoeudNonConnecte(int noeudActuel,
            final int noeudVisites) {
        noeudActuel += noeudVisites;
        return noeudActuel ^ (noeudActuel & noeudVisites);
    }
    // TODO: Pensais à crée une class pour l'utilisation binaire

}