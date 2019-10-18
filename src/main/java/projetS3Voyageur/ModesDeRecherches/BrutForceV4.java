package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.*;

public class BrutForceV4 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private byte villeInitial;
    private byte nombreDeVilles;

    private double distanceOptimum;
    private byte[] villesEmprunteesOptimum;

    public void recherche(Pays pays, int villeInitialP) {
        this.pays = pays;
        this.villeInitial = (byte) villeInitialP;
        nombreDeVilles = (byte) pays.getNombreDeVilles();
        overFlow = (1 << nombreDeVilles)-1;
        distanceOptimum = Double.MAX_VALUE;

        rechercheAuxDistance(1 << villeInitial, villeInitial, 0.0);
        rechercheAuxVillesEmpruntees(1 << villeInitial, villeInitial, 0.0,
                emprunteVille(new byte[nombreDeVilles + 1], 0, villeInitial));
    }

    // TODO: à voir si on peut appliqué DRY
    private void rechercheAuxVillesEmpruntees(int villesVisite, byte villeActuel, double distanceParcourue,
            byte[] villesEmprunté) {

        // Je prend en compte que la VilleActuell est déjà une ville visité
        if (distanceParcourue < distanceOptimum) {

            if (villesVisite == overFlow) {
                double distanceParcourueFinal = distanceParcourue
                        + pays.getDistanceEntreVilles(villeActuel, villeInitial);

                if (distanceParcourueFinal == distanceOptimum) {
                    distanceOptimum = distanceParcourueFinal;
                    villesEmprunteesOptimum = emprunteVille(villesEmprunté, nombreDeVilles, villeInitial);
                }
            } else {

                for (int villeFomatBinaire = villeNonVisite(1,
                        villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                                villeFomatBinaire << 1, villesVisite)) {

                    byte villeChoisie = (byte) Math.getExponent(villeFomatBinaire);

                    rechercheAuxVillesEmpruntees(villesVisite + villeFomatBinaire, (villeChoisie),
                            distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie),
                            emprunteVille(villesEmprunté, Integer.bitCount(villesVisite), villeChoisie));

                }

            }
        }

    }

    //TODO: à voir si on peut appliquer DRY
    private void rechercheAuxDistance(int villesVisite, byte villeActuel, double distanceParcourue) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if (villesVisite == overFlow) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial);

            if (distanceParcourueFinal < distanceOptimum) {
                distanceOptimum = distanceParcourueFinal;
            }
        } else {

            for (int villeFomatBinaire = villeNonVisite(1,
                    villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(
                            villeFomatBinaire << 1, villesVisite)) {

                byte villeChoisie = (byte) (Math.getExponent(villeFomatBinaire));

                rechercheAuxDistance(villesVisite + villeFomatBinaire, (villeChoisie),
                        distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie));

            }

        }

    }

    public Parcours getParcour() {
        // TODO: Ajouter l'exception avec un getParcours sans avoir fait de recherche
        String villesEmpruntees = String.valueOf(villesEmprunteesOptimum[0]);
        for (int i = 1; i < villesEmprunteesOptimum.length; i++) {
            villesEmpruntees += '>' + String.valueOf(villesEmprunteesOptimum[i]);
        }

        return new Parcours(distanceOptimum, villesEmpruntees);
    }

    private int villeNonVisite(int villeActuel, int villesVisite) {
        villeActuel += villesVisite;
        return villeActuel ^ (villeActuel & villesVisite);
    }

    private byte[] emprunteVille(byte[] villesEmpruntees, int index, byte villeSuivante) {
        villesEmpruntees[index] = villeSuivante;
        return villesEmpruntees.clone();
    }

    @Override
    public String getNom() {
        return "BrutForce v4";
    }

}