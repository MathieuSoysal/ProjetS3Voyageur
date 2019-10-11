package projetS3Voyageur.StatsAlgos;

import java.lang.management.*;

import projetS3Voyageur.Pays;
import projetS3Voyageur.CompositionPays.Position;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProche;

public class CurrentCPU implements Runnable {

    public static void main(String[] args) throws Exception {

        long temps = System.nanoTime();
        CurrentCPU test = new CurrentCPU();
        synchronized (test) {
            new Thread(test).start();
            while (test.cpu == -1) {
                test.wait();
            }
        }
        System.out.println("Temps : " + (System.nanoTime() - temps) + " ms");
        System.out.println("CPU : " + test.cpu + " ms");
    }

    private long cpu = -1;

    public synchronized void run() {

        try {

            ThreadMXBean thread = ManagementFactory.getThreadMXBean();
            long cpu = thread.getCurrentThreadCpuTime();
            Thread.sleep(300);
            long temps = System.nanoTime();
            // ModeRecherche algo = new PlusProche();
            // Pays pays = new Pays(6);
            // for (int i = 0; i < 6; i++)
            // pays.setPositionVille(i, new Position(0, i));

            // algo.recherche(pays, 0);
            for (int i = 0; i < 30; i++)
                System.out.println("hi"); //TODO: Attention le temps qu'il renvoie n'est pas juste varie de 0 à 13750000 (voir plus)
            this.cpu = thread.getCurrentThreadCpuTime() - cpu;
        } catch (InterruptedException e) {
        }

        finally {
            notify();
        }
    }
}