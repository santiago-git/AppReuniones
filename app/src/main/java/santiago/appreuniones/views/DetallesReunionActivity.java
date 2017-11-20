package santiago.appreuniones.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import santiago.appreuniones.R;
import santiago.appreuniones.dto.Reunion;

public class DetallesReunionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_reunion);



        Reunion r= (Reunion) getIntent().getSerializableExtra("objeto");

        System.out.println(r);
    }
}
