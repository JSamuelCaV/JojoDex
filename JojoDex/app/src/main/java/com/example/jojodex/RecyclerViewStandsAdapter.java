package com.example.jojodex;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewStandsAdapter extends RecyclerView.Adapter<StandsViewHolder> {
    Activity activity;

    private List<DatosStands> todosLosStands;
    public RecyclerViewStandsAdapter(List<DatosStands> todosLosStands, Activity activity){
        this.todosLosStands=todosLosStands;
        this.activity=activity;
    }

    @NonNull
    @Override
    public StandsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_stands_view_holder,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new StandsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StandsViewHolder holder, int position) {
    DatosStands dataPosition= todosLosStands.get(position);
    holder.enseñarStands(dataPosition,activity);

    }

    @Override
    public int getItemCount() {
        return todosLosStands.size();
    }
}
