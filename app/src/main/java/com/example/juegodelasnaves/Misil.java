package com.example.juegodelasnaves;

import android.content.Context;
import android.graphics.Rect;

import java.util.Random;
//Esta clase Misil tiene un atributo velocidad para definir la velocidad a la que se va a mover el
// misil, un Rect colision para detectar colisiones con la nave marciana, un booleano isColision que
// inicializo a false y se pone true cuando hay una colisión y una variable score para almacenar
//impactos
public class Misil extends GameObject{
    int velocidad;
    Rect colision;

    volatile boolean isColision;
    int score;
    public Misil(Context context, int x, int y, int velocidad){
        super(context,x,y,R.drawable.misil);
        this.velocidad= velocidad;
        isColision=false;
        colision=new Rect(x,y,x+getAncho(),y+getAlto());

    }
    public Misil(Context context){
        this(context,0,0,0);
    }


    @Override
    public void update() {

        setY(getY()-velocidad);
        colision.left=getX();
        colision.right = getX()+getAncho();
        colision.top = getY();
        colision.bottom = getY() + getAlto();
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    public int getAncho(){return getBitmap().getWidth();}
    public int getAlto(){return getBitmap().getHeight();}
    public Rect getColision(){
        return colision;
    }
}
