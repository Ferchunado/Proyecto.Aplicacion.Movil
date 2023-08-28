package com.example.test5.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test5.R;
import com.example.test5.entidades.Tareas;
import com.example.test5.verActivity;

import java.util.ArrayList;

public class lista_tareas_adapter extends RecyclerView.Adapter<lista_tareas_adapter.TareasviewHelper> {

    ArrayList<Tareas> listaTareas;

    public lista_tareas_adapter(ArrayList<Tareas> listaTareas){
        this.listaTareas = listaTareas;
    }
    @NonNull
    @Override
    public lista_tareas_adapter.TareasviewHelper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_actividad, null, false);

        return new TareasviewHelper(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lista_tareas_adapter.TareasviewHelper holder, int position) {
        /*ASiganamos los elementos para cada opcion*/
        holder.viewtitulo_Tarea.setText(listaTareas.get(position).getTitulo_tarea());
        holder.viewFechaLimite.setText(listaTareas.get(position).getFecha_limite());
        holder.viewPrioridad.setText(listaTareas.get(position).getPrioridad());
        holder.viewResponsable.setText(listaTareas.get(position).getResponsable());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class TareasviewHelper extends RecyclerView.ViewHolder {
        TextView viewtitulo_Tarea, viewFechaLimite, viewPrioridad, viewResponsable;

        public TareasviewHelper(@NonNull View itemView) {
            super(itemView);
            viewtitulo_Tarea = itemView.findViewById(R.id.viewtitulo_Tarea);
            viewFechaLimite = itemView.findViewById(R.id.viewFechaLimite);
            viewPrioridad = itemView.findViewById(R.id.viewPrioridad);
            viewResponsable = itemView.findViewById(R.id.viewResponsable);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, verActivity.class);
                    intent.putExtra("ID", listaTareas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
