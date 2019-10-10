package projetS3Voyageur.CompositionPays;

public class Position {
    private int y;
    private int x;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la position y
     * 
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne la position x
     * 
     * @return int x
     */
    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }

    
}