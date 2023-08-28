package com.example.test5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test5.db.DbTareas;
import com.example.test5.entidades.Tareas;

import java.util.Calendar;

public class EditarTarea extends AppCompatActivity {

    EditText txtTitulo, txtFecha, txtPrioridad, txtResponsable, txtDescripcion;
    Button btnGuarda, btnVolver;

    Tareas tareas;
    Boolean correcto = false;
    int id = 0;

    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtTitulo = findViewById(R.id.editarTarea_Titulo);
        txtFecha = findViewById(R.id.editarTarea_Fecha);
        txtPrioridad = findViewById(R.id.editarTarea_Prioridad);
        txtResponsable = findViewById(R.id.editarTarea_Responsable);
        txtDescripcion = findViewById(R.id.editarTarea_Descripcion);
        btnGuarda = findViewById(R.id.editarTarea_boton_actualizar);
        btnVolver = findViewById(R.id.volver);

        btnVolver.setVisibility(View.INVISIBLE);
        txtFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatePickerDialog
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(EditarTarea.this, new DatePickerDialog.OnDateSetListener() {
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
        DbTareas dbTareas = new DbTareas(EditarTarea.this);
        tareas = dbTareas.verTareas(id);

        if(tareas != null){
            txtTitulo.setText(tareas.getTitulo_tarea());
            txtFecha.setText(tareas.getFecha_limite());
            txtPrioridad.setText(tareas.getPrioridad());
            txtResponsable.setText(tareas.getResponsable());
            txtDescripcion.setText(tareas.getDescripcion());

        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtTitulo.getText().toString().equals("") && !txtFecha.getText().toString().equals("")
                && !txtPrioridad.getText().toString().equals("") && !txtResponsable.getText().toString().equals("")
                && !txtDescripcion.getText().toString().equals("")){
                    correcto =   dbTareas.actualizarTarea(id, txtTitulo.getText().toString(), txtFecha.getText().toString(),
                            txtPrioridad.getText().toString(), txtResponsable.getText().toString(), txtDescripcion.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarTarea.this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
                        verResgistro();
                    } else {
                        Toast.makeText(EditarTarea.this, "Error al modificar registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditarTarea.this, "Llena los campos porfavor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void verResgistro (){
        Intent intent = new Intent(this, verActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}