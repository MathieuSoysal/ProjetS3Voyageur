package projetS3Voyageur.Brouillon;


import javax.swing.*;

public class CurveCanvas extends JComponent {

 /*   private static final long serialVersionUID = 7800853645256601960L;

    private CurveFunction function = (x) -> Math.sin( x );


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        // --- Activate antialiasing flag ---
        Graphics2D graphics1 = (Graphics2D) graphics;
        graphics1.setRenderingHint( RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON );

        // --- White background ---
        graphics.setColor( Color.WHITE );
        graphics.fillRect( 0, 0, getWidth(), getHeight() );

        // --- Draw axis ---
        graphics.setColor( Color.GRAY );
        graphics.drawLine( 0, getHeight()/2, getWidth(), getHeight()/2 );
        graphics.drawLine( getWidth()/2, 0, getWidth()/2, getHeight() );

        // --- Draw values ---
        graphics.setColor( Color.BLACK );
        graphics.drawString( "0,0", (int)(getWidth()*0.51), (int)(getHeight()*0.54));
        graphics.drawString( "-\u03c0", (int)(getWidth()*0.02), (int)(getHeight()*0.54));
        graphics.drawString( "\u03c0", (int)(getWidth()*0.96), (int)(getHeight()*0.54));


        // --- Draw curve ---
        double step = 0.1;
        graphics.setColor( new Color( 10, 40, 150 ) );

        int oldX = xToPixel( -Math.PI );
        int oldY = yToPixel( function.compute( -Math.PI ) );

        for( double lx=-Math.PI+step; lx<= Math.PI+step; lx+=step ) {

            int x = xToPixel( lx );
            int y = yToPixel( function.compute( lx ) );

            graphics.drawLine( x, y, oldX, oldY );

            oldX = x;
            oldY = y;
        }
    }

    public void setFunction(CurveFunction function) {
        this.function = function;
        this.repaint();
    }

    private int xToPixel( double x ) {
        return (int)( getWidth() * (x + Math.PI)/(2*Math.PI) );
    }

    private int yToPixel( double y ) {
        return (int)( getHeight() * (1 - (y + 1)/2.0 ) );
    }


    public static interface CurveFunction {

        public double compute( double x );

    }
*/
}