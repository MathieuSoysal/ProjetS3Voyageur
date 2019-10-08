package projetS3Voyageur;

public class Parcour{
    private double distanceParcourue;
    private String villesEmprunté;
    
    public Parcour(double distanceParcourue,String villesEmprunté){
        this.distanceParcourue = distanceParcourue;
        this.villesEmprunté = villesEmprunté;
    }

    public double getDistance(){
        return distanceParcourue;
    }

    public String getVillesEmprunté(){
        return villesEmprunté;
    }

    @Override
    public String toString() {
        return "Le parcours le plus court à une distance parcourue de : " + distanceParcourue + "\n  villes empruntées :" + villesEmprunté ;
    }

}