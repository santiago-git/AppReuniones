package santiago.appreuniones.views;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import santiago.appreuniones.ConfiguracionActivity;
import santiago.appreuniones.InicioSesion;
import santiago.appreuniones.R;
import santiago.appreuniones.dto.Usuario;
import santiago.appreuniones.views.fragments.Inicio;
import santiago.appreuniones.views.fragments.MisReuniones;
import santiago.appreuniones.views.fragments.Perfil;

public class Principal extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    MisReuniones misR=new MisReuniones();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, misR)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();
                    return true;
                case R.id.navigation_dashboard:
                    Inicio inicio=new Inicio();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, inicio)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();

                    return true;
                case R.id.navigation_notifications:
                    Perfil perfil=new Perfil();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, perfil)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(null).commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Inicio inicio=new Inicio();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, inicio).commit();
    }

    public boolean  onCreateOptionsMenu(Menu menu){
     MenuInflater inflater=getMenuInflater();
     inflater.inflate(R.menu.menu_superior, menu);
     return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.config:
                Intent iConf = new Intent(this, ConfiguracionActivity.class);
                startActivity(iConf);

                break;
            case R.id.cerrar_sesion:
                eliminarSesion();
                Intent intent = new Intent(this, InicioSesion.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void eliminarSesion() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        prefs.edit().remove("usuario").commit();//elimina la clave

    }

}
