package projetS3Voyageur;

import java.util.Random;

public class Util {

    /**
     *
     * @param min: int
     * @param max: int
     * @return un int entre min et max
     */
    public static int randomMinMax(int min, int max){
        if(max <= min){
            throw new IllegalArgumentException("Max doit être supérieur à Min");
        }else{
            Random r = new Random();
            int nbRand =  r.nextInt(max + 1 - min) + min;
            assert (min <= nbRand && nbRand <= max) : "tirage aleatoire hors des bornes";
            return nbRand;
        }
    }

    /**
     *
     * @param n: int
     * @return n! (n factorielle)
     */
    public static int factorielle(int n){
        if(n <= 1){
            return 1;
        }else{
            return n * factorielle(n-1);
        }
    }

}