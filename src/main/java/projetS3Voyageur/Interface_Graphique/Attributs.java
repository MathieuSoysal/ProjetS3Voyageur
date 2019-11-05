package projetS3Voyageur.Interface_Graphique;

public class Attributs {


    private int nbVille;
    private double temps;
    private String nom; // nom du programme



    public Attributs(int nbVille, double temps, String nom) {

        this.nbVille = nbVille;
        this.temps = temps;
        this.nom = nom;
    }

    public int getNbVille() {

        return nbVille;
    }

    public void setNbVille(int nbVille) {

        this.nbVille = nbVille;
    }

    public double getTemps() {

        return temps;
    }

    public void setTemps(double temps) {

        this.temps = temps;
    }

    public String getNom() {

        return nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }
}
