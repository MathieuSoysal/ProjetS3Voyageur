package projetS3Voyageur;

import java.util.Deque;
import java.util.LinkedList;

import projetS3Voyageur.ModesDeRecherches.ModeRecherche;

public class Voyageur {
    private Pays pays;
    private int villeDepart;

    public Voyageur(Pays pays, int numVilleDepart) {
        this.pays = pays;
        setVilleDepart(numVilleDepart);
    }


    public void setVilleDepart(int numVille) {
        villeDepart = numVille;
    }

    public String getParcours(ModeRecherche modeRecherche){
        modeRecherche.recherche(pays, villeDepart);
        return modeRecherche.getParcour().toString();
    }


}