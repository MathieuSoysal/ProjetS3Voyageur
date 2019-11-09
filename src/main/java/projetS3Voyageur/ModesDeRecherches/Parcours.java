package projetS3Voyageur.ModesDeRecherches;

public class Parcours {
    private double distanceParcourue;
    private String villesEmprunté;

    Parcours(double distanceParcourue, String villesEmprunté) {
        this.distanceParcourue = distanceParcourue;
        this.villesEmprunté = villesEmprunté;
    }

    double getDistance() {
        return distanceParcourue;
    }

    String getVillesEmprunté() {
        return villesEmprunté;
    }

    @Override
    public String toString() {
        return "Le parcours le plus court à une distance parcourue de : " + distanceParcourue
                + "\n  villes empruntées :" + villesEmprunté;
    }

}