package projetS3Voyageur.Interface;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
  /**
   *
   */
  private static final long serialVersionUID = 7188911714801716647L;

  public static void main(String[] args) {
    JFrame frame = new JFrame(); // creation de la fenetre
    Panneau panel = new Panneau(); // creation du panneau

    frame.setContentPane(panel); // integration du panneau dans la fenetre
    frame.pack(); // ajustement de la taille de la fenetre au contenu
    frame.setResizable(false);
    frame.setLocationRelativeTo(null); // centrage
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quand on clique sur la croix, ca quitte le programme
                                                          // (ce n'est pas le cas par defaut)
    frame.setVisible(true); // rend la fenetre visible (ce n'est pas le cas par defaut)
    frame.setTitle("Voyageur de Commerce");

  }
}