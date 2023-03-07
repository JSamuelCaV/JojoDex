package com.example.jojodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button boton;

    private Button registro;
    private Context context= this;
    private RequestQueue queue;

//Declaramos las variables que se necesitarán
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usuario);
        password = findViewById( R.id.contraseña);
        boton = findViewById(R.id.login);
        registro=findViewById(R.id.resgistro);
// Se asignan las variables a las creadas en el xml
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(context, CrearCuentaActivity.class);
                context.startActivity(I);
            }
        });
// En el momento en el que se pulsa el botón Registrate , cambiará de actividad a la de CrearCuentaActivity


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginUser();}
        });
        queue = Volley.newRequestQueue(this);
    }

    private void loginUser() {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://63e9e3c7811db3d7ef02768b.mockapi.io/usersData?username="+username.getText().toString(),
                null,
                //Aquí se pasa una petición GET para obtener el listado de usuarios con sus contraseñas
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int token;
                        String pw;
                        try {
                            token = response.getJSONObject(0).getInt("id");
                            pw = response.getJSONObject(0).getString("password");
                            Toast.makeText(context,pw,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        if (password.getText().toString().equals(pw)){
                            Toast.makeText(context, "Token: " + token, Toast.LENGTH_LONG).show();
                            Intent I = new Intent(context, NavigationDrawerActivity.class);
                            context.startActivity(I);

                        }else {
                            Toast.makeText(context,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        }

                    }

                },
                // Se establece el valor de usuario y obtiene el de lka contrasela comparandolo con el de la contraseña introducida y si son iguales pasaran a la pestaña del NavigationDrawer
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Código de respuesta: ", Toast.LENGTH_LONG).show();
                    }
                }
        );
        this.queue.add(request);
    }

}