package projetS3Voyageur;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.io.IOException;

public class App {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    public void init(){
        //Do nothing
    }


    public static void main(String[] args) {

        try{
            org.openjdk.jmh.Main.main(args);
        }catch(Exception e){
            e.printStackTrace();
        }


        /*Pays pays = new Pays(10);

        Voyageur v = new Voyageur(pays, pays.getOneVille(0));

        System.out.println("Nombre de possibilitées : " + Util.factorielle(pays.getNbVille()-1));
        System.out.println("Execution en cours (Cela peut prendre plusieurs minutes [voir heures])...");

        Parcours p = v.recherche();

        System.out.println(p);*/

    }
}
