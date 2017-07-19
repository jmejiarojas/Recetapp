package cibertec.pe.recetapp.Entities;

import android.content.ContentValues;

import java.io.Serializable;

import cibertec.pe.recetapp.Helpers.SQLConstants;

/**
 * Created by USUARIO on 13/07/2017.
 */

public class Receta implements Serializable{

    private String id;
    private String nombre;
    private int numPersonas;
    private String descripcion;
    private String preparacion;
    private String image;
    private int favorito;

    public Receta() {
    }

    public Receta(String id, String nombre, int numPersonas, String descripcion, String preparacion, String image, int favorito) {
        this.id = id;
        this.nombre = nombre;
        this.numPersonas = numPersonas;
        this.descripcion = descripcion;
        this.preparacion = preparacion;
        this.image = image;
        this.favorito = favorito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    //Content Values

    public ContentValues toValues(){
        ContentValues contentValues = new ContentValues(7);
        contentValues.put(SQLConstants.COLUMN_ID, id);
        contentValues.put(SQLConstants.COLUMN_NOMBRE, nombre);
        contentValues.put(SQLConstants.COLUMN_DESCRIPCION, descripcion);
        contentValues.put(SQLConstants.COLUMN_PREPARACION, preparacion);
        contentValues.put(SQLConstants.COLUMN_PERSONAS, numPersonas);
        contentValues.put(SQLConstants.COLUMN_IMAGEN, image);
        contentValues.put(SQLConstants.COLUMN_FAV, favorito);

        return contentValues;
    }
}
