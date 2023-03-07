package com.example.jojodex;

import org.json.JSONException;
import org.json.JSONObject;

public class DatosPersonajes {
    String nombre;
    String descripcion;
    String image_character;


    public DatosPersonajes(JSONObject json){
        try{
            this.nombre=json.getString("name");
            this.descripcion=json.getString("description");
            this.image_character=json.getString("imagecharacter");
        }catch (JSONException error){
            error.printStackTrace();
        }
    }

    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;

    }
    public String getImage_character(){
        return image_character;
    }
}
// Se cogen los datos tanto de nombre como de descripcion como de la imagen de los personajes