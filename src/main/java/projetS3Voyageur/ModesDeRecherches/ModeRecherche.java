package projetS3Voyageur.ModesDeRecherches;
import projetS3Voyageur.*;

public interface ModeRecherche{

    public void recherche(Pays pays,int villeDepart);
    public Parcours getParcour();
    public String getNom();
}