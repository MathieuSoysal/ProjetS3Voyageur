package projetS3Voyageur;

public class BruteForce {

    private Pays pays;
    private int nbVille;
    private Ville villeDepart;


    //Constructeurs

    public BruteForce(Pays pays, Ville villeDepart){
        this.pays = pays;
        this.villeDepart = villeDepart;
        this.nbVille = pays.getNbVille();
    }


    //Méthodes & Fonctions

    /**
     *
     * @param v
     * @param villesAVisitees: boolean[] les cases contiennent {@code true} ssi la Ville dont l'id correspond
     *                       à l'index de la case a été visitée.
     * @return Le parcours le plus court partant de {@code v} passant par toute les villes n'ayant pas encore
     * été visitée et retournant à {@code this.villeDepart}.
     */
    public Parcours rechercheAux(Ville v, boolean[] villesAVisitees) {
        Parcours p = new Parcours();
        p.ajouterVille(v);
        if(Util.isAllTrue(villesAVisitees)){
            p.ajouterVille(this.villeDepart);
            return p;
        }else{
            double distanceOptimum = Double.MAX_VALUE;
            Parcours parcoursOptimum = new Parcours();
            for(int i = 0; i < villesAVisitees.length; i++){
                if(!villesAVisitees[i]){
                    double dist = this.pays.getOneVille(i)
                }
            }
        }
    }

}
