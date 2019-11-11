package projetS3Voyageur.Interface;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame{
  /**
   *
   */
  private static final long serialVersionUID = 7188911714801716647L;
  
  // private Panneau pan = new Panneau();

 public static void main(String[] args) {
  JFrame frame = new JFrame(); // creation de la fenetre
  JPanel panel = new Panneau(); // creation du panneau

  frame.setContentPane(panel); // integration du panneau dans la fenetre
  frame.pack(); // ajustement de la taille de la fenetre au contenu
  frame.setResizable(false);
  frame.setLocationRelativeTo(null); // centrage
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quand on clique sur la croix, ca quitte le programme
                                                        // (ce n'est pas le cas par defaut)
  frame.setVisible(true); // rend la fenetre visible (ce n'est pas le cas par defaut)
  frame.setTitle("Voyageur de Commerce");

 }

  // public Fenetre(){        
  //   this.setTitle("Animation");
  //   this.setSize(300, 300);
  //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  //   this.setLocationRelativeTo(null);
  //   this.setContentPane(pan);
  //   this.setVisible(true);
  // }

  // private void go(){
  //   for(int i = -50; i < pan.getWidth(); i++){
  //     int x = pan.getPosX(), y = pan.getPosY();
  //     x++;
  //     y++;
  //     pan.setPosX(x);
  //     pan.setPosY(y);
  //     pan.repaint();  
  //     try {
  //       Thread.sleep(1);
  //     } catch (InterruptedException e) {
  //       e.printStackTrace();
  //     }
  //   }
  // }       
}