package com.example.juegodelasnaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
//En el MainActivity lo que hacemos es iniciar los componentes, reproducir un sonido el cual solo
// se va a detener o bien si se acaba este o bien si cambiamos a la activity del juego o salimos de
// la aplicación, si cambiamos a la activity puntuaciones se sigue reproduciendo. Además tenemos un
//onClickListener que dependiendo del botón que pulses te lleva a un activity o a otro.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button inicioJuego, verPuntuaciones, salir;
    SoundPool soundPool;
    EditText editTextNombre;
    static String nJugador;
    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.juegodetronos1);
        inicioJuego = findViewById(R.id.BInicio);
        verPuntuaciones = findViewById(R.id.BVerPuntuaciones);
        editTextNombre = findViewById(R.id.eTNombre);
        salir = findViewById(R.id.BSalir);
        inicioJuego.setOnClickListener(this);
        verPuntuaciones.setOnClickListener(this);
        salir.setOnClickListener(this);


        mediaPlayer.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BInicio:
               nJugador = editTextNombre.getText().toString();
                Intent intent = new Intent(this,GameMotor.class);
                startActivity(intent);
                mediaPlayer.stop();
                finish();
                break;
            case R.id.BVerPuntuaciones:
                mostrarPuntuaciones();

                break;
            case R.id.BSalir:
                mediaPlayer.stop();
                finish();
                break;
        }
    }

    private void mostrarPuntuaciones() {
        Intent intent = new Intent(this, Puntuaciones.class);
        startActivity(intent);
    }

}