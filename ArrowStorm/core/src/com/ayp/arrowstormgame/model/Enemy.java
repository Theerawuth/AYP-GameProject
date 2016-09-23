package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public abstract class Enemy {
    private Vector2 mPosition;
    private int mWidth;
    private int mHeight;

    public Enemy(float x, float y, int width, int height) {
        mWidth = width;
        mHeight = height;
        mPosition = new Vector2(x, y);
    }
}
