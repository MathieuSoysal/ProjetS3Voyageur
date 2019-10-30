
package projetS3Voyageur.StatsAlgos;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

public class Repertorisation {

    public static File getRepertoire() {
        File f = new File("Statistiques/" + getNameOrdinateur()+"/"+LocalDate.now());
        f.mkdirs();
        return f;
    }

    private static String getNameOrdinateur() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            return env.get("HOSTNAME");
        else
            return System.getProperty("user.name");
    }
}