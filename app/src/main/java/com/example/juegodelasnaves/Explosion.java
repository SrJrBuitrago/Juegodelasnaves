package com.example.juegodelasnaves;

import android.content.Context;
//Esta clase solo sirve para visualizar la foto de la explosión
public class Explosion extends GameObject{

    public Explosion (Context context, int x, int y){
        super(context,x,y, R.drawable.explosion);
    }
    @Override
    public void update() {

    }
}
