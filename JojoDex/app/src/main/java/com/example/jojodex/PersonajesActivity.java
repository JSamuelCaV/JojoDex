package com.example.jojodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class PersonajesActivity extends AppCompatActivity {

    String nombre,descripcion,image_character;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes);

        TextView textView = findViewById(R.id.nombre);
        TextView textView2 = findViewById(R.id.descripcion);
        ImageView image = findViewById(R.id.image_personaje);

        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();
        nombre= (String) bundle.get("name");
        textView.setText(nombre);

        descripcion=(String)bundle.get("descripcion");
        textView2.setText(descripcion);

        image_character=(String)bundle.get("image_character");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmapFromURL(image_character);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setImageBitmap(bitmap);
                    }
                });

            }
        });
        thread.start();



    }

    private Bitmap getBitmapFromURL(String card_image){


        try {
            URL url = new URL(card_image);
            URLConnection connection = url.openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap image = BitmapFactory.decodeStream(input);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}