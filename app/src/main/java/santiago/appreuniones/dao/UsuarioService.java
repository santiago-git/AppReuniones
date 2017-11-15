package santiago.appreuniones.dao;

import android.widget.ArrayAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import santiago.appreuniones.dto.Usuario;

/**
 * Created by roms_ on 20/10/2017.
 */

public interface UsuarioService {
    @GET("pruebaget")
    Call<ArrayAdapter<Usuario>> getUsuarios();

    @GET("usuario/{id}")
    Call<Usuario> getUsuario(@Path("id") int id);

    @POST("usuario/login")
    Call<Usuario> login(@Body Usuario u);

}
