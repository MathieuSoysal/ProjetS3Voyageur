package projetS3Voyageur.ModesDeRecherches;

import java.util.HashMap;

import projetS3Voyageur.*;

public class BrutForceV3 implements ModeRecherche {

    private int overFlow;

    private Pays pays;

    private int villeInitial;
    private int nombreDeVilles;

    private double distanceOptimum;
    private int[] villesEmprunteesOptimum;

    private static HashMap<Integer, Integer> ConversionFormatBinaireEnNumVille = new HashMap<>();

    public void recherche(Pays pays, int villeInitial) {
        this.pays = pays;
        this.villeInitial = villeInitial;
        nombreDeVilles = pays.getNombreDeVilles();
        overFlow = 1 << nombreDeVilles;
        distanceOptimum = Double.MAX_VALUE;

        for (Integer i = 0; i != nombreDeVilles; i++) {
            ConversionFormatBinaireEnNumVille.put(1 << i, i);
        }

        rechercheAux(1 << villeInitial, villeInitial, 0.0, emprunteVille(new int[nombreDeVilles + 1], 0, villeInitial));
    }

    private void rechercheAux(int villesVisite, int villeActuel, double distanceParcourue, int[] villesEmprunté) {

        // Je prend en compte que la VilleActuell est déjà une ville visité

        if ((villesVisite + 1) == overFlow) {
            double distanceParcourueFinal = distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeInitial);

            if (distanceParcourueFinal < distanceOptimum) {
                distanceOptimum = distanceParcourueFinal;
                villesEmprunteesOptimum = emprunteVille(villesEmprunté, nombreDeVilles, villeInitial);
            }
        } else {

            for (int villeFomatBinaire = villeNonVisite(1,
                    villesVisite); villeFomatBinaire < overFlow; villeFomatBinaire = villeNonVisite(villeFomatBinaire << 1,
                            villesVisite)) {

                int villeChoisie = (ConversionFormatBinaireEnNumVille.get(villeFomatBinaire));

                rechercheAux(villesVisite + villeFomatBinaire, (villeChoisie),
                        distanceParcourue + pays.getDistanceEntreVilles(villeActuel, villeChoisie),
                        emprunteVille(villesEmprunté, Integer.bitCount(villesVisite), villeChoisie));

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
        return villeActuel - (villeActuel & villesVisite);
    }

    private int[] emprunteVille(int[] villesEmpruntees, int index, int villeSuivante) {
        villesEmpruntees[index] = villeSuivante;
        return villesEmpruntees.clone();
    }

    @Override
    public String getNom() {
        return "BrutForce v3";
    }

}