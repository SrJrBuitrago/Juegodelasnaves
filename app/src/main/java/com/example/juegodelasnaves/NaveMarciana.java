package com.example.juegodelasnaves;

import android.content.Context;
import android.graphics.Rect;

import java.util.Random;
//Clase NaveMarciana tiene diferentes atributos como maximoX que marca el punto máximo de la
// posición x sobre el que va a poder posicionarse, un retardo y una espera, para que se lleve acabo
// el movimiento y un Rect para detectar colisiones
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
//Aquí lo que hago es que la nave marciana actualice su posición, para ello genero un número random
// y le digo que mientras que la posición random sea mayor o igual que la actual le sume 1 a la actual
    @Override
    public void update() {

        if (esperar==retardo){

            Random random = new Random();
            int posicion = random.nextInt(maxX - getBitmap().getWidth());

            do {
                setX(+1);
            }while (getX()>=posicion);

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
