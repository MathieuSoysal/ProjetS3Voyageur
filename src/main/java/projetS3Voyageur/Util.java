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

    public static boolean isAllTrue(boolean[] t){
        int i = 0;
        while(i < t.length && t[i]){
            i++;
        }
        return i >= t.length;
    }

}