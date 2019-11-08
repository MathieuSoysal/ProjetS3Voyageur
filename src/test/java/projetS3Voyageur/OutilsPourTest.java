package projetS3Voyageur;

public class OutilsPourTest{

    public static double parcoursVilles(Pays pays, String villesEmpruntee,String seprateur) {
        String[] villesEmprunteString = villesEmpruntee.split(seprateur);
        byte[] villesEmprunteByte = new byte[villesEmprunteString.length];
        for (byte i = 0; i < villesEmprunteString.length; i++) {
            villesEmprunteByte[i] = Byte.parseByte(villesEmprunteString[i]);
        }

        double distancParcourue = 0;
        for (byte i = 1; i < villesEmprunteByte.length; i++) {
            distancParcourue += pays.getDistanceEntreVilles(villesEmprunteByte[i - 1], villesEmprunteByte[i]);
        }

        return distancParcourue;
    }
}