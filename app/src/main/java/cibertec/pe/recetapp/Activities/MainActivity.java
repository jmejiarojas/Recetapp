package cibertec.pe.recetapp.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

        //Definimos un callBack
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            //Aca, analizamos el movimiento
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Lo que queremos hacer cuando movamos
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //Me pasa la posicion que tiene el elemento en el adaptador
                int position = viewHolder.getAdapterPosition();

                //Capturamos el adaptador del recyclerViewRecetas
                RecetasAdapter objAdapter = (RecetasAdapter) recyclerViewRecetas.getAdapter();

                String nombreReceta = objAdapter.getRecetaList().get(position).getNombre();

                data.deleteItem(nombreReceta);
                update();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);

        //El itemTouchHelper se anhade al recyclerView
        itemTouchHelper.attachToRecyclerView(recyclerViewRecetas);

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
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Papa rellena",4,"Una comida peruana","Cocer la papa","image",0));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Palta rellena",2,"Una comida peruana","Cocer la palta","image",0));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Yuca rellena",3,"Una comida peruana","Cocer la yuca","image",1));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Aji de gallina",3,"Una comida peruana","Cocer la gallina","image",1));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Rocoto relleno",3,"Una comida peruana","Cocer el rocoto","image",1));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Carapulcra",3,"Una comida peruana","Cocinar el arroz","image",0));
        recetaList.add(new Receta(UUID.randomUUID().toString(),"Arroz con pato",3,"Una comida peruana","Cocer el pato","image",1));

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

    //Inflamos nuestro menu personalizado al activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_receta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemFavoritos:
                recetasAdapter = new RecetasAdapter(this, data.getFavoritos());
                recyclerViewRecetas.setAdapter(recetasAdapter);
                return true;
            case R.id.itemPersonas:
                recetasAdapter = new RecetasAdapter(this, data.getByNumPersonas(2));
                recyclerViewRecetas.setAdapter(recetasAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Este metodo se ejecuta cuando la actividad vuelve a estar activa
    @Override
    protected void onResume() {
        super.onResume();
        update();
    }
}
