package projetS3Voyageur.Interface_Graphique;


import javax.swing.*;
import java.awt.*;

public class IHM extends JFrame {

    public IHM() {
        super();
        setTitle("Notes des élèves");
        setPreferredSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
    }
}