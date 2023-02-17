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
    private Button login;

    private Button registro;
    private Context context= this;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("Jojo", MODE_PRIVATE);
        String userLogged = preferences.getString("USUARIO_VALIDO", "");
        int userId = preferences.getInt("Id_usuario", -1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.usuario);
        password = findViewById( R.id.contraseña);
        login = findViewById(R.id.login);
        registro=findViewById(R.id.resgistro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(context, CrearCuentaActivity.class);
                context.startActivity(I);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginUsuario();}
        });
        queue = Volley.newRequestQueue(this);
    }

    private void loginUsuario() {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://63e9e3c7811db3d7ef02768b.mockapi.io/usersData?username="+username.getText().toString(),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int token;
                        String contraseña;
                        try {
                            token = response.getJSONObject(0).getInt("id");
                            contraseña = response.getJSONObject(0).getString("password");
                            Toast.makeText(context,contraseña,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        if (password.getText().toString().equals(contraseña)){
                            Toast.makeText(context, "Token: " + token, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, CrearCuentaActivity.class);
                            context.startActivity(intent);
                            SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("VALID_USERNAME", username.getText().toString());
                            editor.putInt("userId", token);
                            editor.apply();
                            finish();
                        }else {
                            Toast.makeText(context,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        }

                    }

                },
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