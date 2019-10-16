package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class PlusProcheV2 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private byte villeInitial;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private String villesEmprunteesOptimum;

    public void recherche(Pays pays, int villeInitialP) {
        this.pays = pays;
        this.villeInitial = (byte) villeInitialP;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        overFlow = (1 << nombreDeVilles) - 1;
        distanceOptimum = Double.MAX_VALUE;
        villesEmprunteesOptimum = String.valueOf(villeInitial);

        rechercheAuxDistance(1 << villeInitial, villeInitial, 0.0);

    }

    // TODO: à voir si on peut appliquer DRY
    private void rechercheAuxDistance(int villesVisite, byte villeActuel, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (villesVisite == overFlow) {
            villesEmprunteesOptimum += ">" + villeInitial;
            distanceOptimum = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial);
        } else {
            double distanceMin = Long.MAX_VALUE;
            byte villePlusProche = 0;
            for (int villeFomatBinaire = villeNonVisite(1,
                    villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                            villeFomatBinaire << 1, villesVisite)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFomatBinaire));

                double distanceVilleChoisie = pays.getDistanceEntreVilles(villeActuel, villeChoisie);
                if (distanceVilleChoisie < distanceMin) {
                    distanceMin = distanceVilleChoisie;
                    villePlusProche = villeChoisie;
                }

            }
            villesEmprunteesOptimum += ">" + villePlusProche;
            rechercheAuxDistance((villesVisite + (1 << villePlusProche)), (villePlusProche),
                    distanceParcourue + distanceMin);

        }

    }

    public Parcours getParcour() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche

        return new Parcours(distanceOptimum, villesEmprunteesOptimum);
    }

    private int villeNonVisite(int villeActuel, int villesVisite) {
        villeActuel += villesVisite;
        return villeActuel ^ (villeActuel & villesVisite);
    }

    @Override
    public String getNom() {
        return "PlusProche v2";
    }

}