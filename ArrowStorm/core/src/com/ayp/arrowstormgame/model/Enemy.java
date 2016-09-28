package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public abstract class Enemy {
    private Vector2 position;
    private int width;
    private int height;

    public Enemy(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);
    }
}
