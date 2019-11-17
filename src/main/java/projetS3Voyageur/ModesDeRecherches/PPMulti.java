package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.CompositionPays.Pays;

public class PPMulti implements ModeRecherche {

    private int toutesVillesVisitees;

    private Pays pays;

    private byte nombreDeVilles;

    private double distanceOptimum;
    private String villesEmprunteesOptimum;

    public static void main(String[] args) {
        ModeRecherche ppMulti = new PPMulti();
        Pays pays = new Pays(4);
        ppMulti.recherche(pays, 0);
        System.out.println(ppMulti.getParcours().getVillesEmprunté());
        System.out.println(ppMulti.getParcours().getDistance());


        ModeRecherche trkP = new TrackProchesMulti();
        trkP.recherche(pays, 0);
        System.out.println(trkP.getParcours().getVillesEmprunté());
        System.out.println(trkP.getParcours().getDistance());

    }

    /**
     * Recherche depuis toute les villes de départ possible le parcours le plus
     * optimisé pour visiter toutes les villes d'un pays, en allant à la ville la
     * plus proche non visitée.
     * 
     * @param pays          Le pays concerné par la recherche
     * @param villeInitiale Le numéro de la ville de départ
     */
    @Override
    public void recherche(final Pays pays, final int villeInitiale) {
        this.pays = pays;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        toutesVillesVisitees = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;
        villesEmprunteesOptimum = String.valueOf(villeInitiale);

        for (byte i = 0; i < nombreDeVilles; i++) {
            rechercheAux(1 << i, i);
        }

        final int indexVilleInitial = villesEmprunteesOptimum.indexOf(String.valueOf(villeInitiale));

        villesEmprunteesOptimum = villesEmprunteesOptimum.substring(indexVilleInitial,
                (villesEmprunteesOptimum.length())) + villesEmprunteesOptimum.substring(0, indexVilleInitial)
                + villeInitiale;

    }

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisitees Villes qui ont été visitées jusqu'à présent.
     * @param villeActuelle  Ville où se situe l'algorithme.
     */
    private void rechercheAux(int villesVisitees, byte villeDepart) {

        byte villeActuelle = villeDepart;
        byte villePlusProche = villeActuelle;
        double distanceObtenue = 0;
        String villesEmpruntees = String.valueOf(villeDepart);

        for (byte numVille = 0; numVille < nombreDeVilles - 1; numVille++) {

            double distanceVillePlusProche = Double.MAX_VALUE;

            int villesVisiteesIeration = villesVisitees;
            for (int villeFormatBinaire2 = villeNonVisitee(1 << (villeDepart),
                    villesVisitees); villeFormatBinaire2 < toutesVillesVisitees; villeFormatBinaire2 = villeNonVisitee(
                            villeFormatBinaire2 << 1, villesVisiteesIeration)) {
                villesVisiteesIeration |= villeFormatBinaire2;
                // TODO : à voir si instancier à chaque fois la variable est
                final byte villeIteration = (byte) Math.getExponent(villeFormatBinaire2);
                final double distanceIteration = pays.getDistanceEntreVilles(villeActuelle, villeIteration);

                if ((distanceIteration < distanceVillePlusProche)) {
                    villePlusProche = villeIteration;
                    distanceVillePlusProche = distanceIteration;
                }
            }

            villeActuelle = villePlusProche;
            distanceObtenue += distanceVillePlusProche;
            // TODO: ce block est resté avec l'ancienne version
            villesVisitees |= 1 << villePlusProche;
            villesEmpruntees += ">" + villePlusProche;
            // TODO: possible d'utiliser des méthodes privé pour aléger le code

        }

        distanceObtenue += pays.getDistanceEntreVilles(villePlusProche, villeDepart);
        if (distanceObtenue < distanceOptimum) {
            distanceOptimum = distanceObtenue;
            villesEmprunteesOptimum = villesEmpruntees + ">";
        }
    }

    // #region Outils

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle fait passer la ville actuelle à une ville non
     * visitée.
     * 
     * @param villeActuelle  Chaque bit du int représente une ville seul l'un des
     *                       bits est à 1, elle représente la ville actuelle
     * 
     * @param villesVisitees Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private int villeNonVisitee(int villeActuelle, final int villesVisitees) {
        villeActuelle += villesVisitees;
        return (villeActuelle <= toutesVillesVisitees || villesVisitees == toutesVillesVisitees)
                ? (villeActuelle ^ (villeActuelle & villesVisitees))
                : villeNonVisitee(1, villesVisitees);
    }

    // #endregion Outils

    // #region Getters

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    @Override
    public Parcours getParcours() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche

        return new Parcours(distanceOptimum, villesEmprunteesOptimum);
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "PlusProche v3";
    }

    // #endregion Getters

}