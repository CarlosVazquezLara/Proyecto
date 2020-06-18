package mx.edu.tesoem.isc.cvl.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView gvDatos;
    EditText txtmatricula, txtnombre, txtcorreo, txtpromedio;
    List<Datos> datos = new ArrayList<>();
    AdaptadorBase adaptadorBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvDatos = findViewById(R.id.gvDatos);
        txtmatricula = findViewById(R.id.txtMatricula);
        txtnombre = findViewById(R.id.txtNombre);
        txtcorreo = findViewById(R.id.txtCorreo);
        txtpromedio = findViewById(R.id.txtPromedio);

        verifica();

        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datos dato = (Datos) adaptadorBase.getItem(position);
                DatosParcelable datosParcelable = new DatosParcelable(dato.getMatricula(),dato.getNombre(),dato.getCorreo(),dato.getPromedio());
                Intent intent = new Intent(MainActivity.this, DetallesActivity2.class);
                intent.putExtra("Nombre",dato.getNombre());
                intent.putExtra("DatosParcelable",datosParcelable);
                startActivity(intent);
            }
        });
    }

    private void verifica(){
        Almacen conexion = new Almacen();

        if (conexion.Existe(this)){
            if (conexion.Leer(this)){
                datos = conexion.getDatos();
                adaptadorBase = new AdaptadorBase(datos, this);
                gvDatos.setAdapter(adaptadorBase);
            }else {
                Toast.makeText(this, "No sepudo leer la información", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "No existe el archivo, favor de grabar información", Toast.LENGTH_SHORT).show();
        }
    }

    public void Graba(View v){
        Datos dato = new Datos();
        Almacen conexion = new Almacen();

        dato.setMatricula(txtmatricula.getText().toString());
        dato.setNombre(txtnombre.getText().toString());
        dato.setCorreo(txtcorreo.getText().toString());
        dato.setPromedio(txtpromedio.getText().toString());

        datos.add(dato);
        if (conexion.Escribir(this, datos)){
            Toast.makeText(this, "Se escribieron los datos correctamente", Toast.LENGTH_SHORT).show();
            verifica();
        }else{
            Toast.makeText(this, "Error al escribir los datos", Toast.LENGTH_SHORT).show();
        }
    }
}