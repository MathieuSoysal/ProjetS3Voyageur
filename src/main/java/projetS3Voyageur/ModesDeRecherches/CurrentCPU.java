package projetS3Voyageur.ModesDeRecherches;


import java.lang.management.*;

public class CurrentCPU implements Runnable {

    public static void main(String[] args)
            throws Exception {

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
            while (System.nanoTime() - temps < 700000000);
            this.cpu = thread.getCurrentThreadCpuTime() - cpu;
        }
        catch (InterruptedException e) {}

        finally {
            notify();
        }
    }
}


