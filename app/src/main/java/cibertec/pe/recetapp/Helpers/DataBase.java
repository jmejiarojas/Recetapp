package cibertec.pe.recetapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        if(items == 0)//Solo vamos a insertar los datos, si esta vacia la tabla
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
}
