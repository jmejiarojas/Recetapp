package cibertec.pe.recetapp.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cibertec.pe.recetapp.Adapters.RecetasAdapter;
import cibertec.pe.recetapp.Entities.Receta;
import cibertec.pe.recetapp.Helpers.DataBase;
import cibertec.pe.recetapp.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewRecetas;
    List<Receta> recetaList;
    RecetasAdapter recetasAdapter;
    DataBase data;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createData();

        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerViewRecetas = (RecyclerView) findViewById(R.id.recyclerRecetas);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecetas.setLayoutManager(linearLayoutManager);

        //recetasAdapter = new RecetasAdapter(this, recetaList);
        //recyclerViewRecetas.setAdapter(recetasAdapter);
        update();

        //Add event to fab
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddRecetaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void createData(){
        recetaList = new ArrayList<>();
        recetaList.add(new Receta("1","Papa rellena",4,"Una comida peruana","Cocer la papa","image",0));
        recetaList.add(new Receta("2","Palta rellena",2,"Una comida peruana","Cocer la palta","image",0));
        recetaList.add(new Receta("3","Yuca rellena",3,"Una comida peruana","Cocer la yuca","image",1));
        recetaList.add(new Receta("4","Aji de gallina",3,"Una comida peruana","Cocer la gallina","image",1));
        recetaList.add(new Receta("5","Rocoto relleno",3,"Una comida peruana","Cocer el rocoto","image",1));
        recetaList.add(new Receta("6","Carapulcra",3,"Una comida peruana","Cocinar el arroz","image",0));
        recetaList.add(new Receta("7","Arroz con pato",3,"Una comida peruana","Cocer el pato","image",1));

        data = new DataBase(getApplicationContext());
        data.openDatabase();
        data.insertRecetas(recetaList);
    }

    public List<Receta> getData(){
        return data.getAll();
    }

    //Pintar la lista de Recetas desde la BD
    public void update(){
        recetasAdapter = new RecetasAdapter(this, getData());
        recyclerViewRecetas.setAdapter(recetasAdapter);
    }

    //Este metodo se ejecuta cuando la actividad vuelve a estar activa
    @Override
    protected void onResume() {
        super.onResume();
        update();
    }
}
