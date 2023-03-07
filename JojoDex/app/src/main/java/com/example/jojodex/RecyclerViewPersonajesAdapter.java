package com.example.jojodex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerViewPersonajesAdapter extends RecyclerView.Adapter<PersonajesViewHolder> {

    private Activity activity;

    private List<DatosPersonajes> todosLosPersonajes;
    public RecyclerViewPersonajesAdapter(List<DatosPersonajes> todosLosPersonajes,Activity activity){
        this.todosLosPersonajes=todosLosPersonajes;
        this.activity=activity;
        //Se hace un array con los DatosPersonajes
    }

    @NonNull
    @Override
    public PersonajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_personajes_view_holder,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new PersonajesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PersonajesViewHolder holder, int position) {
        DatosPersonajes datosPosicion= todosLosPersonajes.get(position);
        holder.enseñarDatos(datosPosicion,activity);
        //Se pasa el array de datos del viewHolder con la posicion del array que se ha seleccionado
    }

    @Override
    public int getItemCount() {
        return todosLosPersonajes.size();
    }
    //Da la cantidad de Personajes que hay registrados en el FragmentPersonajesJojo
}