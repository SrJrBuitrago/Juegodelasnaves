package com.example.juegodelasnaves;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.juegodelasnaves.db.DbJugadores;

import java.util.ArrayList;
import java.util.Random;

public class Motor extends SurfaceView implements Runnable {
    volatile boolean isPlaying;
    private int tiempoColision;
    static final int TIEMPO_MAX_COLISION=35;
    Thread juegoHilo = null;
    Context context;
    int score = 0;
    int anchoScreen, altoScreen;
    private int tiempoMaxJuego;
    private int contadorTiempoJuego;
    ArrayList<GameObject> objetosJuego;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    SoundPool soundPool;
    int idExplosion;
    boolean isGameOver;
    boolean isColision;
    static Jugador jugador;




    public Motor(Context context, int x, int y, int tiempoJuego) {
        super(context);
        this.context = context;
        anchoScreen = x;
        altoScreen = y;
        surfaceHolder = getHolder();
        paint = new Paint();
        objetosJuego = new ArrayList<GameObject>();
        objetosJuego.add(new NaveJugador(context,anchoScreen,altoScreen));
        objetosJuego.add(new NaveMarciana(context, new Random().nextInt(anchoScreen), 100, 10, anchoScreen));
        tiempoMaxJuego = tiempoJuego;
        contadorTiempoJuego = tiempoJuego;
        isPlaying = true;
        isColision = false;
        isGameOver=false;
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            soundPool =( new SoundPool.Builder()).setMaxStreams(5).build();}
        else{
                    new SoundPool(5, AudioManager.STREAM_MUSIC, 0);}
            idExplosion = soundPool.load(context, R.raw.impacto, 0);

    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        --contadorTiempoJuego;
        if (contadorTiempoJuego == 0) {
            isPlaying = false;
            isGameOver=true;
            Jugador jugador = new Jugador();
            jugador.setNombreJugador(MainActivity.nJugador);
            jugador.setPuntuacionJugador(score);
            DbJugadores dbJugadores = new DbJugadores(context);
            long id = dbJugadores.insertarJugador(jugador);

        } else {
            if (isColision) {
                tiempoColision -= 1;
                if ( tiempoColision == 0){
                    isColision = false;
                    objetosJuego.remove(1);
                    objetosJuego.add(1, new NaveMarciana(context, new Random().nextInt(800)
                            , 100, 50, anchoScreen));
                }
            }
            else {
                if (objetosJuego.size()>2){
                    if (Rect.intersects(((Misil)objetosJuego.get(2)).getColision(),((NaveMarciana)objetosJuego
                    .get(1)).getColision())){
                        int posXcolision,posYcolision, numObjetos;
                        ++score;
                        isColision=true;
                        soundPool.play(idExplosion,1,1,0,0,1);
                        tiempoColision = TIEMPO_MAX_COLISION;
                        contadorTiempoJuego=tiempoMaxJuego;
                        posXcolision=objetosJuego.get(1).getX();
                        posYcolision=objetosJuego.get(1).getY();
                        numObjetos=objetosJuego.size();
                        for (int i= 1; i<numObjetos; i++){
                            objetosJuego.remove(1);
                        }
                        objetosJuego.add(new Explosion(context,posXcolision,posYcolision));
                    }else if(objetosJuego.get(2).getY()<0){
                        objetosJuego.remove(2);
                    }
                }
                for (GameObject objetoJuego : objetosJuego) {
                    objetoJuego.update();
                }
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);
            paint.setTextSize(80);
            canvas.drawText(Integer.toString(contadorTiempoJuego),70,70,paint);
            canvas.drawText( "Puntos:" + score,250,70,paint);
            canvas.drawText(MainActivity.nJugador ,625,70,paint);

            synchronized (objetosJuego) {
                for (GameObject objetoJuego : objetosJuego) {
                    canvas.drawBitmap(objetoJuego.getBitmap(),
                            objetoJuego.getX(), objetoJuego.getY(),
                            paint);
                }
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            juegoHilo.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        isPlaying = false;
        try {
            juegoHilo.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        isPlaying = true;
        isGameOver=false;
        juegoHilo = new Thread(this);
        juegoHilo.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        NaveJugador naveJugador;
        synchronized (objetosJuego) {
            naveJugador = (NaveJugador) objetosJuego.get(0);
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case (MotionEvent.ACTION_DOWN):
                    if ((motionEvent.getY() > naveJugador.getY()) && (motionEvent.getX() >=
                            naveJugador.getX()) && (motionEvent.getX() <= (naveJugador.getX() + naveJugador.getAncho()))) {
                    naveJugador.setSeleccionado(true);
                }
                break;
                case (MotionEvent.ACTION_MOVE):
                    if (naveJugador.isSeleccionado()) naveJugador.setX((int) motionEvent.getX());
                    break;
                case (MotionEvent.ACTION_UP):
                    if (naveJugador.isSeleccionado()) {
                        naveJugador.setSeleccionado(false);
                        if (!isColision){
                            Misil misil = new Misil(context);
                        misil.setX(naveJugador.getX() + naveJugador.getAncho() / 2);
                        misil.setY(naveJugador.getY() - misil.getAlto());
                        misil.setVelocidad(35);
                        objetosJuego.add(misil);
                    }
                }
                    break;

            }
            return true;
        }
    }
    public boolean isGameOver(){
        return isGameOver;
    }
}
