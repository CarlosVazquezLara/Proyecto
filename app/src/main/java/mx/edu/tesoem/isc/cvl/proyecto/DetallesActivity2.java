package mx.edu.tesoem.isc.cvl.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetallesActivity2 extends AppCompatActivity {

    TextView lblmatricula, lblnombre, lblcorreo, lblpromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles2);

        lblmatricula = findViewById(R.id.dlblMatricula);
        lblnombre = findViewById(R.id.dlblNombre);
        lblcorreo = findViewById(R.id.dlblCorreo);
        lblpromedio = findViewById(R.id.dlblPromedio);

        String nombre = getIntent().getExtras().get("Nombre").toString();
        DatosParcelable dato = getIntent().getParcelableExtra("DatosParcelable");

        lblmatricula.setText(dato.getMatricula());
        lblnombre.setText(dato.getNombre());
        lblcorreo.setText(dato.getCorreo());
        lblpromedio.setText(dato.getPromedio());

        getSupportActionBar().setTitle(nombre);
    }
}