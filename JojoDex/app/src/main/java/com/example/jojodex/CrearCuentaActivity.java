package com.example.jojodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CrearCuentaActivity extends AppCompatActivity {

    private EditText usuario, contraseña, confirmar_contraseña;
// Se crean las variables necesarias para que se pueda hacer una cuenta en la app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        final Button boton_registro=findViewById(R.id.boton_registro);

        boton_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String susuario,scontraseña,sconfirmar_contraseña;
                usuario=findViewById(R.id.usuario);
                susuario=usuario.getText().toString();
                if(susuario.matches("")){
                    Toast.makeText(view.getContext(),"Necesitas añadir el nombre de usuario dentro de la casilla",Toast.LENGTH_SHORT).show();
                //Se pasa a string el valor usuario dentro que introduce el del EditText comprobando que no esté vacia
                }
                contraseña=findViewById(R.id.contraseña);
                scontraseña=contraseña.getText().toString();
                if(scontraseña.matches("")){
                    Toast.makeText(view.getContext(),"Necesitas añadir la contraseña dentro de la casilla",Toast.LENGTH_SHORT).show();
                    //Se pasa a string el valor contraseña dentro que introduce el del EditText comprobando que no esté vacia
                }
                confirmar_contraseña=findViewById(R.id.confirmar_contraseña);
                sconfirmar_contraseña=confirmar_contraseña.getText().toString();

                if(sconfirmar_contraseña.matches("")){
                    Toast.makeText(view.getContext(),"Necesitas añadir la confirmacion de la contraseña dentro de la casilla",Toast.LENGTH_SHORT).show();
                    //Se pasa a string el valor contraseña dentro que introduce el del EditText comprobando que no esté vacia

                }
                if (!scontraseña.equals(sconfirmar_contraseña)){
                    Toast.makeText(view.getContext(),"La contraseña y la confirmación de la misma tienen que ser iguales",Toast.LENGTH_SHORT).show();
                // Se compara la contraseña con las confirmación de la misma para saber si son iguales y si lo son no mostraría el toast
                }
                if (susuario!="" && scontraseña!="" && sconfirmar_contraseña!="" && scontraseña.equals(sconfirmar_contraseña)){
                    crearUsuario(view,susuario,scontraseña);
                    // Se confirma que todo está correcto y si es el caso se procede a avanzar al método crearUsuario
                }
            }
        });
    }
    public void crearUsuario(View view,String susuario,String scontraseña){
        RequestQueue queue= Volley.newRequestQueue(this);
        JSONObject jsonObject=new JSONObject();
        try{
            jsonObject.put("username",susuario);
            jsonObject.put("password",scontraseña);
        }catch(JSONException e){
            e.printStackTrace();
            //se añaden los datos de usuario y de contraseña para pasarlos al mockapi
        }
        String url="https://63e9e3c7811db3d7ef02768b.mockapi.io/usersData";
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.POST,
                url, jsonObject, new Response.Listener<JSONObject>() {
                    // Se proporciona la información para introducirla con un POST en el mockapi
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(view.getContext(), "Creando usuario", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                //si funciona correctamente se incluira por pantalla un toast e ira a la pantalla de login
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(),"Fallo al crear usuario"+error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("Error: ",error.toString());
                    }
                });
        //En caso contrario se incluye un toast pero no cambia de pantalla debido al error
        queue.add(jsonObjectRequest);
    }
}
