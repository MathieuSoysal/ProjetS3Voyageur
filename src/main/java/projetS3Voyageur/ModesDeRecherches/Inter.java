package projetS3Voyageur.ModesDeRecherches;





import java.awt.Color;
import java.awt.Graphics;/*
import java.io.FileInputStream;
import java.io.FileNotFoundException;*/
import java.io.IOException;/*
import java.util.Iterator;*/
/*
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
*/


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;*/

    class inter extends JPanel  {

        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private double offsetX, offsetY;            // decalage de la vue
        private double echelle;                     // echelle de la vue

        int points [], nbre;
        int pointsa [], nbrea;
        final int hauteur = 100;
        private int posX=-50,posY=-50;
        public inter() {
            super();
            this.offsetX = 0;
            this.offsetY = 0;
            this.echelle = 100;
        }

        public void paintComponent(Graphics g) {


            int h = this.getHeight();
            int w = this.getWidth();

            g.setColor(Color.pink);
            g.fillRect(0, 0, w, h);

            //tracÃ© des axes

            int roundOffsetX = (int) Math.round(this.offsetX);
            int roundOffsetY = (int) Math.round(this.offsetY);

            g.setColor(Color.red);
            g.drawLine(w / 3 + roundOffsetX, 0, w / 3 + roundOffsetX, h);
            g.drawLine(0, 4*h/5  + roundOffsetY, w, 4*h/5  + roundOffsetY);


            nbre=26;

            int points[] =new int[nbre];

            points[0]=0;
            points[1]=0;
            points[2]=40;
            points[3]=40;
            points[4]=80;
            points[5]=80;
            points[6]=120;
            points[7]=120;
            points[8]=160;
            points[9]=160;
            points[10]=200;
            points[11]=200;
            points[12]=240;
            points[13]=240;
            points[14]=280;
            points[15]=280;
            points[16]=320;
            points[17]=320;
            points[18]=360;
            points[19]=360;
            points[20]=400;
            points[21]=400;
            points[22]=440;
            points[23]=440;
            points[24]=480;
            points[25]=480;

            int pointsa[] =new int[nbre];

            pointsa[0]=-500;
            pointsa[1]=-480;
            pointsa[2]=-460;
            pointsa[3]=-440;
            pointsa[4]=-420;
            pointsa[5]=-400;
            pointsa[6]=-380;
            pointsa[7]=-360;
            pointsa[8]=-340;
            pointsa[9]=-320;
            pointsa[10]=-300;
            pointsa[11]=-280;
            pointsa[12]=-260;
            pointsa[13]=-240;
            pointsa[14]=-220;
            pointsa[15]=-200;
            pointsa[16]=-180;
            pointsa[17]=-160;
            pointsa[18]=-140;
            pointsa[19]=-120;
            pointsa[20]=-100;
            pointsa[21]=-80;
            pointsa[22]=-60;
            pointsa[23]=-40;
            pointsa[24]=-20;
            pointsa[25]=00;


            // Barre.
            g.setColor(Color.black);
            int i,j;
            j = 0;
            i = 0;
            while ((i <nbre-1)&&(j<nbre-1)) {

                g.drawLine(points[i]+w / 3, pointsa[j]+4*h/5,
                        points[i+1]+w / 3, pointsa[j+1]+4*h/5);

                i=i+1;
                j=j+1;
            }

            g.drawString("kaoutar",10,20);
        }


        public void takeScreenshot() {

            JFileChooser fc = new JFileChooser();

            int returnVal = fc.showOpenDialog(this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File fichier = fc.getSelectedFile();
                String nomFichier = fichier.getName().toLowerCase();

                if ((nomFichier.endsWith("png")) || (nomFichier.endsWith("jpg"))) {
                    try {
                        BufferedImage bufImage = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_RGB);
                        this.paint(bufImage.createGraphics());

                        String extension = "png";
                        if (nomFichier.endsWith("jpg")) {
                            extension = "jpg";
                        }

                        ImageIO.write(bufImage, extension, fichier);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Veuillez préciser l'extension du fichier image (png ou jpg)",
                            "Extension fichier manquante", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


    }

