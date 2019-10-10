package projetS3Voyageur.ModesDeRecherches;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReadCSV {
    //https://thierry-leriche-dessirier.developpez.com/tutoriels/java/csv-avec-java/


    public void read() {
        Path orderPath = Paths.get("order.csv");
        List<String> lines = null; //null mean no value by default
        try {
            lines = Files.readAllLines(orderPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier des commandes");
        }
        if (lines.size() < 2) {
            System.out.println("Il n'y a pas de commande dans le fichier");
            return;
        }
        String[] menus = {"Menu Poulet", "Menu Boeuf", "Menu Végétarien"};
        String[] side = {" avec des légumes frais", " avec des frites", " avec du riz"};
        String[] sideVegetarian = {" avec du riz", " sans riz"};
        String[] drink = {" et avec de l'eau plate", " et avec de l'eau gazeuse", " et avec du soda"};

        for (int i = 1; i < lines.size(); i++) {
            String[] split = lines.get(i).split(",");
            int nbMenu = Integer.valueOf(split[0]);
            int nbSide = Integer.valueOf(split[1]);
            int nbDrink = Integer.valueOf(split[2]);
            String orderLine = menus[nbMenu - 1];
            if (nbMenu == 3) //vegetarian menu
                orderLine += sideVegetarian[nbSide - 1];
            else
                orderLine += side[nbSide - 1];
            if (nbDrink == -1)
                orderLine += " et sans boisson";
            else
                orderLine += drink[nbDrink - 1];
            System.out.println(orderLine);
        }
    }

    public static void main(String[] args) {
        ReadCSV orderReader = new ReadCSV();
        orderReader.read();
    }
}

