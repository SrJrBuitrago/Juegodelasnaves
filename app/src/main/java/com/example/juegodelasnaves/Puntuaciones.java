package com.example.juegodelasnaves;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.juegodelasnaves.db.DbJugadores;

import java.util.ArrayList;
//Esta clase puntuaciones sirve para mostrar los resultados de los jugadores almacenados en una base
// de datos sqlite. Contiene un ArrayList<Jugador> jugadores y un AdaptadorJugador que es un
// adaptador personalizado y se aplica sobre el RecyclerView recyclerView. Hay un botón salir el
// cual con el método finish() nos manda al activity de donde venimos
public class Puntuaciones extends AppCompatActivity implements View.OnClickListener {
    Button bSalir;
    RecyclerView recyclerView;
    static ArrayList<Jugador> jugadores = new ArrayList<>();
    static AdaptadorJugador adaptadorJugador;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);
        bSalir = findViewById(R.id.bSalir);
        recyclerView = findViewById(R.id.RecyclerView);
        bSalir.setOnClickListener(this);
        DbJugadores dbJugadores = new DbJugadores(this);
        jugadores.removeAll(jugadores);
        dbJugadores.verJugador(jugadores);
        adaptadorJugador = new AdaptadorJugador(jugadores,this,R.layout.item_jugador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptadorJugador);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
