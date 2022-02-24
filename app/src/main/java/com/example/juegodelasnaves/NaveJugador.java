package com.example.juegodelasnaves;

import android.content.Context;

public class NaveJugador extends GameObject{
    int x_Max;
    boolean seleccionado;
    public NaveJugador(Context context, int anchoScreen, int altoScreen){
        super(context, anchoScreen/2, altoScreen,R.drawable.nave);
        x_Max=anchoScreen;
        setY(altoScreen-getAlto());
    }

    @Override
    public void setX(int x){
        if ((x<=(x_Max-getAncho())) && (x>=0)){
            super.setX(x);
        }
    }

    public int getAncho(){return getBitmap().getWidth();}
    public int getAlto(){return getBitmap().getHeight();}
    @Override
    public void update() {

    }
    public boolean isSeleccionado(){
        return seleccionado;
    }
    public void setSeleccionado(boolean seleccionado){
        this.seleccionado=seleccionado;
    }
}
