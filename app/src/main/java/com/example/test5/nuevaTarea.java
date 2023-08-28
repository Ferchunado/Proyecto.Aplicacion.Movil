package com.example.test5;

import static com.example.test5.db.DbHelper.TABLE_TAREAS;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.test5.db.DbHelper;
import com.example.test5.db.DbTareas;

import java.util.Calendar;

public class nuevaTarea extends AppCompatActivity {


    EditText titulo_et, fecha_limite_et,  prioridad_et, responsable_et;
    Button btnNueva_Tarea;
    EditText descripcion_et;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);

        titulo_et = findViewById(R.id.eT_Titulo_Tarea);
        fecha_limite_et = findViewById(R.id.eT_Fecha_Limite);
        prioridad_et = findViewById(R.id.eT_Prioridad);
        responsable_et = findViewById(R.id.eT_Responsable);
        descripcion_et = findViewById(R.id.eT_Descripcion);

        fecha_limite_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatePickerDialog
                Calendar calendar = Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(nuevaTarea.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String fechaSeleccionada = dayOfMonth + "/" + (month+1) + "/" + year;
                        fecha_limite_et.setText(fechaSeleccionada);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });




    }


    public void regresarHome (View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void registrarTarea (View view) {
        DbHelper admin = new DbHelper(this);
        SQLiteDatabase db = admin.getWritableDatabase();

        String titulo = titulo_et.getText().toString();
        String fecha_limite = fecha_limite_et.getText().toString();
        String prioridad = prioridad_et.getText().toString();
        String responsable = responsable_et.getText().toString();
        String descripcion= descripcion_et.getText().toString();
        
        if (!titulo.isEmpty() && !fecha_limite.isEmpty() && !prioridad.isEmpty() && !responsable.isEmpty() && !descripcion.isEmpty()){
            ContentValues tarea = new ContentValues();
            
            tarea.put("titulo_Tarea",titulo);
            tarea.put("descripcion",descripcion);
            tarea.put("fecha_limite",fecha_limite);
            tarea.put("prioridad",prioridad);
            tarea.put("responsable",responsable);
            tarea.put("descripcion", descripcion);
            
            db.insert(TABLE_TAREAS,null,tarea);
            
            db.close();
            limpiar();

            Toast.makeText(this, "Tarea Agregada", Toast.LENGTH_SHORT).show();
            
        } else {
            Toast.makeText(this, "Favor de rellenar todos los datos", Toast.LENGTH_SHORT).show();
        }

        
    }

    private void limpiar (){
        titulo_et.setText("");
        descripcion_et.setText("");
        fecha_limite_et.setText("");
        prioridad_et.setText("");
        responsable_et.setText("");
        descripcion_et.setText("");
    }
}