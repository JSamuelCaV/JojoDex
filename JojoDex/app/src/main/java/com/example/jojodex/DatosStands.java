package com.example.jojodex;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class DatosStands  {
    String stand;
    String ability;
    String image_stand;


    public DatosStands(JSONObject json){
        try{
            this.stand=json.getString("stand");
            this.ability=json.getString("ability");
            this.image_stand=json.getString("imagestand");
        }catch (JSONException error){
            error.printStackTrace();
        }
    }

    public String getStand(){
        return stand;
    }
    public String getAbility(){
        return ability;

    }
    public String getImage_stand(){
        return image_stand;
    }
}
// Se cogen los datos tanto las stands como la habilidad como de la imagen de los stands
