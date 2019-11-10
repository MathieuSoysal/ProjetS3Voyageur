package projetS3Voyageur.ModesDeRecherches;

import projetS3Voyageur.CompositionPays.Pays;

public interface ModeRecherche {

    /**
     * Recherche depuis une ville de départ le parcours le plus optimisé pour
     * visiter toutes les villes d'un pays.
     * 
     * @param pays          Le pays concerné par la recherche
     * @param villeInitiale Le numéro de la ville de départ
     */
    public void recherche(Pays pays, int villeInitiale);

    /**
     * @près-requis : Cette méthode doit être exécuté après la méthode recherche().
     * 
     * @return {@code Parcours} parcours le plus optimisé qu'il ai trouvé
     */
    public Parcours getParcours();

    /**
     * Renvois le nom de l'algorithme de recherche
     * 
     * @return {@code String} Nom de l'algorithme
     */
    public String getNom();
}