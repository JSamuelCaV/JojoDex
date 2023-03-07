package com.example.jojodex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PersonajesViewHolder extends RecyclerView.ViewHolder {
    private final TextView textPersonajes;
    private final View principal;

    public PersonajesViewHolder(@NonNull View itemView) {
        super(itemView);
        textPersonajes= itemView.findViewById(R.id.textView);
        principal=itemView.findViewById(R.id.principal);
    }
    public void enseñarDatos(@NonNull DatosPersonajes datos , Activity activity){
        textPersonajes.setText(datos.getNombre());


        principal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,PersonajesActivity.class);
                intent.putExtra("name",String.valueOf(datos.getNombre()));
                intent.putExtra("descripcion",String.valueOf(datos.getDescripcion()));
                intent.putExtra("image_character",String.valueOf(datos.getImage_character()));
                activity.startActivity(intent);
            }
        });
}
    // Se guarda el valor que se ha seleccionado en el Recycler para pasarlo  la actividad PersonajesActivity

}