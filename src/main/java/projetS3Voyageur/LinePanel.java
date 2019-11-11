package projetS3Voyageur;

import static java.lang.Math.random;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import projetS3Voyageur.CompositionPays.Pays;

/**
 * panneau de 300x200 pixels
 * 
 * @author jonathan
 */
public class LinePanel extends JPanel implements MouseListener {

    private List<Point> points;
    private List<Point> pointsOpti;

    private int[] positionsX;
    private int[] positionsY;

    private JButton boutonReset = new JButton("Reinitialiser");
    private JButton boutonCalculer = new JButton("Calculer");

    private Pays graphe;

    public static void main(String[] args) {

        JFrame frame = new JFrame(); // creation de la fenetre
        JPanel panel = new LinePanel(); // creation du panneau

        frame.setContentPane(panel); // integration du panneau dans la fenetre
        frame.pack(); // ajustement de la taille de la fenetre au contenu
        frame.setLocationRelativeTo(null); // centrage
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quand on clique sur la croix, ca quitte le programme
                                                              // (ce n'est pas le cas par defaut)
        frame.setVisible(true); // rend la fenetre visible (ce n'est pas le cas par defaut)
        frame.setTitle("Voyageur de Commerce");

    }

    /**
     * constructeur du panneau
     */
    public LinePanel() {

        points = new LinkedList<>();

        add(boutonReset);
        add(boutonCalculer);
        setPreferredSize(new Dimension(400, 400));
        addMouseListener(this);

        boutonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                points.clear();
                
            }
          });

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        getPositionsAbs(points);
        graphics.setColor(Color.gray);
        graphics.drawPolyline(positionsX, positionsY, points.size() + 1);

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

    // try {
    //     Thread.sleep(3);
    //   } catch (InterruptedException e) {
    //     e.printStackTrace();
    //   }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        points.add(getMousePosition());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }
}