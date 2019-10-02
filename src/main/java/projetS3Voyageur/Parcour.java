package projetS3Voyageur;

public class Parcour{
    private double distanceParcour;
    private String villesEmprunté;
    
    public Parcour(double distanceParcour,String villesEmprunté){
        this.distanceParcour = distanceParcour;
        this.villesEmprunté = villesEmprunté;
    }

    public double getDistance(){
        return distanceParcour;
    }

    public String getVillesEmprunté(){
        return villesEmprunté;
    }

}