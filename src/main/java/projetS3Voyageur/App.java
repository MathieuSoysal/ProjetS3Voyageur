package projetS3Voyageur;

import java.util.Timer;

public class App {

    public static void main(String[] args) {

        Timer timer = new Timer(true);
        InterruptTimerTask interruptTimerTask = new InterruptTimerTask(Thread.currentThread());
        timer.schedule(interruptTimerTask, 10000);

        try {
            Graphe g = new Graphe(100, 5);
            System.err.println(g);

            Resolveur r = new Resolveur(g, g.getOneSommet(0).getVille());

            Trajet t = r.bruteForce();

            System.out.println("Trajet: " + t);
            System.out.println("Distance: " + t.distance());
        }catch (InterruptedException e) {
            System.out.println("timeout exeeded");
        }finally {
            timer.cancel();
        }

    }
}
