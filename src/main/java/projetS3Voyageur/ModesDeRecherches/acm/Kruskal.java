package projetS3Voyageur.ModesDeRecherches.acm;

import java.awt.Point;

import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

//TODO vérifier les fautes d'orthographes

    private static final int EXTREMITE_Y = 1;

    private static final int EXTREMITE_X = 0;

    private int[] listeAdjacence;

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

        final byte TAILLE = (byte) pays.getNombreDeVilles();

        final int OVERFLOW = (1 << (TAILLE)) - 1;

        final int OVERFLOW_NOEUD_ADJACENCE = OVERFLOW ^ listeNoirNoeuds;

        final byte NOEUD_REFERANT = getNoeudHorsListeNoire(listeNoirNoeuds);

        int[] noeudsConnecte = new int[TAILLE], listeAdjacence = new int[TAILLE];

        while (noeudsConnecte[NOEUD_REFERANT] < OVERFLOW_NOEUD_ADJACENCE) {
            double poidsAreteMin = Double.MAX_VALUE;
            byte[] noeudsArete = new byte[2];

            int noeudVisites = listeNoirNoeuds;

            for (VecteurBinaire noeudi = new VecteurBinaire(noeudVisites, OVERFLOW >> 1); noeudi.haseNext(); noeudi
                    .next()) {

                noeudVisites |= noeudi.getValeurBinaire();
                byte numeroVillei = noeudi.getNumeroNoeud();

                for (VecteurBinaire noeudj = new VecteurBinaire(noeudVisites, TAILLE); noeudj.haseNext(); noeudj
                        .next()) {

                    byte numeroNoeudj = noeudj.getNumeroNoeud();

                    double poidsArete = pays.getDistanceEntreVilles(numeroVillei, numeroNoeudj);

                    if (poidsArete < poidsAreteMin && neCreePasDeCycle(noeudsConnecte, noeudi, noeudj)) {

                        poidsAreteMin = poidsArete;
                        actualiseNoeudsArete(noeudsArete, numeroVillei, numeroNoeudj);

                    }
                }
            }

            listeAdjacence[noeudsArete[EXTREMITE_X]] |= 1 << noeudsArete[EXTREMITE_Y];
            listeAdjacence[noeudsArete[EXTREMITE_Y]] |= 1 << noeudsArete[EXTREMITE_X];

            actualiseNoeudsConnecte(OVERFLOW, noeudsConnecte, noeudsArete);
        }
        this.listeAdjacence = listeAdjacence;
        return listeAdjacence;
    }

    /**
     * Vérifie que l'ajout de l'arete du noeud valeurBinaireNoeudi au noeud j ne
     * crée pas un cycle avec le reste de l'arbre.
     * 
     * @param noeudsConnecte {@code int[]} représente la liste d'adjacences des
     *                       noeuds
     * @param noeudi         {@code VecteurBinaire} du noeud i
     * @param noeudj         {@code VecteurBinaire} du noeud j
     * 
     * @return {@code boolean} retourne vrai si le Noeud valeurBinaireNoeudi et le
     *         Noeud j ne crée pas de cycle avec la liste d'ajacences.
     */
    private boolean neCreePasDeCycle(int[] noeudsConnecte, VecteurBinaire noeudi, VecteurBinaire noeudj) {

        final int INTERSECTION_NOEUDS_CONNECTES = noeudsConnecte[noeudi.getNumeroNoeud()]
                & noeudsConnecte[noeudj.getNumeroNoeud()];

        final int NOEUDS_I_J = noeudi.getValeurBinaire() | noeudj.getValeurBinaire();
        final int NOEUDS_I_J_DANS_INTERSECTION_NOEUDS_CONNECTES = INTERSECTION_NOEUDS_CONNECTES & NOEUDS_I_J;

        return NOEUDS_I_J_DANS_INTERSECTION_NOEUDS_CONNECTES != NOEUDS_I_J;
    }

    /**
     * Actualise chacune des villes de la {@code int[]} liste d'ajacence donné en
     * paramètre, selon l'arête donnée en paramètre.
     * 
     * 
     * @param noeudsConnecte {@code int[]} la liste d'ajacence à actualiser.
     * 
     * @param noeudsArete    {@code Byte[]} La nouvelle arête à ajouter dans la
     *                       liste d'adjacence.
     */
    private void actualiseNoeudsConnecte(final int OVERFLOW, int[] noeudsConnecte, final byte[] noeudsArete) {

        final int extremitesArete = (1 << noeudsArete[EXTREMITE_X]) | (1 << noeudsArete[EXTREMITE_Y]);
        final int sommetsConnectee = noeudsConnecte[noeudsArete[EXTREMITE_X]] | noeudsConnecte[noeudsArete[EXTREMITE_Y]]
                | extremitesArete;
        final int valeurBinaireNoeudj = sommetsConnectee ^ OVERFLOW;

        for (VecteurBinaire noeudi = new VecteurBinaire(valeurBinaireNoeudj, OVERFLOW); noeudi.haseNext(); noeudi
                .next()) {
            noeudsConnecte[noeudi.getNumeroNoeud()] = sommetsConnectee;
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

    private void actualiseNoeudsArete(byte[] noeudsArete, byte numeroVillei, byte numeroNoeudj) {
        noeudsArete[EXTREMITE_X] = numeroVillei;
        noeudsArete[EXTREMITE_Y] = numeroNoeudj;
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
        final int OVERFLOW = ((1 << listeAdjacence.length) - 1);
        for (int adjacence : listeAdjacence) {
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