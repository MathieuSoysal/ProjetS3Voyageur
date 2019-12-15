package projetS3Voyageur.ModesDeRecherches.acm;

import java.awt.Point;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    private int[] listeAdjacence;

    public static void main(String[] args) {
        Kruskal k = new Kruskal();

        Pays pays = new Pays(6);

        pays.setPositionVille(0, new Point(1, 4));
        pays.setPositionVille(1, new Point(2, 5));
        pays.setPositionVille(2, new Point(3, 3));
        pays.setPositionVille(3, new Point(4, 2));
        pays.setPositionVille(4, new Point(1, 5));
        pays.setPositionVille(5, new Point(1, 3));

        k.genereArbre(pays, (1 << 3));
        System.out.println(k.toString());
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
        return genereArbre(pays, 0);
    }

    /**
     * Génère et renvoie l'arbre minimum recouvrant du graphe/Pays donné en
     * paramètre en ignorant les noeuds donnée en pramètre.
     * 
     * @param pays            {@code Pays} représente le graphe où l'arbre minimum
     *                        recouvrant doit être trouvé.
     * 
     * @param listeNoirNoeuds {@code int} représente les noeuds qui doivent être
     *                        ignorés lors de la recherche de l'abre minimum
     *                        recouvrant. (vecteur de bit où chaque bit représente
     *                        un noeud)
     * 
     * @return {@code int[]} Retourne la liste d'adjacence des noeuds du graphe.
     */
    public int[] genereArbre(final Pays pays, final int listeNoirNoeuds) {
        final int TAILLE = pays.getNombreDeVilles();
        final int OVERFLOW = (1 << (TAILLE)) - 1;

        int[] noeudsConnecte = new int[TAILLE], listeAdjacence = new int[TAILLE];

        final int OVERFLOW_NOEUD_ADJACENCE = OVERFLOW ^ listeNoirNoeuds;

        final byte noeudReferant = (byte) Math.getExponent(getNoeudNonConnecte(1, listeNoirNoeuds));

        while (noeudsConnecte[noeudReferant] < OVERFLOW_NOEUD_ADJACENCE) {
            double poidsAreteMin = Double.MAX_VALUE;
            Byte[] adjacents = new Byte[2];

            int noeudVisites = listeNoirNoeuds;
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
        this.listeAdjacence = listeAdjacence;
        return listeAdjacence;
    }

    /**
     * Actualise chacune des villes de la {@code int[]} liste d'ajacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * @param OVERFLOW       {@code int} représente un noeud voisin à tous les
     *                       autres noeuds du graphe.
     * 
     * @param noeudsConnecte {@code int[]} la liste d'ajacence à actualiser.
     * 
     * @param adjacents      {@code Byte[]} La nouvelle arête à ajouter dans la
     *                       liste d'adjacence.
     */
    private void actualiseNoeudsConnecte(final int OVERFLOW, int[] noeudsConnecte, final Byte[] adjacents) {

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
     * @param noeudActuel  Chaque bit du int représente une ville seul l'un des bits
     *                     est à 1, elle représente la ville actuelle
     * 
     * @param noeudVisites Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private int getNoeudNonConnecte(int noeudActuel, final int noeudVisites) {
        noeudActuel += noeudVisites;
        return noeudActuel ^ (noeudActuel & noeudVisites);
    }
    // TODO: Pensais à crée une class pour l'utilisation binaire

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        int i = 0;
        String resultat = "";
        for (int adjacents : listeAdjacence) {
            resultat += (String.format("\n Noeud n°%s connectés : ", i));

            int noeudVisite = (1 << i++) | (adjacents ^ ((1 << 10) - 1));

            for (int j = getNoeudNonConnecte(1, noeudVisite); j < ((1 << 10) - 1); j = getNoeudNonConnecte(j << 1,
                    noeudVisite)) {
                resultat += (Math.getExponent(j) + " ");
                noeudVisite |= j;

            }
        }
        return resultat;
    }

}