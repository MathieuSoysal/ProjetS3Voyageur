package projetS3Voyageur.ModesDeRecherches.acm;

import projetS3Voyageur.ModesDeRecherches.Parcours;
import projetS3Voyageur.CompositionPays.Pays;

class Kruskal {

    public static void main(String[] args) {
        int taille = 4;
        int villesVisitees = 1 << 1;
        int overFlow = (1 << (taille)) - 1;
        for (int i = villeNonVisitee(1, villesVisitees); i < (overFlow >> 1); i = villeNonVisitee(i << 1,
                villesVisitees)) {
            villesVisitees |= i;
            for (int j = villeNonVisitee(1, villesVisitees); j < overFlow; j = villeNonVisitee(j << 1,
                    villesVisitees)) {
                System.out.println(String.format("[%s,%s]", Math.getExponent(i), Math.getExponent(j)));
            }
        }
    }

    private static final boolean nonVisitee = false;
    private Pays pays;

    private int villeInitale;
    private int nombreDeVilles;

    private Parcours parcoursOptimum;

    private void initChaineTrie() {
        int taille = 4;
        int villesVisitees = 1 << 1;
        int overFlow = (1 << (taille)) - 1;
        for (int i = villeNonVisitee(1, villesVisitees); i < (overFlow >> 1); i = villeNonVisitee(i << 1,
                villesVisitees)) {
            villesVisitees |= i;
            for (int j = villeNonVisitee(1, villesVisitees); j < overFlow; j = villeNonVisitee(j << 1,
                    villesVisitees)) {
                System.out.println(String.format("[%s,%s]", Math.getExponent(i), Math.getExponent(j)));
            }
        }
    }

    /**
     * Renvois un type int où chaque bit représente une ville, si un bit 0 elle
     * n'est pas visitée, si un bit vaut 1 elle a été visitée. La méthode récupère
     * les villes visitées et la ville actuelle si la ville actuelle fait partie des
     * villes déjà visitée elle fait passer la ville actuelle à une ville non
     * visitée.
     * 
     * @param villeActuelle  Chaque bit du int représente une ville seul l'un des
     *                       bits est à 1, elle représente la ville actuelle
     * 
     * @param villesVisitees Chaque bit à 1 du int représente les villes visitées.
     * 
     * @return {@code int} Renvois un int avec un seul bit à 1, son emplacement
     *         (dans la séquence de bits du int) représente une ville non visitée
     *         qui est la nouvelle ville actuelle.
     */
    private static/*TODO: mit en static temporairement */ int villeNonVisitee(int villeActuelle, final int villesVisitees) {
        villeActuelle += villesVisitees;
        return villeActuelle ^ (villeActuelle & villesVisitees);
    }
    // TODO: Pensais à crée une class pour l'utilisation binaire

}