package projetS3Voyageur.ModesDeRecherches.acm;

import projetS3Voyageur.ModesDeRecherches.Parcours;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    public static void main(String[] args) {
        final int TAILLE = 30;
        int noeudVisites;
        final int OVERFLOW = (1 << (TAILLE)) - 1;
        final Pays pays = new Pays(TAILLE);

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

        for (Byte[] arete : arbre) {
            System.out.println(String.format("[%s,%s] distance ->", arete[EXTREMITE_X], arete[EXTREMITE_Y]));
        }

    }

    /**
     * Actualise chacune des villes de la {@code int[]} listeAdjacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * @param OVERFLOW  {@code int}      représente un noeud voisin à tous les autres noeuds du
     *                       graphe.
     * @param listeAdjacence {@code int[]} la liste d'ajacence à actualiser.
     * @param arete {@code Byte[]} La nouvelle arête à ajouter dans la liste d'adjacence.
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

    private static final boolean nonVisitee = false;
    private Pays pays;

    private int villeInitale;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;

    private void initChaineTrie() {
        final int TAILLE = 4;
        int noeudVisites = 1 << 1;
        final int OVERFLOW = (1 << (TAILLE)) - 1;
        for (int i = recupereNoeudNonConnecte(1, noeudVisites); i < (OVERFLOW >> 1); i = recupereNoeudNonConnecte(
                i << 1, noeudVisites)) {
            noeudVisites |= i;
            for (int j = recupereNoeudNonConnecte(1, noeudVisites); j < OVERFLOW; j = recupereNoeudNonConnecte(j << 1,
                    noeudVisites)) {
                System.out.println(String.format("[%s,%s]", Math.getExponent(i), Math.getExponent(j)));
            }
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