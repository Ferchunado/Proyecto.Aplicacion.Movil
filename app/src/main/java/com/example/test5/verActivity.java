package com.example.test5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.test5.db.DbTareas;
import com.example.test5.entidades.Tareas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class verActivity extends AppCompatActivity {

    EditText txtTitulo, txtFecha, txtPrioridad, txtResponsable, txtDescripcion;
    Button btnGuarda;
    Button volverMenu;
    FloatingActionButton fabEditar, fabEliminar;

    Tareas tareas;

    int id = 0;

    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        volverMenu = findViewById(R.id.volver);
        txtTitulo = findViewById(R.id.editarTarea_Titulo);
        txtFecha = findViewById(R.id.editarTarea_Fecha);
        txtPrioridad = findViewById(R.id.editarTarea_Prioridad);
        txtResponsable = findViewById(R.id.editarTarea_Responsable);
        txtDescripcion = findViewById(R.id.editarTarea_Descripcion);
        btnGuarda = findViewById(R.id.editarTarea_boton_actualizar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fbDelete);

        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatePickerDialog
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(verActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechaSeleccionada = dayOfMonth + "/" + (month+1) + "/" + year;
                        txtFecha.setText(fechaSeleccionada);
                    }
                },year,month,day);
                datePickerDialog.show();
            }


        });

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();

            if (extras == null) {
                id = Integer.parseInt(null);

            } else{
                id= extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbTareas dbTareas = new DbTareas(verActivity.this);
        tareas = dbTareas.verTareas(id);

        if(tareas != null){
            txtTitulo.setText(tareas.getTitulo_tarea());
            txtFecha.setText(tareas.getFecha_limite());
            txtPrioridad.setText(tareas.getPrioridad());
            txtResponsable.setText(tareas.getResponsable());
            txtDescripcion.setText(tareas.getDescripcion());
            btnGuarda.setVisibility(View.INVISIBLE);

            txtTitulo.setInputType(InputType.TYPE_NULL);
            txtFecha.setInputType(InputType.TYPE_NULL);
            txtPrioridad.setInputType(InputType.TYPE_NULL);
            txtResponsable.setInputType(InputType.TYPE_NULL);
            txtDescripcion.setInputType(InputType.TYPE_NULL);

        }

        volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(verActivity.this, EditarTarea.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(verActivity.this);
                builder.setMessage("¿Desea eliminar esta tarea?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if (dbTareas.eliminarTarea(id)){
                                    menuprincipal();
                                }
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }

    private void menuprincipal(){
        Intent intent   = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}