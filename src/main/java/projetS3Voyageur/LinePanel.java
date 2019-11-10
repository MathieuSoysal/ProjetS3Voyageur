package projetS3Voyageur;

import static java.lang.Math.random;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * panneau de 300x200 pixels ou on dessine une ligne diagonale
 * 
 * @author jonathan
 */
public class LinePanel extends JPanel implements MouseListener {

    private List<Point> points;

    public static void main(String[] args) {

        JFrame frame = new JFrame(); // creation de la fenetre
        JPanel panel = new LinePanel(); // creation du panneau

        frame.setContentPane(panel); // integration du panneau dans la fenetre
        frame.pack(); // ajustement de la taille de la fenetre au contenu
        frame.setLocationRelativeTo(null); // centrage
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quand on clique sur la croix, ca quitte le programme
                                                              // (ce n'est pas le cas par defaut)
        frame.setVisible(true); // rend la fenetre visible (ce n'est pas le cas par defaut)
    }

    /**
     * constructeur du panneau
     */
    public LinePanel() {

        points = new LinkedList<>();
        points.add(new Point((int) (random() * 300), (int) (random() * 200)));
        points.add(new Point((int) (random() * 300), (int) (random() * 200)));
        points.add(new Point((int) (random() * 300), (int) (random() * 200)));
        points.add(new Point((int) (random() * 300), (int) (random() * 200)));
        points.add(new Point((int) (random() * 300), (int) (random() * 200)));

        setPreferredSize(new Dimension(300, 200)); // ajustement de la taille "preferee" du panneau
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.create();

        Iterator<Point> pointIterator = points.iterator();

        Point start;

        int[] x = new int[points.size() + 1];
        int[] y = new int[points.size() + 1];

        for (int i = 0; pointIterator.hasNext(); i++) {
            start = pointIterator.next();
            x[i] = (int) start.getX();
            y[i] = (int) start.getY();
        }

        x[points.size()] = x[0];
        y[points.size()] = y[0];

        graphics.drawPolyline(x, y, points.size());
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Collections.shuffle(points);
        repaint(); // on ne redessine que quand on a un nombre pair de points
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
}