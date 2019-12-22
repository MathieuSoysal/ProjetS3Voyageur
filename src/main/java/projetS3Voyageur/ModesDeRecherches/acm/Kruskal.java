package projetS3Voyageur.ModesDeRecherches.acm;

import java.awt.Point;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    // TODO vérifier les fautes d'orthographes

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    private int OVERFLOW;

    private int[] listeAdjacences;

    public static void main(String[] args) {
        Kruskal k = new Kruskal();

        Pays pays = new Pays(11);

        pays.setPositionVille(0, new Point(6, 10));
        pays.setPositionVille(1, new Point(8, 3));
        pays.setPositionVille(2, new Point(2, 4));
        pays.setPositionVille(3, new Point(1, 7));
        pays.setPositionVille(4, new Point(9, 2));
        pays.setPositionVille(5, new Point(7, 1));
        pays.setPositionVille(6, new Point(8, 5));
        pays.setPositionVille(7, new Point(6, 2));
        pays.setPositionVille(8, new Point(5, 6));
        pays.setPositionVille(9, new Point(2, 1));
        pays.setPositionVille(10, new Point(4, 4));

        k.genereArbre(pays);
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
     * @param pays             {@code Pays} représente le graphe où l'arbre minimum
     *                         recouvrant doit être trouvé.
     * 
     * @param listeNoireNoeuds {@code int} représente les noeuds qui doivent être
     *                         ignorés lors de la recherche de l'abre minimum
     *                         recouvrant. (vecteur de bits où chaque bit représente
     *                         un noeud)
     * 
     * @return {@code int[]} Retourne la liste d'adjacence des noeuds du graphe.
     */
    public int[] genereArbre(final Pays pays, final int listeNoireNoeuds) {

        final byte TAILLE = (byte) pays.getNombreDeVilles();

        this.OVERFLOW = (1 << (TAILLE)) - 1;

        final int OVERFLOW_RESEAU_NOEUDS = OVERFLOW ^ listeNoireNoeuds;

        final byte NOEUD_REFERANT = getNoeudHorsListeNoire(listeNoireNoeuds);

        int[] reseauNoeuds = new int[TAILLE], listeAdjacences = new int[TAILLE];

        while (reseauNoeuds[NOEUD_REFERANT] < OVERFLOW_RESEAU_NOEUDS) {
            double poidsMinArete = Double.MAX_VALUE;
            byte[] aretePoidsMin = new byte[2];

            int noeudsVisites = listeNoireNoeuds;

            for (VecteurBinaire noeudi = VecteurBinaire.AvecOVERFLOW(noeudsVisites, OVERFLOW >> 1); noeudi
                    .haseNext(); noeudi.next()) {

                noeudsVisites |= noeudi.getValeurBinaire();
                byte numeroNoeudi = noeudi.getNumeroNoeud();

                for (VecteurBinaire noeudj = VecteurBinaire.AvecOVERFLOW(noeudsVisites, OVERFLOW); noeudj
                        .haseNext(); noeudj.next()) {

                    byte numeroNoeudj = noeudj.getNumeroNoeud();

                    double poidsArete = pays.getDistanceEntreVilles(numeroNoeudi, numeroNoeudj);

                    if (poidsArete < poidsMinArete && neCreePasDeCycle(reseauNoeuds, noeudi, noeudj)) {

                        poidsMinArete = poidsArete;
                        actualiseNoeudsArete(aretePoidsMin, numeroNoeudi, numeroNoeudj);
                        // TODO Il est possible d'augmenter la portée des variables numereNoeud Pour
                        // évité d'utilisais l'arete
                    }
                }
            }

            actualiseListeAdjacences(listeAdjacences, aretePoidsMin);

            actualiseReseauxNoeuds(OVERFLOW, reseauNoeuds, aretePoidsMin);
        }
        this.listeAdjacences = listeAdjacences;
        return listeAdjacences;
    }

    // #region Boite à outils de la class

    /**
     * Vérifie que l'ajout de l'arete qui relie le noeud i et le noeud j ne crée pas
     * un cycle avec le reste de l'arbre.
     * 
     * @param reseauNoeuds {@code int[]} chaque {@code int} du tableau représente
     *                     les noeuds au quelle le noeud est connecté (tout degrès
     *                     de connexion confondue)
     * @param noeudi       {@code VecteurBinaire} représantant l'une des extrémité
     *                     de l'arete
     * @param noeudj       {@code VecteurBinaire} représantant l'une des extrémité
     *                     de l'arete
     * 
     * @return {@code boolean} retourne {@code true} si ne crée pas de cycle, sinon
     *         {@code false}.
     */
    private boolean neCreePasDeCycle(int[] reseauNoeuds, VecteurBinaire noeudi, VecteurBinaire noeudj) {

        final int INTERSECTION = reseauNoeuds[noeudi.getNumeroNoeud()] & reseauNoeuds[noeudj.getNumeroNoeud()];

        final int I_J = noeudi.getValeurBinaire() | noeudj.getValeurBinaire();
        final int I_J_COMPRIS_DANS_INTERSECTION = INTERSECTION & I_J;

        return I_J_COMPRIS_DANS_INTERSECTION != I_J;
    }

    /**
     * Renvoie un numéro de noeud qui n'est pas compris dans la liste noire.
     * 
     * @param listeNoire Chaque bit à 1 du int représente les noeuds à ignorer.
     * 
     * @return {@code byte} Renvoie un numéro de noeud qui n'est pas compris dans la
     *         liste noire.
     */
    private byte getNoeudHorsListeNoire(final int listeNoire) {
        int noeudHorsListeNoire = listeNoire + 1;
        return (byte) Math.getExponent((noeudHorsListeNoire ^ (noeudHorsListeNoire & listeNoire)));
    }

    // #region méthodes actualisant une liste

    /**
     * Actualise chacune des villes de la {@code int[]} liste d'ajacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * 
     * @param reseauNoeuds {@code int[]} chaque {@code int} du tableau représente
     *                     les noeuds au quelle le noeud est connecté (tous degrès
     *                     de connexion confondue)
     * 
     * @param arete        {@code Byte[]} La nouvelle arete à ajouter dans les
     *                     reseau de connxion des noeuds.
     */
    private void actualiseReseauxNoeuds(final int OVERFLOW, int[] reseauNoeuds, final byte[] arete) {

        final int EXTREMITES_ARETE = (1 << arete[EXTREMITE_X]) | (1 << arete[EXTREMITE_Y]);
        final int UNION_RESEAUX = reseauNoeuds[arete[EXTREMITE_X]] | reseauNoeuds[arete[EXTREMITE_Y]];
        final int NOUVEAU_RESEAU = UNION_RESEAUX | EXTREMITES_ARETE;
        final int NOEUDS_HORS_RESEAU = NOUVEAU_RESEAU ^ OVERFLOW;

        for (VecteurBinaire noeudi = VecteurBinaire.AvecOVERFLOW(NOEUDS_HORS_RESEAU, OVERFLOW); noeudi
                .haseNext(); noeudi.next()) {
            reseauNoeuds[noeudi.getNumeroNoeud()] = NOUVEAU_RESEAU;
        }

    }

    private void actualiseNoeudsArete(byte[] arete, byte numeroNoeudi, byte numeroNoeudj) {
        arete[EXTREMITE_X] = numeroNoeudi;
        arete[EXTREMITE_Y] = numeroNoeudj;
    }

    private void actualiseListeAdjacences(int[] listeAdjacences, byte[] aretePoidsMin) {
        listeAdjacences[aretePoidsMin[EXTREMITE_X]] |= 1 << aretePoidsMin[EXTREMITE_Y];
        listeAdjacences[aretePoidsMin[EXTREMITE_Y]] |= 1 << aretePoidsMin[EXTREMITE_X];
    }

    // #endregion méthodes actualisant une liste
    // #endregion Boite à outils de la class

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {

        String resultat = "";

        for (int numeroNoeudi = 0; numeroNoeudi < listeAdjacences.length; numeroNoeudi++) {

            resultat += (String.format(" Noeud n°%s connectés : ", (numeroNoeudi + 1)));
            // Ajout de 1 pour commencé par 1 au lieu de 0

            resultat += recupereAdjacenceFormatNumerique(listeAdjacences[numeroNoeudi], numeroNoeudi) + "\n";

        }
        return resultat;
    }

    /**
     * Renvoie un {@code String} possédant une suite de numéro de noeuds
     * correspondant à l'adjacence du noeud donné en paramètre.
     * 
     * @param adjacence   {@code int} Adjacence sous forme de vecteur de bit
     * @param numeroNoeud {@code byte} Numero du noeud auquel l'adjacence doit être
     *                    convertie en format numérique.
     * @return {@code String} suite de numéro de noeuds correspondant à l'adjacence
     *         du noeud donné en paramètre.
     */
    private String recupereAdjacenceFormatNumerique(int adjacence, int numeroNoeud) {
        String adjacenceFormatNumerique = "";

        int noeudsHorsAdjacence = (1 << numeroNoeud) | (adjacence ^ OVERFLOW);

        for (VecteurBinaire noeudi = VecteurBinaire.AvecOVERFLOW(noeudsHorsAdjacence, OVERFLOW); noeudi
                .haseNext(); noeudi.next()) {

            adjacenceFormatNumerique += ((noeudi.getNumeroNoeud() + 1) + " ");
        }

        return adjacenceFormatNumerique;
    }

}