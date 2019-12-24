package projetS3Voyageur.ModesDeRecherches.acm;

public class VecteurBinaire {
    private int overflow;
    private int valeurBinaire = 1;
    private int listeNoireBits;

    private byte numeroNoeud;

    // #region Constructeurs
    /**
     * Chacun des bits des vecteurs binaires représantent un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param valeurBinaire  Vecteur binaire dans {@code int} seul un de ces bits
     *                       doit être à 1, celui-ci représente le noeud actuel.
     * @param listeNoireBits Vecteur de bits dans un {@code int} , chacun des bits à
     *                       1 est ignoré par la class.
     * @param taille         {@code byte} L'effectif des noeuds au sein du graphe.
     */
    public VecteurBinaire(int valeurBinaire, int listeNoireBits, byte taille) {
        this.valeurBinaire = valeurBinaire;
        initialise(listeNoireBits, taille);
    }

    /**
     * Chacun des bits des vecteurs binaires représantent un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireBits Vecteur de bits dans un {@code int} , chacun des bits à
     *                       1 est ignoré par la class.
     * @param taille         {@code byte} L'effectif des noeuds au sein du graphe.
     */
    private VecteurBinaire(int listeNoireBits, byte taille) {
        initialise(listeNoireBits, taille);
    }

    /**
     * Chacun des bits des vecteurs binaires représantent un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireBits Vecteur de bits dans un {@code int} , chacun des bits à
     *                       1 est ignoré par la class.
     * @param taille         {@code byte} L'effectif des noeuds au sein du graphe.
     */
    public static VecteurBinaire AvecTaille(int listeNoireBits, byte taille) {
        return new VecteurBinaire(listeNoireBits, taille);
    }

    /**
     * Chacun des bits des vecteurs binaires représantent un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireBits Vecteur de bits dans un {@code int} , chacun des bits à
     *                       1 est ignoré par la class.
     * @param OVERFLOW       Vecteur de bits dans un {@code int} où tous les bits
     *                       représentant un noeud sont à 1 sauf le dernier bit.
     */
    private VecteurBinaire(int listeNoireBits, final int OVERFLOW) {
        initialise(listeNoireBits, OVERFLOW);
    }

    /**
     * Chacun des bits des vecteurs binaires représantent un noeud, au sein de la
     * class les vecteurs binaires sont stockés dans un {@code int}.
     * 
     * @param listeNoireBits Vecteur de bits dans un {@code int} , chacun des bits à
     *                       1 est ignoré par la class.
     * @param OVERFLOW       Vecteur de bits dans un {@code int} où tous les bits
     *                       représentant un noeud sont à 1 sauf le dernier bit.
     */
    public static VecteurBinaire AvecOVERFLOW(int listeNoireBits, final int OVERFLOW) {
        return new VecteurBinaire(listeNoireBits, OVERFLOW);
    }
    // #endregion Contructeurs

    // #region Méthodes public de la class
    /**
     * @return {@code boolean} si le bit (noeud) actuel n'est pas le dernier bit
     *         (noeud) du vecteur de bits (graphe) retourne {@code true} sinon
     *         {@code false}.
     */
    public boolean haseNext() {
        return valeurBinaire < overflow;
    }

    /**
     * Passe au bite (noeud) suivant en fonction de la liste noire.
     */
    public void next() {
        valeurBinaire <<= 1;
        verifieListeNoire();
    }
    // #endregion Méthodes public de class

    // #region Outils interne à la class
    /**
     * Vérifie que la variable valeurBinaire n'est pas dans la liste noire si elle y
     * est elle change sa valeur pour qu'elle n'y soit plus.
     */
    private void verifieListeNoire() {
        valeurBinaire += listeNoireBits;
        valeurBinaire ^= (valeurBinaire & listeNoireBits);

        numeroNoeud = (byte) Math.getExponent(valeurBinaire);
    }

    private void initialise(int listeNoireBits, byte taille) {
        initialise(listeNoireBits, (1 << (taille)) - 1);
    }

    private void initialise(int listeNoireBits, final int OVERFLOW) {
        this.overflow = OVERFLOW;
        this.listeNoireBits = listeNoireBits;
        verifieListeNoire();
    }
    // #endregion Outils interne à la class

    // #region Getters
    /**
     * 
     * @return Chaque bit du {@code int} retourné représente un noeud, seul le bit
     *         représentant le noeud actuel est à 1.
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