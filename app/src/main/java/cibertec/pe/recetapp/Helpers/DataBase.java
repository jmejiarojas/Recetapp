package cibertec.pe.recetapp.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
