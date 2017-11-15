package santiago.appreuniones.dao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import santiago.appreuniones.dto.Reunion;
import santiago.appreuniones.dto.Usuario;

/**
 * Created by roms_ on 23/10/2017.
 */

public interface ReunionService {
    @Headers({
            "Accept: application/json"
    })
    @GET("reunion/reunionusuario/{idUsuario}")
    public Call<List<Reunion>> obtenerReunionesUsuario(@Path("idUsuario") int idUsuario);

    @GET("reunion/5")
    Call<String> getReunion();

    @POST("reunion/crearreunion")
    Call<Integer> crearReunion(@Body Reunion r);
}
