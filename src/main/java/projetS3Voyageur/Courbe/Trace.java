package projetS3Voyageur.Courbe;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Trace extends JFrame {

    private static final long serialVersionUID = -3914578220391097071L;

    private JButton btnSinus = new JButton( "Sinus" );
    private JButton btnCosinus = new JButton( "Cosinus" );
    private JButton btnBruteForce = new JButton( "BruteForce" );
    private Quadrillage grille = new Quadrillage();

    public Trace() {

        super( "Tracé" );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        JPanel contentPane = (JPanel) this.getContentPane();

        JPanel pnlTop = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
        pnlTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        btnSinus.addActionListener( this::btnSinusListener );
        pnlTop.add( btnSinus );
        btnCosinus.addActionListener( this::btnCosinusListener );
        pnlTop.add( btnCosinus );
        btnBruteForce.addActionListener( this::btnBruteForceListener );
        pnlTop.add( btnBruteForce );
        contentPane.add( pnlTop, BorderLayout.NORTH );

        contentPane.add( grille, BorderLayout.CENTER );


        this.setSize( 400, 470 );
        this.setLocationRelativeTo( null );
    }

    private void btnSinusListener( ActionEvent event ) {
        grille.setFunction( (x) -> 3*x+9 );
    }/*Math.sin( x )*/

    private void btnCosinusListener( ActionEvent event ) {
        grille.setFunction( (x) -> Math.cos( x ) );
    }

    private void btnBruteForceListener( ActionEvent event ) {
        grille.setFunction( (x) -> Math.cos( x ) );
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        Trace window = new Trace();
        window.setVisible( true );
    }

}