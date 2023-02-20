package com.example.jojodex;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class StandsViewHolder extends RecyclerView.ViewHolder {
    private final TextView textStands;
    private final View principal;

    public StandsViewHolder(@NonNull View itemView) {
        super(itemView);
        textStands = itemView.findViewById(R.id.textView2);
        principal = itemView.findViewById(R.id.principal);
    }

    public void enseñarStands(@NonNull DatosStands stands, Activity activity) {
        textStands.setText(stands.getStand());
        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, StandsActivity.class);
                intent.putExtra("stand", String.valueOf(stands.getStand()));
                intent.putExtra("ability", String.valueOf(stands.getAbility()));
                intent.putExtra("image_stands", String.valueOf(stands.getImage_stand()));
                activity.startActivity(intent);

            }
        });
    }
}