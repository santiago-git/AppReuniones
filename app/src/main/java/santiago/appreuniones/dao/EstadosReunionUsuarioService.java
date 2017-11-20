package santiago.appreuniones.dao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import santiago.appreuniones.dto.Estados_reunion_usuario;

public interface EstadosReunionUsuarioService {
    @GET("invitacionesUsuario/{idUsuario}")
    public Call<List<Estados_reunion_usuario>> obtenerIvitacionesUsuario(@Path("idUsuario") int idUsuario);
}
