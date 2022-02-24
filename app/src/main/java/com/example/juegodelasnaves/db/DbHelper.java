package com.example.juegodelasnaves.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/* Esta clase es una clase "ayudante" que nos proporcionará los elementos necesarios para crear
   nuestras bases de datos y las tablas */
public class DbHelper extends SQLiteOpenHelper {

    // Definimos las variables que nos ayudarán a establecer los parámetros de nuestra base de datos
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "b_juegomarcianos.db";
    public static final String TABLE_JUGADORES = "t_jugadores";


    // Método constructor para la base de datos
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    // Método que se ejecutará al momento de llamar a esta clase para crear la base de datos y tablas
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_JUGADORES + "(" +
                "idJugador INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreJugador TEXT NOT NULL," +
                "puntuacion INTEGER NOT NULL)");


    }

    // Método que se ejecutará cuando cambie la versión de la base de datos (cuando hagamos algún cambio)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_JUGADORES); onCreate(sqLiteDatabase);

    }
}
