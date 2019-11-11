package projetS3Voyageur.Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PlusProcheV2;
import projetS3Voyageur.ModesDeRecherches.TrackProchesV2_1;

class Panneau extends JPanel {

  /**
   *
   */
  private static final long serialVersionUID = 949185009500049201L;

  private JButton boutonReset = new JButton("Reinitialiser");
  private JButton boutonCalculer = new JButton("Calculer");
  private JButton boutonMelanger = new JButton("Melanger");
  private JButton boutonPlusProche = new JButton("Voisin plus proche");

  private PanneauGraphique graphique = new PanneauGraphique();

  Panneau() {

    // #region Organisation de l'affichage de la fênetre :

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    // #region Première Boite
    JPanel boite1 = new JPanel();

    boite1.setLayout(new BoxLayout(boite1, BoxLayout.LINE_AXIS));
    boite1.add(graphique);
    add(boite1);
    // #endregion Première Boite

    // #region Deuxième Boite
    JPanel boite2 = new JPanel();

    boite2.setLayout(new BoxLayout(boite2, BoxLayout.LINE_AXIS));
    boite2.add(boutonReset);
    boite2.add(boutonCalculer);
    boite2.add(boutonMelanger);
    boite2.add(boutonPlusProche);

    add(boite2);
    // #endregion Deuxième Boite
    
    // #endregion Organisation de l'affichage de la fênetre

    // #region Action des boutons

    // Bouton Plus Proche
    boutonPlusProche.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        calculer(new PlusProcheV2());
      }
    });

    // Bouton Mélanger
    boutonMelanger.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        Collections.shuffle(graphique.points);
        graphique.repaint();
      }
    });

    // Bouton Reset
    boutonReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent event) {
        graphique.points.clear();
        graphique.repaint();
      }
    });

    // Bouton Calculer
    boutonCalculer.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent event) {
        calculer(new TrackProchesV2_1());
      }
    });
    // #endregion Action des boutons
  }

  // #region Outils
  private void calculer(ModeRecherche algo) {
    Pays pays = new Pays(graphique.points);
    algo.recherche(pays, 0);
    graphique.points.clear();

    String parcours = algo.getParcours().getVillesEmprunté();
    parcours = parcours.substring(0, parcours.length() - 2);
    for (String ville : parcours.split(">")) {
      graphique.points.add(pays.getPositionVille(Integer.valueOf(ville)));
    }

    graphique.repaint();
  }
  // #endregion Outils
}