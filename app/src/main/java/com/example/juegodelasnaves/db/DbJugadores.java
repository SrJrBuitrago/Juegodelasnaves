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

    /* Método utilizado en el "NuevoActivity" para insertar un producto en la tabla pasada
       como parámetro (TABLE_PRODUCTOS) */
    public long insertarJugador(Jugador jugador) {

        long idJugador = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Guardamos los valores (nombre,cantidad,categoria) que formarán nuestro producto
            ContentValues values = new ContentValues();
            values.put("nombreJugador", jugador.getNombreJugador());
            values.put("puntuacion", jugador.getPuntuacionJugador());
            /* Insertamos los valores guardados en el ContentValues a nuestra tabla y guardamos el id
               del producto generado por esta inserción en una variable tipo long */
            idJugador = db.insert(TABLE_JUGADORES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return idJugador;
    }


    /* Método para mostrar un producto y sus detalles de manera independiente en el "VerActivity",
       mediante un select individual a través del ID recibido con el método getExtras */
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

