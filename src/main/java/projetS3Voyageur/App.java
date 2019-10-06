package projetS3Voyageur;

import projetS3Voyageur.RepertoireAlgo.MathieuSV1.AlgoMathieuS;
import projetS3Voyageur.RepertoireAlgo.MatthiasDV1.AlgoMatthiasD;

class App {

    public static void main(String[] args) {
        Tester test = new Tester(new AlgoMatthiasD(), new AlgoMathieuS());
        test.afficher();
    }

}