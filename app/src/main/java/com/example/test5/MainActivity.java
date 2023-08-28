package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.test5.adaptadores.lista_tareas_adapter;
import com.example.test5.db.DbTareas;
import com.example.test5.entidades.Tareas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaTarea;
    ArrayList<Tareas> tareasArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaTarea = findViewById(R.id.lista_Tareas_view);
        listaTarea.setLayoutManager(new LinearLayoutManager(this));

        DbTareas dbTareas = new DbTareas(MainActivity.this);
         tareasArrayList = new ArrayList<>();

         lista_tareas_adapter adapter = new lista_tareas_adapter(dbTareas.motsrarTareas());
         listaTarea.setAdapter(adapter);

    }

    public  boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal_1,menu);

        return  true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.nueva_Tarea_Menu:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void nuevoRegistro(){

        //Intent para brincar de una actividad a otra
        Intent intent  = new Intent(this, nuevaTarea.class);
        startActivity(intent);
    }
}