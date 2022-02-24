package com.example.juegodelasnaves;

import android.content.Context;
import android.graphics.Rect;

import java.util.Random;

public class NaveMarciana extends GameObject{
    int maxX;
    int retardo;
    int esperar;
    Rect colision;

    public NaveMarciana(Context context, int x, int y, int retardo, int maximoX){
        super(context,x,y,R.drawable.ovni);
        this.retardo=retardo;
        esperar=0;

        maxX = maximoX;
        colision= new Rect(x,y,x+getAncho(),y+getAlto());
    }

    @Override
    public void update() {
        ++esperar;
        if (esperar==retardo){
            Random random = new Random();
            setX(random.nextInt(maxX-getBitmap().getWidth()));
            esperar=0;
        }else esperar+=1;
        colision.left=getX();
        colision.top=getY();
        colision.right=getX()+getBitmap().getWidth();
        colision.bottom=getY()+getBitmap().getHeight();
    }
    public Rect getColision(){
        return colision;
    }
    public int getAncho(){
        return getBitmap().getWidth();
    }
    public int getAlto(){
        return getBitmap().getHeight();
    }
}
