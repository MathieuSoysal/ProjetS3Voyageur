package projetS3Voyageur.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

/**
 * panneau de 300x200 pixels
 * 
 * @author jonathan & Mathieu
 */
class PanneauGraphique extends JPanel implements MouseListener {

    /**
     *
     */
    private static final long serialVersionUID = 4883455012046646573L;

    // TODO: temporairement mit en public pour les tests
    List<Point> points;

    private int[] positionsX;
    private int[] positionsY;

    PanneauGraphique() {

        points = new LinkedList<>();

        setBackground(Color.darkGray);
        setPreferredSize(new Dimension(400, 400));
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        getPositionsX_Y(points);
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

    }

    // #region Outils

    private void getPositionsX_Y(List<Point> points) {
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

    // #endregion Outils

    // #region Evenement souris

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        //TODO gérer l'exception à 30 ville
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

    // #endregion Evenement souris
}