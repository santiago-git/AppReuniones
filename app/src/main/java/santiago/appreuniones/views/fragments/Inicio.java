package santiago.appreuniones.views.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import santiago.appreuniones.R;
import santiago.appreuniones.adapter.AdaptadorInvitaciones;
import santiago.appreuniones.dao.EstadosReunionUsuarioService;
import santiago.appreuniones.dao.ReunionService;
import santiago.appreuniones.dao.UsuarioService;
import santiago.appreuniones.dto.Constantes;
import santiago.appreuniones.dto.Estados_reunion_usuario;
import santiago.appreuniones.dto.Reunion;
import santiago.appreuniones.dto.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    Usuario usuario;
    List<Estados_reunion_usuario> listaInvitaciones;
    RecyclerView recyclerInvitaciones;


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerInvitaciones = view.findViewById(R.id.reciyclerInvitaciones);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerInvitaciones.setLayoutManager(mLayoutManager);

        usuario=obtenerSesion();
        cargarInvitaciones();




        return view;
    }

    private void cargarRecycler(){
        AdaptadorInvitaciones adapter=new AdaptadorInvitaciones(listaInvitaciones);
        recyclerInvitaciones.setAdapter(adapter);
    }




    public void cargarInvitaciones() {
        Constantes c=new Constantes();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(c.getRUTASERVIDOR())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EstadosReunionUsuarioService servicio = retrofit.create(EstadosReunionUsuarioService.class);

        Call<List<Estados_reunion_usuario>> call = servicio.obtenerIvitacionesUsuario(usuario.getId());

        call.enqueue(new Callback<List<Estados_reunion_usuario>>() {
            @Override
            public void onResponse(Call<List<Estados_reunion_usuario>> call, Response<List<Estados_reunion_usuario>> response) {
                if(response.code()==200){
                    listaInvitaciones=response.body();
                    Log.i("Resp", response.body().toString());
                    cargarRecycler();
                }else{
                    Toast.makeText(getActivity(), "Fallo en el servidor", Toast.LENGTH_SHORT).show();
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Estados_reunion_usuario>> call, Throwable t) {
                Log.e("Error", "Err: "+t);
                Toast.makeText(getActivity(), "No se ha podido establecer la comunicacion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Usuario obtenerSesion() {
        //System.out.println("Sesión");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson(); //Instancia Gson.
        String json = prefs.getString("usuario", "");
        Usuario u = gson.fromJson(json, Usuario.class);

        return u;
        //prefs.edit().remove("usuario").commit();//elimina la clave

    }

}
