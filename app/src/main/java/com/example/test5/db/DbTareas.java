package com.example.test5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.test5.entidades.Tareas;

import java.util.ArrayList;

public class DbTareas extends DbHelper{

    Context context;

    public DbTareas(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long agregarTarea (String titulo, String fecha_limite, String prioridad, String responsable, String descripcion){

        long id = 0;

        try {

            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("titulo_Tarea",titulo);
            values.put("fecha_limite",fecha_limite);
            values.put("prioridad", prioridad);
            values.put("responsable", responsable);
            values.put("descripcion", descripcion);

            id = db.insert(TABLE_TAREAS, null, values);

        } catch (Exception e) {
            e.toString();
        }
        return id;
    }

    public ArrayList<Tareas> motsrarTareas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Tareas> listaTareas = new ArrayList<>();

        Tareas tarea = null;
        Cursor cursorTareas = null;

        cursorTareas = db.rawQuery("SELECT * FROM " + TABLE_TAREAS,null);

        if(cursorTareas.moveToFirst()){
            do{
                tarea = new Tareas();
                tarea.setId(cursorTareas.getInt(0));
                tarea.setTitulo_tarea(cursorTareas.getString(1));
                tarea.setFecha_limite(cursorTareas.getString(2));
                tarea.setPrioridad(cursorTareas.getString(3));
                tarea.setResponsable(cursorTareas.getString(4));

                listaTareas.add(tarea);

            } while (cursorTareas.moveToNext());
        }
        cursorTareas.close();
        return  listaTareas;
    }

    public Tareas verTareas(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();



        Tareas tarea = null;
        Cursor cursorTareas = null;

        cursorTareas = db.rawQuery("SELECT * FROM " + TABLE_TAREAS + " WHERE id = " + id + " LIMIT 1",null);

        if(cursorTareas.moveToFirst()){

                tarea = new Tareas();
                tarea.setId(cursorTareas.getInt(0));
                tarea.setTitulo_tarea(cursorTareas.getString(1));
                tarea.setFecha_limite(cursorTareas.getString(2));
                tarea.setPrioridad(cursorTareas.getString(3));
                tarea.setResponsable(cursorTareas.getString(4));
                tarea.setDescripcion(cursorTareas.getString(5));





        }
        cursorTareas.close();
        return  tarea;
    }


    public boolean actualizarTarea (int id, String titulo, String fecha_limite, String prioridad, String responsable, String descripcion){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE " + TABLE_TAREAS+ " SET titulo_Tarea = '"+ titulo+ "', fecha_limite = '"+ fecha_limite+ "', " +
                    "prioridad = '"+ prioridad+ "', responsable = '"+ responsable+ "', descripcion = '"+ descripcion+ "' WHERE id ='"+ id +"'");

            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
    public boolean eliminarTarea (int id){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM " + TABLE_TAREAS + " WHERE id= '"+ id +" '");
            correcto = true;
        } catch (Exception e) {
            e.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

}
