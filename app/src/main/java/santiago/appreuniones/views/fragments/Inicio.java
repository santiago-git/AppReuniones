package santiago.appreuniones.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import santiago.appreuniones.R;
import santiago.appreuniones.dao.UsuarioService;
import santiago.appreuniones.dto.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {


    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_inicio, container, false);

        Button btnApi = (Button) view.findViewById(R.id.btnGet);
        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("info", "click");
                cargarApiRest();
            }
        });

        return view;
    }

    public void cargarApiRest() {
/*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.19:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService servicio = retrofit.create(UsuarioService.class);
        Call<List<Usuario>> all=servicio.getUsuarios();

        all.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {

                if (!response.isSuccessful()){
                    Log.i("inf", "error: "+response.code());
                }else {
                    List<Usuario> l=response.body();
                    String txt="";
                    for(Usuario p: l){
                        Log.i("inf", p.getNom_usuario());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Log.e("err", "error: "+t);
            }
        });*/

    }

}
