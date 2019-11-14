package projetS3Voyageur.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton backTrackV2Button;
    private JPanel Courbes;
    private JSpinner spinner1;

    public App() {
        backTrackV2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bonjour");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args) {

        JFrame interface1 = new JFrame("App"); // crée l'interface
        interface1.setContentPane(new App().Courbes); // afficher l'interface
        interface1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermer l'interface
        interface1.pack();
        interface1.setVisible(true);
    }
}

/*tuto https://www.youtube.com/watch?v=5vSyylPPEko*/