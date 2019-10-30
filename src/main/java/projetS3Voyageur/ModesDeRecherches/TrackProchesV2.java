package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class TrackProchesV2 implements ModeRecherche {

    private int toutesVillesVisitees;

    private Pays pays;
    private double[] distancesPlusCourt;

    private byte villeInitiale;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private byte[] villesEmprunteesOptimum;

    /**
     * Recherche depuis une ville de départ le parcours pour visiter toutes les
     * villes d'un pays, en allant à la ville la plus proche non visitée.
     * 
     * @param pays          Le pays concerné par la recherche
     * @param villeInitiale Le numéro de la ville de départ
     */
    @Override
    public void recherche(Pays pays, int villeInitiale) {
        this.pays = pays;
        this.villeInitiale = (byte) villeInitiale;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        toutesVillesVisitees = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;

        distancesPlusCourt = distancesPlusCourts();

        rechercheAuxDistanceProche(1 << this.villeInitiale, this.villeInitiale, 0.0);
        rechercheAuxDistance(1 << this.villeInitiale, this.villeInitiale, 0.0);
        rechercheAuxVillesEmpruntees(1 << this.villeInitiale, this.villeInitiale, 0.0,
                emprunteVille(new byte[nombreDeVilles + 1], 0, this.villeInitiale));
    }

    // #region Badtrack v2 + Optimisation

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue ne soit pas plus longue que la distance la plus courte
     * enregistré.
     * 
     * @param villesVisitees    Villes qui reste à visitée.
     * @param villeActuelle     Ville où se situe l'algorithme
     * @param distanceParcourue Distance parcourure depuis la première itération
     */
    private void rechercheAuxDistance(int villesVisitees, byte villeActuelle, double distanceParcourue) {

        // Je prend en compte que VilleActuelle est déjà une ville visitée

        if (distanceParcourue < distanceOptimum
                && ((distanceParcourue + parcoursIdeal(villesVisitees)) <= distanceOptimum)) {
            if (Integer.bitCount(villesVisitees) > (nombreDeVilles / 2))
                rechercheAuxDistanceProche(villesVisitees, villeActuelle, distanceParcourue);
            if (villesVisitees == toutesVillesVisitees) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);

                if (distanceParcourueFinal < distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                }
            } else {

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    byte villeChoisie = (byte) (Math.getExponent(villeFormatBinaire));

                    rechercheAuxDistance(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie));

                }

            }
        }

    }
    // #endregion BadTrack v2 + Optimisation

    // #region PlusProche v2

    /**
     * Recherche la ville la plus proche parmis les villes non visitées pour y
     * aller.
     * 
     * @param villesVisitees    Villes qui ont été visitées jusqu'à présent.
     * @param villeActuelle     Ville où se situe l'algorithme.
     * @param distanceParcourue Distance parcourue depuis la première itération
     */
    private void rechercheAuxDistanceProche(int villesVisitees, byte villeActuelle, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité
//TODO : Mettre un backTrack ?
        if (villesVisitees == toutesVillesVisitees) {
            if (distanceOptimum > distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeInitiale))
                distanceOptimum = distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);
        } else {
            double distanceMin = Long.MAX_VALUE;
            byte villePlusProche = 0;
            for (int villeFormatBinaire = villeNonVisitee(1,
                    villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                            villeFormatBinaire << 1, villesVisitees)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFormatBinaire));

                double distanceVilleChoisie = pays.getDistanceEntreVilles(villeActuelle, villeChoisie);
                if (distanceVilleChoisie < distanceMin) {
                    distanceMin = distanceVilleChoisie;
                    villePlusProche = villeChoisie;
                }

            }
            rechercheAuxDistanceProche((villesVisitees + (1 << villePlusProche)), (villePlusProche),
                    distanceParcourue + distanceMin);

        }

    }

    // #endregion PlusProche v2

    // #region Récupére parcours effectué

    /**
     * Recherche récursivement le parcours le plus court possible. En vérifiant que
     * la distance parcourue n'est pas plus longue que la distance la distance
     * optimum enregistrée via rechercheDistanceAux().
     * 
     * @param villesVisitees    Villes qui ont été visitées depuis la première
     *                          itération
     * 
     * @param villeActuelle     Ville dans laquelle se situe l'algo
     * 
     * @param distanceParcourue Distance parcourue depuis la première itération
     * 
     * @param villesEmpruntees  Stock par ordre chronologique les numéros des villes
     *                          empruntées
     */
    private void rechercheAuxVillesEmpruntees(int villesVisitees, byte villeActuelle, double distanceParcourue,
            byte[] villesEmpruntees) {

        // Je prend en compte que la VilleActuell est déjà une ville visité
        if (distanceParcourue < distanceOptimum
                && ((distanceParcourue + parcoursIdeal(villesVisitees)) <= distanceOptimum)) {

            if (villesVisitees == toutesVillesVisitees) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuelle, villeInitiale);

                if (distanceParcourueFinal == distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                    villesEmprunteesOptimum = emprunteVille(villesEmpruntees, nombreDeVilles, villeInitiale);
                }
            } else {

                for (int villeFormatBinaire = villeNonVisitee(1,
                        villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                                villeFormatBinaire << 1, villesVisitees)) {

                    byte villeChoisie = (byte) Math.getExponent(villeFormatBinaire);

                    rechercheAuxVillesEmpruntees(villesVisitees + villeFormatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuelle, villeChoisie),
                            emprunteVille(villesEmpruntees, Integer.bitCount(villesVisitees), villeChoisie));

                }

            }
        }

    }

    // #endregion Récupére parcours

    // #region Outils utile

    /**
     * /** Renvois un type int où chaque bit représente une ville, si un bit 0 elle
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
    private int villeNonVisitee(int villeActuelle, int villesVisitees) {
        villeActuelle += villesVisitees;
        return villeActuelle ^ (villeActuelle & villesVisitees);
    }

    /**
     * Stock par ordre chronologique la nouvelle ville visitée à la liste des villes
     * visitées.
     * 
     * @param villesEmpruntees  Tableau de int où chaque case représente le numéro
     *                          d'une ville visité
     * 
     * @param indexVilleVisitee index auquel le numéro de la ville visité doit être
     *                          ajouté
     * 
     * @param villeVisitee      Numéro de la ville visitée
     * 
     * @return {@code int[]} Une liste de int contenant par ordre chronologique les
     *         villes empruntées
     */
    private byte[] emprunteVille(byte[] villesEmpruntees, int indexVilleVisitee, byte villeSuivante) {
        villesEmpruntees[indexVilleVisitee] = villeSuivante;
        return villesEmpruntees.clone();
    }

    // #region parcours idéal

    private double parcoursIdeal(int villesVisitees) {
        return distancesPlusCourt[(nombreDeVilles - Integer.bitCount(villesVisitees))];
    }

    private double distancePlusCourtEntre2Villes(double minimumSuperieur, int index) {
        int villesVisitees = 0;
        double resultat = Double.MAX_VALUE;
        for (int villeFormatBinaire = villeNonVisitee(1,
                villesVisitees); villeFormatBinaire < toutesVillesVisitees; villeFormatBinaire = villeNonVisitee(
                        villeFormatBinaire << 1, villesVisitees)) {
            for (int villeFormatBinaire2 = villeNonVisitee(1,
                    villeFormatBinaire); villeFormatBinaire2 < toutesVillesVisitees; villeFormatBinaire2 = villeNonVisitee(
                            villeFormatBinaire2 << 1, villeFormatBinaire)) {

                double valeurMinimal = pays.getDistanceEntreVilles(Math.getExponent(villeFormatBinaire),
                        Math.getExponent(villeFormatBinaire2));

                if ((valeurMinimal < resultat) && (minimumSuperieur <= valeurMinimal)
                        && (pairDeVillesNonVisite(villeFormatBinaire | villeFormatBinaire2))) {
                    resultat = valeurMinimal;
                    pairDeVilleVisite[index] = villeFormatBinaire + villeFormatBinaire2;

                }
            }

        }

        return resultat;
    }

    private int[] pairDeVilleVisite;

    private double[] distancesPlusCourts() {
        pairDeVilleVisite = new int[nombreDeVilles + 1];
        int i = 0;
        double[] resultat = new double[nombreDeVilles];
        for (double minValue = distancePlusCourtEntre2Villes(0,
                0); i < resultat.length; minValue = distancePlusCourtEntre2Villes(minValue, i)) {
            resultat[i++] = minValue + resultat[(i == 1) ? 0 : i - 2];
        }

        return resultat;
    }

    private boolean pairDeVillesNonVisite(int pairDeVilleActuel) {
        boolean resultat = true;
        for (int pairVille : pairDeVilleVisite) {
            resultat = ((pairDeVilleActuel ^ pairVille) != 0) && resultat;
        }
        return resultat;
    }

    // #endregion parcours idéal

    // #endregion Outils Utile

    // #region Getters

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    @Override
    public Parcours getParcours() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche
        String villesEmpruntees = String.valueOf(villesEmprunteesOptimum[0]);
        for (int i = 1; i < villesEmprunteesOptimum.length; i++) {
            villesEmpruntees += '>' + String.valueOf(villesEmprunteesOptimum[i]);
        }

        return new Parcours(distanceOptimum, villesEmpruntees);
    }

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    @Override
    public String getNom() {
        return "TrackProches v1.2";
    }

    // #endregion Getters

}