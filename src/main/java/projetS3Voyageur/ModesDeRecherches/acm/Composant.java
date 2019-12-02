package projetS3Voyageur.ModesDeRecherches.acm;

class Composant {
    public int bin = 0;
    public Composant tete = this;

    /**
     * @param bin
     */
    public Composant(int bin) {
        this.bin = bin;
    }

}