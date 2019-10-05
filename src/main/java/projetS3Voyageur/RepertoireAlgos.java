package projetS3Voyageur;

import projetS3Voyageur.MathieuS.*;
import projetS3Voyageur.MatthiasD.*;

public class RepertoireAlgos {

    public void mathieuS() {
        Pays p = new Pays(10);
        BrutForce b = new BrutForce(p);
        b.recherche();
    }

    public void matthiasD() {
        Graphe g = new Graphe(50, 10);
        Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());
        r.bruteForce();
    }
}