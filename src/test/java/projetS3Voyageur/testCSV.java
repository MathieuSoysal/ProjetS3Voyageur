package projetS3Voyageur;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import projetS3Voyageur.StatsAlgos.CSV;


public class testCSV {


    @Test
    public void test_Write_and_Read_avec_un_element(){

        List<String[]> liste = new ArrayList<String[]>();
        String[] villes = {"Paris"};

       liste.add(villes);

        CSV.writeCSV(liste, ";", "test.csv");

        assertEquals(liste.get(0)[0] , CSV.readCSV(";","test.csv").get(0)[0]);

    }

    @Test
    public void test_Write_and_Read_avec_plusieurs_éléments(){
        List<String[]> liste = new ArrayList<String[]>();
        String[] villes = {"Paris","Marseille","Lyon"};

       liste.add(villes);

        CSV.writeCSV(liste, ";", "test.csv");

        assertEquals(liste.get(0)[0] , CSV.readCSV(";","test.csv").get(0)[0]);
        assertEquals(liste.get(0)[1] , CSV.readCSV(";","test.csv").get(0)[1]);
        assertEquals(liste.get(0)[2] , CSV.readCSV(";","test.csv").get(0)[2]);


    }
}