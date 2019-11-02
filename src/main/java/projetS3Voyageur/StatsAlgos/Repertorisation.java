
package projetS3Voyageur.StatsAlgos;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

public class Repertorisation {

    /**
     * Renvoie le chemin d'un répertoire en créant ou en choisissant les répertoires
     * en fonction du nom de l'ordinateur et de la date actuelle dans le répertoire
     * Statistique
     * 
     * @return {@code File} renvoie un répertoire en suivant le chemin :
     *         Statistiques/{Nom Ordinateur}/{Date actuelle}/
     */
    public static File getRepertoire() {
        File f = new File("Statistiques/" + getNameOrdinateur() + "/" + LocalDate.now());
        f.mkdirs();
        return f;
    }

    /**
     * Essaye de récupérer (afin de le renvoyer) nom de l'ordinateur sinon renvois le
     * nom de la session actuelle
     * 
     * @return {@code String} nom de l'ordinateur sinon nom de la session
     */
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