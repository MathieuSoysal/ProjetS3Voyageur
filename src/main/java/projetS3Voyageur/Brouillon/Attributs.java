package projetS3Voyageur.Brouillon;
/*https://thierry-leriche-dessirier.developpez.com/tutoriels/java/afficher-tableau-avec-tablemodel-5-min/*/
public class Attributs {
    /* attribut du tableau*/

    private int nbVilles;
    private String nom_du_Programme;
    private double temps;

    public Attributs(int nbVilles, String nom_du_Programme, double temps) {
        this.nbVilles = nbVilles;
        this.nom_du_Programme = nom_du_Programme;
        this.temps = temps;
    }

    public int getNbVilles() {
        return nbVilles;
    }

    public void setNbVilles(int nbVilles) {
        this.nbVilles = nbVilles;
    }

    public String getNom_du_Programme() {
        return nom_du_Programme;
    }

    public void setNom_du_Programme(String nom_du_Programme) {
        this.nom_du_Programme = nom_du_Programme;
    }

    public double getTemps() {
        return temps;
    }

    public void setTemps(double temps) {
        this.temps = temps;
    }
}
