package projetS3Voyageur.ModesDeRecherches;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class CurveTracer extends JFrame {

   /* private static final long serialVersionUID = -3914578220391097071L;

    private JButton btnSinus = new JButton( "Sinus" );
    private JButton btnCosinus = new JButton( "Cosinus" );
    private CurveCanvas curveCanvas = new CurveCanvas();

    public CurveTracer() {
        super( "Curve tracer" );
        this.setDefaultCloseOperation( DISPOSE_ON_CLOSE );

        JPanel contentPane = (JPanel) this.getContentPane();

        JPanel pnlTop = new JPanel( new GridLayout( 1, 2, 10, 0 ) );
        pnlTop.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
        btnSinus.addActionListener( this::btnSinusListener );
        pnlTop.add( btnSinus );
        btnCosinus.addActionListener( this::btnCosinusListener );
        pnlTop.add( btnCosinus );
        contentPane.add( pnlTop, BorderLayout.NORTH );

        contentPane.add( curveCanvas, BorderLayout.CENTER );


        this.setSize( 400, 470 );
        this.setLocationRelativeTo( null );
    }

    private void btnSinusListener( ActionEvent event ) {
        curveCanvas.setFunction( (x) -> Math.sin( x ) );
    }

    private void btnCosinusListener( ActionEvent event ) {
        curveCanvas.setFunction( (x) -> Math.cos( x ) );
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel( new NimbusLookAndFeel() );
        CurveTracer window = new CurveTracer();
        window.setVisible( true );
    }
*/
}