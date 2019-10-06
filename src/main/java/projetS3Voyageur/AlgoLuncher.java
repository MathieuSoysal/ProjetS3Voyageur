package projetS3Voyageur;

import projetS3Voyageur.RepertoireAlgo.MathieuSV1.*;
import projetS3Voyageur.RepertoireAlgo.MatthiasDV1.*;

public enum AlgoLuncher {

    MathieuS {
        private int nombreDeVilles = 10;


        @Override
        public void execute() {
            Pays p = new Pays(nombreDeVilles);
            BrutForce b = new BrutForce(p);
            b.recherche();
        }

        @Override
        public void setNombreDeVilles(int nombreDeVilles) {
            this.nombreDeVilles = nombreDeVilles;
        }

        @Override
        public String getNom() {
            return "Branche Mathieu-Soysal";
        }

    },
    MatthiasD {
        private int nbSommet = 10;


        @Override
        public void execute() {
            Graphe g = new Graphe(50, nbSommet);
            Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());
            r.bruteForce();
        }

        @Override
        public void setNombreDeVilles(int nombreDeVilles) {
            this.nbSommet = nombreDeVilles;
        }

        @Override
        public String getNom() {
            return "Branche Matthias-Delon";
        }
    };

    public abstract void execute();
    public abstract String getNom();
    public abstract void setNombreDeVilles(int nombreDeVilles);
}