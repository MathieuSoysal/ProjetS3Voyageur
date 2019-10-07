package projetS3Voyageur;

import java.util.Random;

public class Util {

    /**
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
     * @param t: boolean[]
     * @return {@code true} ssi toutes les cases de {@code t} sont {@code true}.
     */
    public static boolean isAllTrue(boolean[] t){
        int i = 0;
        while(i < t.length && t[i]){
            i++;
        }
        return i >= t.length;
    }

    /**
     * @param t: boolean[]
     * @return une copie de {@code t}.
     */
    public static boolean[] clone(boolean[] t){
        boolean[] res = new boolean[t.length];
        for(int i = 0; i < res.length; i++){
            res[i] = t[i];
        }
        return res;
    }

    public static long factorielle(long n){
        if(n <= 1){
            return 1;
        }else{
            return n * factorielle(n-1);
        }
    }

}