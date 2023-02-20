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

public class StandsActivity extends AppCompatActivity {
String stand,ability ,image_stands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stands);
        TextView textView=findViewById(R.id.stand);
        TextView textView1=findViewById(R.id.ability);
        ImageView image=findViewById(R.id.image_stand);
        Intent intent =getIntent();

        Bundle bundle=intent.getExtras();
        stand= (String) bundle.get("stand");
        textView.setText(stand);

        ability=(String)bundle.get("ability");
        textView1.setText(ability);
        image_stands=(String) bundle.get("image_stands");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bt = getBitmapFromURL(image_stands);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        image.setImageBitmap(bt);
                    }
                });

            }
        });
        thread.start();



    }

    private Bitmap getBitmapFromURL(String image_stands){


        try {
            URL url = new URL(image_stands);
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
