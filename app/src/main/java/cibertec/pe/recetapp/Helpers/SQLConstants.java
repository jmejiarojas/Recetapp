package cibertec.pe.recetapp.Helpers;

/**
 * Created by USUARIO on 14/07/2017.
 */

public class SQLConstants {

    //Estamos haciendo referencia a la BD
    public static final String DATABASE = "db_recetas.db";

    //Estamos haciendo referencia a las tablas
    public static final String TABLE_RECETAS = "recetas";

    //COLUMNS
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String  COLUMN_PERSONAS = "numPersonas";
    public static final String  COLUMN_DESCRIPCION = "descripcion";
    public static final String  COLUMN_PREPARACION = "preparacion";
    public static final String  COLUMN_IMAGEN = "imagen";
    public static final String  COLUMN_FAV = "favorito";


    //QUERY

    public static final String SQL_CREATE_TABLE_RECETAS =
            "CREATE TABLE " + TABLE_RECETAS + "("+
            COLUMN_ID + " TEXT PRIMARY KEY," +
            COLUMN_NOMBRE + " TEXT," +
            COLUMN_PERSONAS + " INT," +
            COLUMN_DESCRIPCION + " TEXT,"+
            COLUMN_PREPARACION + " TEXT,"+
            COLUMN_IMAGEN + " TEXT,"+
            COLUMN_FAV + " INT" +
            ");";

    public static final String SQL_DROP_TABLE = "DROP TABLE " + TABLE_RECETAS;

    public static final String[] ALL_COLUMNS = {
      COLUMN_ID,
            COLUMN_NOMBRE,
            COLUMN_PERSONAS,
            COLUMN_DESCRIPCION,
            COLUMN_PREPARACION,
            COLUMN_IMAGEN,
            COLUMN_FAV
    };

    public static final String WHERE_CLAUSE_NOMBRE = COLUMN_NOMBRE + "=?";
    public static final String  WHERE_CLAUSE_FAVORITOS = COLUMN_FAV + "=?";
    public static final String  WHERE_CLAUSE_PERSONAS= COLUMN_PERSONAS + "=?";

}
