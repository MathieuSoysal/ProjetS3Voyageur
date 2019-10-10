package projetS3Voyageur.ModesDeRecherches;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.management.*;


public class Bazar {


    public static void main(String[] args) {


    }

    /**
     * Get CPU time in nanoseconds.
     */
    public long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    /**
     * Get user time in nanoseconds.
     */
    public long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadUserTime() : 0L;
    }

    /**
     * Get system time in nanoseconds.
     */
    /*public long getSystemTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                (bean.getCurrentCpuTime() - bean.getCurrentThreadUserTime()) : 0L;
    }*/
}


