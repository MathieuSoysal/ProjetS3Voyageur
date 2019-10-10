package projetS3Voyageur;

public class App {

    public static void main(String[] args) {

        Pays pays = new Pays(8);

        Voyageur v = new Voyageur(pays, pays.getOneVille(0));

        System.out.println("Execution en cours (Cela peut prendre plusieurs minutes [voir heures])...");
        System.out.println("Nombre de possibilitées : " + Util.factorielle(pays.getNbVille()-1));

        Parcours p = v.recherche();

        System.out.println(p);

    }
}
