package santiago.appreuniones;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import santiago.appreuniones.dao.UsuarioService;
import santiago.appreuniones.dto.Constantes;
import santiago.appreuniones.dto.Usuario;
import santiago.appreuniones.views.Principal;

public class InicioSesion extends AppCompatActivity{

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        comprobarSesion();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario u =new Usuario();
                u.setCorreo(mEmailView.getText().toString().trim());
                u.setContrasena(mPasswordView.getText().toString().trim());

                validarUsuario(u);
                //Intent intent = new Intent(InicioSesion.this, Principal.class);
                //startActivity(intent);
            }
        });
    }

    private void comprobarSesion() {
        Usuario u = obtenerSesion();
        if (u!=null){
            System.out.println("No es nulo");
            Intent intent = new Intent(InicioSesion.this, Principal.class);
            startActivity(intent);
            finish();
        }
    }

    private void validarUsuario(Usuario u) {
        Constantes c=new Constantes();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(c.getRUTASERVIDOR())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsuarioService servicio = retrofit.create(UsuarioService.class);

        Call<Usuario> call = servicio.login(u);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.code()==200 && response.body()!=null){
                    Usuario u=response.body();
                    guardarSesion(u);
                    
                    Toast.makeText(getApplicationContext(), u.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InicioSesion.this, Principal.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña invalido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("", t.toString());
                Toast.makeText(getApplicationContext(), "No se pudo conectar al servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarSesion(Usuario u) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor prefsEditor = prefs.edit();
        Gson gson = new Gson();  //Instancia Gson.
        String json = gson.toJson(u); //convierte a .json el objeto
        prefsEditor.putString("usuario", json);
        prefsEditor.commit();
        System.out.println(json);
    }

    private Usuario obtenerSesion() {
        System.out.println("Sesión");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson(); //Instancia Gson.
        String json = prefs.getString("usuario", "");
        Usuario u = gson.fromJson(json, Usuario.class);

        return u;
        //prefs.edit().remove("usuario").commit();//elimina la clave

    }
}