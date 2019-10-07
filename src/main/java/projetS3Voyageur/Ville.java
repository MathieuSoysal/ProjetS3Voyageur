package projetS3Voyageur;

public class Ville {

    private static int instanceCount = 0;
    private int id;
    private int x;
    private int y;


    //Constructeurs

    public Ville(){
        this.id = instanceCount;
        instanceCount++;
        this.x = Util.randomMinMax(0, 100);
        this.y = Util.randomMinMax(0, 100);
    }


    //Getters & Setters

    public int getId() {
        return id;
    }

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }


    //Méthodes & Fonctions

    /**
     * @param v: Ville
     * @return la distance entre {@code this} et {@code v} calculée à l'aide du théorème de Pythagore
     */
    public double distance(Ville v){
        return Math.sqrt(Math.pow(this.x - v.x, 2)+Math.pow(this.y - v.y, 2));
    }

    /*public String showAttributs(){
        return "Ville{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }*/

    @Override
    public String toString() {
        return this.id + "";
    }
}
