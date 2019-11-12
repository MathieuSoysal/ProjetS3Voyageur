package projetS3Voyageur.Brouillon;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class Interface_class implements Interface {

    private JPanel p;
    private final int largeur = 400;
    private final int hauteur = 400;
    private final int echelle = 2;


    public static void main(final String[] args) {
        new Interface_class();
    }


    public Interface_class() {

        JFrame f = new JFrame();
        p = new JPanel();
        JButton b = new JButton("dessiner");

        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
      /*  b.addActionListener(this);*/

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.getContentPane().add(p, BorderLayout.CENTER);
        f.getContentPane().add(b, BorderLayout.SOUTH);

        p.setPreferredSize(new Dimension(largeur, hauteur));

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }


    private double getPoint(final double x) {
        double y = x * x;
        return y;
    }


    private Map<Double, Double> getCurve(final double debut, final double fin, final double step) {
        Map<Double, Double> map = new LinkedHashMap<Double, Double>();
        for (double x = debut; x <= fin; x = x + step) {
            double y = getPoint(x);
            map.put(x, y);
        }
        return map;
    }

    private void drawCurve(final Map<Double, Double> map, final Graphics g) {
        int ox = largeur / 2;
        int oy = hauteur / 2;
        g.translate(ox, oy);

        g.setColor(Color.BLACK);
        g.drawLine(-ox, 0, ox, 0);
        g.drawLine(0, -oy, 0, oy);

        g.setColor(Color.BLUE);

        Set<Double> keys = map.keySet();
        Double lastX = null;
        Double lastY = null;
        for (Double key : keys) {
            Double value = map.get(key);
            if (lastX != null && lastY != null) {
                g.drawLine(lastX.intValue() * echelle, lastY.intValue() * -1 * echelle,
                        key.intValue() * echelle, value.intValue() * -1 * echelle);
            }
            lastX = key;
            lastY = value;
        }
    }


    private final void showPoints(final Map<Double, Double> map) {
        Set<Double> keys = map.keySet();
        for (Double key : keys) {
            Double value = map.get(key);
            System.out.println("x = " + key + ", y = " + value);
        }
    }


    @Override
    public void actionPerformed(final ActionEvent e) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Graphics g = p.getGraphics();
                Map<Double, Double> map = getCurve(-8, 8, 1);
                showPoints(map);
                drawCurve(map, g);
            }
        });

    }

}

