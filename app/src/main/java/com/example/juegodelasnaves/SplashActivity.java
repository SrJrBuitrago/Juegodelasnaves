package com.example.juegodelasnaves;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
//Esto es un activity que sirve de preloader, lo que hace es dormirse durante 2000ms y luego cambiar
// al activity main
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
