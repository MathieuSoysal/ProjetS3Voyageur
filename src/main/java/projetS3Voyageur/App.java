package projetS3Voyageur;

public class App {

    public static void main(String[] args) {

        Grille g = new Grille(15, 10);
        System.out.println(g);

        Resolveur r = new Resolveur(g);
        r.brutForce(g.getVilles()[0]);

    }
}
