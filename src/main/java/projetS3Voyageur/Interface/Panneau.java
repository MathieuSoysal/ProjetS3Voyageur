package projetS3Voyageur.Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import projetS3Voyageur.CompositionPays.Pays;
import projetS3Voyageur.ModesDeRecherches.ModeRecherche;
import projetS3Voyageur.ModesDeRecherches.PPMulti;
import projetS3Voyageur.ModesDeRecherches.PlusProcheV3;
import projetS3Voyageur.ModesDeRecherches.TrackProchesMulti;

class Panneau extends JPanel {

  /**
   *
   */
  private static final long serialVersionUID = 949185009500049201L;

  private JButton boutonReset = new JButton("Reinitialiser");
  private JButton boutonCalculer = new JButton("Calculer");
  private JButton boutonMelanger = new JButton("Melanger");
  private JComboBox<String> listeDeroulante = new JComboBox<>();

  private PanneauGraphique graphique = new PanneauGraphique();

  private ModeRecherche algo = new TrackProchesMulti();

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
    boite2.add(listeDeroulante);
    // boite2.add(boutonPlusProche);

    add(boite2);
    // #endregion Deuxième Boite

    // #endregion Organisation de l'affichage de la fênetre

    // Liste déroulante
    listeDeroulante.addItem("TrackProche");
    listeDeroulante.addItem("Plus Proche");
    listeDeroulante.addItem("Plus Proche multiple");
    listeDeroulante.addItemListener(new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
        switch (e.getItem().toString()) {
        case "Plus Proche":
          algo = new PlusProcheV3();
          break;

        case "TrackProche":
          algo = new TrackProchesMulti();
          break;

        case "Plus Proche multiple":
          algo = new PPMulti();
          break;

        default:
        System.err.println("Une erreur est survenue lors de la selection");
          break;
        }

      }

    });

    // #region Action des boutons

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
        if (graphique.points.size() > 3)
          calculer(algo);
        else
          erreurPointsInsufisant();
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

  // #region Exception
  private void erreurPointsInsufisant() {
    JOptionPane.showMessageDialog(graphique,
        "Veuillez d'abord placer au minimum 4 points sur le plant. \n Pour placer un point il suffit de cliquer sur le plant.",
        "Nombre de points insufisant", JOptionPane.ERROR_MESSAGE);
  }
  // #endregion Exception

  // #endregion Outils
}