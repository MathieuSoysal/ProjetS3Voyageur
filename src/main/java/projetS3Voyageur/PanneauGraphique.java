package projetS3Voyageur;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * panneau de 300x200 pixels
 * 
 * @author jonathan
 */
public class PanneauGraphique extends JPanel implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 4883455012046646573L;

    // TODO: temporairement mit en public pour les tests
    public List<Point> points;

    private int[] positionsX;
    private int[] positionsY;

    public static void main(String[] args) {

        JFrame frame = new JFrame(); // creation de la fenetre
        JPanel panel = new PanneauGraphique(); // creation du panneau

        frame.setContentPane(panel); // integration du panneau dans la fenetre
        frame.pack(); // ajustement de la taille de la fenetre au contenu
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // centrage
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quand on clique sur la croix, ca quitte le programme
                                                              // (ce n'est pas le cas par defaut)
        frame.setVisible(true); // rend la fenetre visible (ce n'est pas le cas par defaut)
        frame.setTitle("Voyageur de Commerce");

    }

    /**
     * constructeur du panneau
     */
    public PanneauGraphique() {

        points = new LinkedList<>();

        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(400, 400));
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        getPositionsAbs(points);
        graphics.setColor(Color.gray);
        graphics.drawPolyline(positionsX, positionsY, points.size() + 1);

        int r = 7;

        for (Point point : points) {
            if (point.equals(points.get(0)))
                graphics.setColor(Color.LIGHT_GRAY);
            else
                graphics.setColor(Color.orange);

            graphics.fillOval((int) point.getX() - (r / 2), (int) point.getY() - (r / 2), r, r);

        }

        // getPositionsAbs(pointsOpti);
        // graphics.setColor(Color.cyan);
        // graphics.drawPolyline(positionsX, positionsY, points.size() + 1);

    }

    private void getPositionsAbs(List<Point> points) {
        int i = 0;
        positionsY = new int[points.size() + 1];
        positionsX = new int[points.size() + 1];
        for (Point point : points) {
            positionsY[i] = (int) point.getY();
            positionsX[i++] = (int) point.getX();
        }

        positionsY[points.size()] = positionsY[0];
        positionsX[points.size()] = positionsX[0];

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        points.add(getMousePosition());
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /**
     * @return the points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }
}