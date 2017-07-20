package cibertec.pe.recetapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cibertec.pe.recetapp.Entities.Receta;

/**
 * Created by Julio on 18/07/2017.
 */

public class DataBase {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    SQLiteOpenHelper sqLiteOpenHelper;

    public DataBase(Context context) {
        this.context = context;
        //Habilitamos la BD para empezarse a utilizar
        sqLiteOpenHelper = new DBHelper(context);
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void openDatabase(){
        sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
    }

    public void closeDatabase(){
        sqLiteOpenHelper.close();
    }

    public void insertReceta(Receta receta){
        ContentValues contentValues = receta.toValues();
        sqLiteDatabase.insert(SQLConstants.TABLE_RECETAS,null, contentValues);
    }

    //Me retorna el numero de registros que tiene mi tabla
    public Long getItemCounts(){
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,SQLConstants.TABLE_RECETAS);
    }

    //Para insertar varias recetas a la vez
    public void insertRecetas(List<Receta> recetas){
        long items = getItemCounts();
        if(items == 0) //Solo vamos a insertar los datos, si esta vacia la tabla
        {
            for (Receta receta: recetas) {
                try{
                    insertReceta(receta);
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Receta> getAll(){
        List<Receta> recetasList = new ArrayList<>();

        //Cargamos el cursor con los datos de la tabla
        Cursor cursor = sqLiteDatabase.query(SQLConstants.TABLE_RECETAS,
                SQLConstants.ALL_COLUMNS,
                null,
                null,
                null,
                null,
                null
                );

        //Cargando los datos del cursor a recetasList
        while (cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_NOMBRE)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_DESCRIPCION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_PREPARACION)));
            receta.setNumPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_PERSONAS)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_IMAGEN)));
            receta.setFavorito(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_FAV)));
            recetasList.add(receta);
        }

        return recetasList;
    }

    public List<Receta> getFavoritos(){
        List<Receta> recetasFav = new ArrayList<>();
        String[] whereArgs = new String[] { String.valueOf(1)};
        Cursor cursor = sqLiteDatabase.query(SQLConstants.TABLE_RECETAS,
                SQLConstants.ALL_COLUMNS,
                SQLConstants.WHERE_CLAUSE_FAVORITOS,
                whereArgs,
                null,
                null,
                null
                );
        while (cursor.moveToNext()){
            Receta receta = new Receta();
            receta.setId(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_ID)));
            receta.setNombre(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_NOMBRE)));
            receta.setDescripcion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_DESCRIPCION)));
            receta.setPreparacion(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_PREPARACION)));
            receta.setNumPersonas(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_PERSONAS)));
            receta.setImage(cursor.getString(cursor.getColumnIndex(SQLConstants.COLUMN_IMAGEN)));
            receta.setFavorito(cursor.getInt(cursor.getColumnIndex(SQLConstants.COLUMN_FAV)));
            recetasFav.add(receta);
        }

        return recetasFav;
    }

    /**
    * En este metodo borramos un item por el nombre de la receta,
    * este metodo es usado en el MainActivity en el metodo onSwiped
    * del simpleCallBack
    * */
    public void deleteItem(String nombre){
        //String[] whereArgs = {nombre};
        String[] whereArgs = new String[] { String.valueOf(nombre)};
        sqLiteDatabase.delete(SQLConstants.TABLE_RECETAS, SQLConstants.WHERE_CLAUSE_NOMBRE, whereArgs);
    }


}
