package projetS3Voyageur.ModesDeRecherches.acm;

import java.awt.Point;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    // TODO vérifier les fautes d'orthographes

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

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
     *                         recouvrant. (vecteur de bit où chaque bit représente
     *                         un noeud)
     * 
     * @return {@code int[]} Retourne la liste d'adjacence des noeuds du graphe.
     */
    public int[] genereArbre(final Pays pays, final int listeNoireNoeuds) {

        final byte TAILLE = (byte) pays.getNombreDeVilles();

        final int OVERFLOW = (1 << (TAILLE)) - 1;

        final int OVERFLOW_RESEAU_NOEUDS = OVERFLOW ^ listeNoireNoeuds;

        final byte NOEUD_REFERANT = getNoeudHorsListeNoire(listeNoireNoeuds);

        int[] reseauxNoeud = new int[TAILLE], listeAdjacences = new int[TAILLE];

        while (reseauxNoeud[NOEUD_REFERANT] < OVERFLOW_RESEAU_NOEUDS) {
            double poidsMinArete = Double.MAX_VALUE;
            byte[] aretePoidsMin = new byte[2];

            int noeudsVisites = listeNoireNoeuds;

            for (VecteurBinaire noeudi = new VecteurBinaire(noeudsVisites, OVERFLOW >> 1); noeudi.haseNext(); noeudi
                    .next()) {

                noeudsVisites |= noeudi.getValeurBinaire();
                byte numeroNoeudi = noeudi.getNumeroNoeud();

                for (VecteurBinaire noeudj = new VecteurBinaire(noeudsVisites, TAILLE); noeudj.haseNext(); noeudj
                        .next()) {

                    byte numeroNoeudj = noeudj.getNumeroNoeud();

                    double poidsArete = pays.getDistanceEntreVilles(numeroNoeudi, numeroNoeudj);

                    if (poidsArete < poidsMinArete && neCreePasDeCycle(reseauxNoeud, noeudi, noeudj)) {

                        poidsMinArete = poidsArete;
                        actualiseNoeudsArete(aretePoidsMin, numeroNoeudi, numeroNoeudj);
                        // TODO Il est possible d'augmenter la portée des variables numereNoeud Pour
                        // évité d'utilisais l'arete
                    }
                }
            }

            listeAdjacences[aretePoidsMin[EXTREMITE_X]] |= 1 << aretePoidsMin[EXTREMITE_Y];
            listeAdjacences[aretePoidsMin[EXTREMITE_Y]] |= 1 << aretePoidsMin[EXTREMITE_X];

            actualiseReseauxNoeuds(OVERFLOW, reseauxNoeud, aretePoidsMin);
        }
        this.listeAdjacences = listeAdjacences;
        return listeAdjacences;
    }

    /**
     * Vérifie que l'ajout de l'arete qui relie le noeud i et le noeud j ne crée pas
     * un cycle avec le reste de l'arbre.
     * 
     * @param reseauxNoeud {@code int[]} chaque {@code int} du tableau représente
     *                     les noeuds au quelle le noeud est connecté (tout degrès
     *                     de connexion confondue)
     * @param noeudi       {@code VecteurBinaire} du noeud i
     * @param noeudj       {@code VecteurBinaire} du noeud j
     * 
     * @return {@code boolean} retourne vrai si le Noeud valeurBinaireNoeudi et le
     *         Noeud j ne crée pas de cycle avec la liste d'ajacences.
     */
    private boolean neCreePasDeCycle(int[] reseauxNoeud, VecteurBinaire noeudi, VecteurBinaire noeudj) {

        final int INTERSECTION_NOEUDS_CONNECTES = reseauxNoeud[noeudi.getNumeroNoeud()]
                & reseauxNoeud[noeudj.getNumeroNoeud()];

        final int NOEUDS_I_J = noeudi.getValeurBinaire() | noeudj.getValeurBinaire();
        final int NOEUDS_I_J_DANS_INTERSECTION_NOEUDS_CONNECTES = INTERSECTION_NOEUDS_CONNECTES & NOEUDS_I_J;

        return NOEUDS_I_J_DANS_INTERSECTION_NOEUDS_CONNECTES != NOEUDS_I_J;
    }

    /**
     * Actualise chacune des villes de la {@code int[]} liste d'ajacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * 
     * @param reseauxNoeud {@code int[]} chaque {@code int} du tableau représente
     *                     les noeuds au quelle le noeud est connecté (tous degrès
     *                     de connexion confondue)
     * 
     * @param arete        {@code Byte[]} La nouvelle arete à ajouter dans les
     *                     reseau de connxion des noeuds.
     */
    private void actualiseReseauxNoeuds(final int OVERFLOW, int[] reseauxNoeud, final byte[] arete) {

        final int ESTREMITES_ARETE = (1 << arete[EXTREMITE_X]) | (1 << arete[EXTREMITE_Y]);
        final int RESEAU_ACTUEL = reseauxNoeud[arete[EXTREMITE_X]] | reseauxNoeud[arete[EXTREMITE_Y]];
        final int NOUVEAU_RESEAU = RESEAU_ACTUEL | ESTREMITES_ARETE;
        final int NOEUDS_HORS_RESEAU = NOUVEAU_RESEAU ^ OVERFLOW;

        for (VecteurBinaire noeudi = new VecteurBinaire(NOEUDS_HORS_RESEAU, OVERFLOW); noeudi.haseNext(); noeudi
                .next()) {
            reseauxNoeud[noeudi.getNumeroNoeud()] = NOUVEAU_RESEAU;
        }

    }

    /**
     * Renvoie un numéro de noeud qui n'est pas compris dans la liste noire.
     * 
     * @param listeNoire Chaque bit à 1 du int représente les noeuds à ignoré.
     * 
     * @return {@code byte} Renvoie un numéro de noeud qui n'est pas compris dans la
     *         liste noire.
     */
    private byte getNoeudHorsListeNoire(final int listeNoire) {
        int noeudHorsListeNoire = listeNoire + 1;
        return (byte) Math.getExponent((noeudHorsListeNoire ^ (noeudHorsListeNoire & listeNoire)));
    }

    private void actualiseNoeudsArete(byte[] arete, byte numeroNoeudi, byte numeroNoeudj) {
        arete[EXTREMITE_X] = numeroNoeudi;
        arete[EXTREMITE_Y] = numeroNoeudj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        int numeroNoeudi = 0;
        String resultat = "";
        final int OVERFLOW = ((1 << listeAdjacences.length) - 1);
        for (int adjacence : listeAdjacences) {
            resultat += (String.format(" Noeud n°%s connectés : ", (numeroNoeudi + 1)));
            // Ajout de 1 pour commencé par 1 au lieu de 0

            int noeudVisite = (1 << numeroNoeudi++) | (adjacence ^ OVERFLOW);

            for (VecteurBinaire noeudi = new VecteurBinaire(noeudVisite, OVERFLOW); noeudi.haseNext(); noeudi.next()) {
                resultat += ((noeudi.getNumeroNoeud() + 1) + " ");
                noeudVisite |= noeudi.getValeurBinaire();

            }
            resultat += "\n";
        }
        return resultat;
    }

}