package santiago.appreuniones.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import santiago.appreuniones.InicioSesion;
import santiago.appreuniones.R;
import santiago.appreuniones.dao.ReunionService;
import santiago.appreuniones.dto.Constantes;
import santiago.appreuniones.dto.Reunion;
import santiago.appreuniones.dto.Usuario;

public class NuevaReunionActivity extends AppCompatActivity {
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reunion);

        usuario=obtenerSesion();

        Button guardarReunion = (Button) findViewById(R.id.btn_guardarReunion);
        guardarReunion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(NuevaReunionActivity.this, UbicacionActivity.class);
                startActivity(intent);*/
                EditText nombre_reunion = (EditText)findViewById(R.id.nombre_reunion);
                EditText desc = (EditText)findViewById(R.id.descripcion);

                Reunion r = new Reunion();
                r.setNombre(nombre_reunion.getText().toString());
                r.setDescripcion(desc.getText().toString());
                r.setOwner(usuario.getId());

                java.util.Date fecha = new Date();

                Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                r.setFecha(formatter.format(fecha));

                crearReunion(r);
            }
        });

    }

    private void crearReunion(Reunion miRreunion){
        Constantes c=new Constantes();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(c.getRUTASERVIDOR())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ReunionService servicio = retrofit.create(ReunionService.class);

        Call<Integer> call = servicio.crearReunion(miRreunion);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.code()==200){
                    if(response.body()==1){
                        Toast.makeText(getApplicationContext(), "Se agregó la reunión satisfactoriamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NuevaReunionActivity.this, Principal.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "No se pudo agregar la reunion", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Fallo en el servidor", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("Error", "Err: "+t);
                Toast.makeText(getApplicationContext(), "No se ha podido establecer la comunicacion", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Usuario obtenerSesion() {
        System.out.println("Sesión");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplication());
        Gson gson = new Gson(); //Instancia Gson.
        String json = prefs.getString("usuario", "");
        Usuario u = gson.fromJson(json, Usuario.class);

        return u;
        //prefs.edit().remove("usuario").commit();//elimina la clave

    }


}
