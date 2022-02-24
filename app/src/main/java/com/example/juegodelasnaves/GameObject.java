package com.example.juegodelasnaves;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class GameObject {
    private Bitmap bitmap;
    private int x,y;


    public GameObject(Context context, int x, int y, int id){
        this.x = x;
        this.y = y;
        bitmap = BitmapFactory.decodeResource(context.getResources(),id);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    abstract public void update();
}
