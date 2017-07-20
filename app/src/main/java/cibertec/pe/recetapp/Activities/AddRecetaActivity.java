package cibertec.pe.recetapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

import cibertec.pe.recetapp.Entities.Receta;
import cibertec.pe.recetapp.Helpers.DataBase;
import cibertec.pe.recetapp.R;

public class AddRecetaActivity extends AppCompatActivity {

    EditText editTextId;
    EditText editTextNombre;
    EditText editTexDescripcion;
    EditText editTextNumPersonas;
    EditText editTextFav;
    EditText editTextPreparacion;
    Button buttonAdd;

    DataBase dataBase;
    Receta receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);

        //git lg1editTextId = (EditText) findViewById(R.id.editTextID);
        editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        editTexDescripcion = (EditText) findViewById(R.id.editTextDescripcion);
        editTextNumPersonas = (EditText) findViewById(R.id.editTextNumPersonas);
        editTextFav = (EditText) findViewById(R.id.editTextFav);
        editTextPreparacion = (EditText) findViewById(R.id.editTextPreparacion);

        buttonAdd = (Button) findViewById(R.id.buttonAddReceta);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                receta = new Receta(UUID.randomUUID().toString(),
                        editTextNombre.getText().toString(),
                        Integer.parseInt(editTextNumPersonas.getText().toString()),
                        editTexDescripcion.getText().toString(),
                        editTextPreparacion.getText().toString(),
                        "image.jpg",
                        Integer.parseInt(editTextFav.getText().toString())
                        );
                dataBase = new DataBase(getApplicationContext());
                dataBase.openDatabase();
                dataBase.insertReceta(receta);
                Toast.makeText(AddRecetaActivity.this, "Se ha insertado la receta", Toast.LENGTH_SHORT).show();
                finish(); //Para cerrar la Activity
            }
        });
    }
}
