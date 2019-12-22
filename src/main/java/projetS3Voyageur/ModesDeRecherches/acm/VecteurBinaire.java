package projetS3Voyageur.ModesDeRecherches.acm;

public class VecteurBinaire {
    private int overflow;
    private int valeurBinaire = 1; // TODO à voir si on le nomme pas emplacementBinaire ou un autre nom
    private int listeNoireEmplacement;

    private byte numeroNoeud;

    // #region Constructeurs
    /**
     * Chacun des bits des vecteurs binaires représante un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param valeurBinaire         Vecteur binaire dans {@code int} seul un de ces
     *                              bits doit être à 1, celui-ci représante le noeud
     *                              actuel.
     * @param listeNoireEmplacement Vecteur de bits dans un {@code int} , chacun des
     *                              des bits à 1 sont ignorés par la class
     * @param taille                {@code byte} L'effectif des noeuds au sein du
     *                              graphe.
     */
    public VecteurBinaire(int valeurBinaire, int listeNoireEmplacement, byte taille) {
        this.valeurBinaire = valeurBinaire;
        initialise(listeNoireEmplacement, taille);
    }

    /**
     * Chacun des bits des vecteurs binaires représante un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireEmplacement Vecteur de bits dans un {@code int} , chacun des
     *                              des bits à 1 sont ignorés par la class
     * @param taille                {@code byte} L'effectif des noeuds au sein du
     *                              graphe.
     */
    public VecteurBinaire(int listeNoireEmplacement, byte taille) {
        initialise(listeNoireEmplacement, taille);
    }

    /**
     * Chacun des bits des vecteurs binaires représante un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireEmplacement Vecteur de bits dans un {@code int} , chacun des
     *                              des bits à 1 sont ignorés par la class
     * @param OVERFLOW              Vecteur de bits dans un {@code int} où tous les
     *                              bits représantant un noeud sont à 1 sauf le
     *                              dernier bit.
     */
    public VecteurBinaire(int listeNoireEmplacement, final int OVERFLOW) {
        initialise(listeNoireEmplacement, OVERFLOW);
    }
    // #endregion Contructeurs

    // #region Méthodes public de la class
    /**
     * @return {@code boolean} si le noeud actuel à un noeud suivant retourne
     *         {@code true} sinon {@code false}.
     */
    public boolean haseNext() {
        return valeurBinaire < overflow;
    }

    /**
     * Passe au noeud suivant en fonction de la liste noire.
     */
    public void next() {
        valeurBinaire <<= 1;
        verifieListeNoire();
    }
    // #endregion Méthodes public de class

    // #region Outils interne à la class
    /**
     * Verifie que la variable valeurBinaire n'est pas dans la liste noire si elle y
     * est elle change sa valeur pour qu'elle n'y soit plus.
     */
    private void verifieListeNoire() {
        valeurBinaire += listeNoireEmplacement;
        valeurBinaire ^= (valeurBinaire & listeNoireEmplacement);

        numeroNoeud = (byte) Math.getExponent(valeurBinaire);
    }

    private void initialise(int listeNoireEmplacement, byte taille) {
        initialise(listeNoireEmplacement, (1 << (taille)) - 1);
    }

    private void initialise(int listeNoireEmplacement, final int OVERFLOW) {
        this.overflow = OVERFLOW;
        this.listeNoireEmplacement = listeNoireEmplacement;
        verifieListeNoire();
    }
    // #endregion Outils interne à la class

    // #region Getters
    /**
     * 
     * @return Chaque bit du {@code int} retourné représente un noeud, seul le bit
     *         représantant le noeud actuel est à 1.
     */
    public int getValeurBinaire() {
        return valeurBinaire;
    }

    /**
     * 
     * @return {@code byte} renvoie le numéro du noeud actuel.
     */
    public byte getNumeroNoeud() {
        return numeroNoeud;
    }
    // #endregion Getters

}