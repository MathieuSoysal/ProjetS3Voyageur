package projetS3Voyageur;

class App {

    public static void main(String[] args) {

        Comparer compare = new Comparer(AlgoLuncher.MathieuS, AlgoLuncher.MathieuS);
        compare.setNombreDeTest(100);
        compare.setNombreDeVilles(10);
        compare.afficher();
    }

}