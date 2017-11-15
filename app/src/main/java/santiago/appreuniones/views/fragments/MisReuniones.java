package santiago.appreuniones.views.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import santiago.appreuniones.adapter.ListAdapter;
import santiago.appreuniones.dao.ReunionService;
import santiago.appreuniones.dao.UsuarioService;
import santiago.appreuniones.dto.Constantes;
import santiago.appreuniones.dto.Reunion;
import santiago.appreuniones.dto.Usuario;
import santiago.appreuniones.views.DetallesReunionActivity;
import santiago.appreuniones.views.NuevaReunionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MisReuniones extends Fragment {


    public MisReuniones() {
        // Required empty public constructor
    }

    View view;
    List<Reunion> reuniones;
    Usuario usuario;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mis_reuniones, container, false);

        usuario=obtenerSesion();

        cagarLista();



        //Boton flotante ( circulo rojo)
        FloatingActionButton btnAgReu = (FloatingActionButton) view.findViewById(R.id.btn_agregarReunion);
        btnAgReu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NuevaReunionActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }



    private void cargarReciclerView() {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.reciclerId);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration itemDecoration=new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());

        // specify an adapter (see also next example)
        mAdapter = new ListAdapter(reuniones);

        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void cagarLista() {

        Constantes c=new Constantes();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(c.getRUTASERVIDOR())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReunionService servicio = retrofit.create(ReunionService.class);

        Call<List<Reunion>> call = servicio.obtenerReunionesUsuario(usuario.getId());

        call.enqueue(new Callback<List<Reunion>>() {
            @Override
            public void onResponse(Call<List<Reunion>> call, Response<List<Reunion>> response) {
                System.out.println(response);
                if(response.code()==200){
                    reuniones=response.body();
                    //Log.i("Resp", response.body().toString());
                    cargarReciclerView();
                }else{
                    Toast.makeText(getActivity(), "Fallo en el servidor", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Reunion>> call, Throwable t) {
                Log.e("Error", "Err: "+t);
                Toast.makeText(getActivity(), "No se ha podido establecer la comunicacion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Usuario obtenerSesion() {
        System.out.println("Sesión");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        Gson gson = new Gson(); //Instancia Gson.
        String json = prefs.getString("usuario", "");
        Usuario u = gson.fromJson(json, Usuario.class);

        return u;
        //prefs.edit().remove("usuario").commit();//elimina la clave

    }


}
