package santiago.appreuniones.dto;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by roms_ on 6/11/2017.
 */

public class Constantes {
    private static final String RUTASERVIDOR = "http://192.168.0.25/";
   // private static final String RUTASERVIDOR = "http://192.168.43.243/";


    public static String getRUTASERVIDOR() {
        return RUTASERVIDOR;
    }
}
