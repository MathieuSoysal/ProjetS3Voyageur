package projetS3Voyageur.RepertoireAlgo.MathieuSV1;

import projetS3Voyageur.ExecuteAlgos;

public class AlgoMathieuS implements ExecuteAlgos {

    private int nombreDeVilles = 10;

    @Override
    public void execute() {
        Pays p = new Pays(nombreDeVilles);
        BrutForce b = new BrutForce(p);
        b.recherche();

    }

    @Override
    public String getNomAlgo() {
        return "Branche Mathieu-Soysal";
    }

    @Override
    public void setNombreDeVilles(int nombreDeVilles){
        this.nombreDeVilles = nombreDeVilles;
    }

}
