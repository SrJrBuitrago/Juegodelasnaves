package com.example.juegodelasnaves.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.juegodelasnaves.Jugador;

import java.util.ArrayList;

public class DbJugadores extends DbHelper {

    Context context; // Definimos el context

    // Método constructor y asignamos el context
    public DbJugadores(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    //Metodo para insertar valores en la bbdd
    public long insertarJugador(Jugador jugador) {

        long idJugador = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Guardamos los los valores  de nuestro objeto Jugador
            ContentValues values = new ContentValues();
            values.put("nombreJugador", jugador.getNombreJugador());
            values.put("puntuacion", jugador.getPuntuacionJugador());
            // Insertamos los valores en la TABLE_JUGADORES y nos devuelve el id generado por cada
            //jugador
            idJugador = db.insert(TABLE_JUGADORES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return idJugador;
    }


   //Método para obtener un ArrayList<Jugador> jugadores con todos los valores de la TABLE_JUGADORES
    //obtenidos mediante una query
    public ArrayList<Jugador> verJugador(ArrayList<Jugador> jugadores) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Jugador jugador = null;
        Cursor cursorJugador;

        cursorJugador = db.rawQuery("SELECT * FROM " + TABLE_JUGADORES , null);

        if (cursorJugador.moveToFirst()) {
            do {
                jugador = new Jugador();
                jugador.setIdJugador(cursorJugador.getInt(0));
                jugador.setNombreJugador(cursorJugador.getString(1));
                jugador.setPuntuacionJugador(cursorJugador.getInt(2));
                jugadores.add(jugador);
            }while (cursorJugador.moveToNext());
        }

        cursorJugador.close();

        return jugadores;
    }


}

