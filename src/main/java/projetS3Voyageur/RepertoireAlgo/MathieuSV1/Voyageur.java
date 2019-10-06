package projetS3Voyageur.RepertoireAlgo.MathieuSV1;

import java.util.Deque;
import java.util.LinkedList;

public class Voyageur {
    private Pays pays;
    private Deque<Integer> villesAVisiter = new LinkedList<>();

    public Voyageur(Pays pays, int numVilleDepart) {
        this.pays = pays;
        initVillesAVisiter();
        setVilleDepart(numVilleDepart);
    }

    private void initVillesAVisiter() {
        for (Integer i = 0; i < pays.getNombreDeVilles(); i++) {
            villesAVisiter.add(i);
        }
    }

    public void setVilleDepart(int numVille) {
        while (villesAVisiter.peek() != numVille) {
            villesAVisiter.offer(villesAVisiter.poll());
        }
    }

    public String recherche(Recherche modeDeRecherche){
        switch (modeDeRecherche){
            case BrutForce:
            //TODO: Penser à faire l'interface recherche
            BrutForce brtforce = new BrutForce(pays);
            brtforce.recherche();
            return brtforce.getParcour().getVillesEmprunté();
            default:
            return null;
        }
    }


}