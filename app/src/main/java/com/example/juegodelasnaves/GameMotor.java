package com.example.juegodelasnaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
//Viene a ser la activity del juego, obtiene los datos de la Clase Motor y con el método
// setSystemUiVisibility ponemos la pantalla en modo envolvente fijo ocultando la barra superior
public class GameMotor extends AppCompatActivity {
    private Motor motor;
    final int tiempoDeJuegoMax =200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_motor);
        Display display = getWindowManager().getDefaultDisplay();
        Point tamanyo = new Point();
        display.getSize(tamanyo);
        motor = new Motor(this, tamanyo.x, tamanyo.y, tiempoDeJuegoMax);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN

        );
        setContentView(motor);

    }
    public void onBackPressed(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        motor.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        motor.resume();
    }
}