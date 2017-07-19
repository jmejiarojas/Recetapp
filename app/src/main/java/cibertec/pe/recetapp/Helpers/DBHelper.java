package cibertec.pe.recetapp.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USUARIO on 14/07/2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static  final int DB_VERSION = 1;

    public DBHelper(Context context){
       super(context, SQLConstants.DATABASE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLConstants.SQL_CREATE_TABLE_RECETAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizar a una nueva version
        db.execSQL(SQLConstants.SQL_DROP_TABLE);
        //Luego tenemos que crear la nueva BD
        onCreate(db);
    }
}
