package projetS3Voyageur.RepertoireAlgo.MatthiasDV1;

import projetS3Voyageur.*;


public class AlgoMatthiasD implements ExecuteAlgos {

    private int nbSommet = 10;

    @Override
    public void execute() {
        Graphe g = new Graphe(50, nbSommet);
        Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());
        r.bruteForce();
    }

    @Override
    public String getNomAlgo() {
        return "Branche Matthias-Delon";
    }

    @Override
    public void setNombreDeVilles(int nombreDeVilles) {
        this.nbSommet = nombreDeVilles;
    }

}