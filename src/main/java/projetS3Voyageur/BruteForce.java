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


    //Getters & Setters

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }


    //Méthodes & Fonctions

    /**
     *
     * @param v
     * @param villesAVisiter: boolean[] les cases contiennent {@code true} ssi la Ville dont l'id correspond
     *                       à l'index de la case a été visitée.
     * @return Le parcours le plus court partant de {@code v} passant par toute les villes n'ayant pas encore
     * été visitée et retournant à {@code this.villeDepart}.
     */
    private Parcours rechercheAux(Ville v, boolean[] villesAVisiter) {
        Parcours p = new Parcours();
        p.ajouterVille(v);
        if(Util.isAllTrue(villesAVisiter)){
            p.ajouterVille(this.villeDepart);
            return p;
        }else{
            double distanceOptimum = Double.MAX_VALUE;
            Parcours parcoursOptimum = new Parcours();
            for(int i = 0; i < villesAVisiter.length; i++){
                if(!villesAVisiter[i]){
                    boolean[] villesAVisiterBis = Util.clone(villesAVisiter);
                    villesAVisiterBis[i] = true;
                    Parcours pTemp = rechercheAux(this.pays.getOneVille(i), villesAVisiterBis);
                    double distance = v.distance(this.pays.getOneVille(i)) + pTemp.getDistance();
                    if(distance < distanceOptimum){
                        distanceOptimum = distance;
                        parcoursOptimum = pTemp;
                    }
                }
            }
            p.ajouterParcours(parcoursOptimum);
            return p;
        }
    }

    public Parcours recherche() {
        boolean[] villesAVisiter = new boolean[this.nbVille];
        for(int i = 0; i < villesAVisiter.length; i++){
            villesAVisiter[i] = false;
        }
        villesAVisiter[this.villeDepart.getId()] = true;
        return rechercheAux(this.villeDepart, villesAVisiter);
    }

}
