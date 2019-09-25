package projetS3Voyageur;

public class App {

    public static void main(String[] args) {

        Grille g = new Grille(5, 3);
        System.out.println(g);

        Resolveur r = new Resolveur(g);
        r.brutForce(g.getVilles()[Util.randomMinMax(0, g.getNbSommetPlaces()-1)]);

    }
}
