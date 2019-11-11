package projetS3Voyageur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.TrackProchesV2_1;

public class Panneau extends JPanel {

  /**
   *
   */
  private static final long serialVersionUID = 949185009500049201L;

  private JButton boutonReset = new JButton("Reinitialiser");
  private JButton boutonCalculer = new JButton("Calculer");
  private JButton boutonMelanger = new JButton("Melanger");

  private ModeRecherche algo = new TrackProchesV2_1();

  public Panneau() {

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    JPanel box1 = new JPanel();

    PanneauGraphique graphique = new PanneauGraphique();
    box1.setLayout(new BoxLayout(box1, BoxLayout.LINE_AXIS));
    box1.add(graphique);
    add(box1);

    JPanel box2 = new JPanel();

    box2.setLayout(new BoxLayout(box2, BoxLayout.LINE_AXIS));
    box2.add(boutonReset);
    box2.add(boutonCalculer);
    box2.add(boutonMelanger);

    add(box2);

    boutonMelanger.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        Collections.shuffle(graphique.points);
        graphique.repaint();
      }
    });

    boutonReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        graphique.points.clear();
        graphique.repaint();
      }
    });

    boutonCalculer.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        ModeRecherche algoCalcul = algo;
        Pays pays = new Pays(graphique.points);
        algoCalcul.recherche(pays, 0);
        graphique.points.clear();

        String parcours = algoCalcul.getParcours().getVillesEmprunté();
        parcours = parcours.substring(0, parcours.length() - 2);
        for (String ville : parcours.split(">")) {
          graphique.points.add(pays.getPositionVille(Integer.valueOf(ville)));
        }

        graphique.repaint();
      }
    });
  }
}