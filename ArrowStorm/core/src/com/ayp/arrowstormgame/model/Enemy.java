package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public abstract class Enemy {

    private Vector2 position;
    private float width;
    private float height;

    public Enemy(float x, float y, float width, float height) {
        position = new Vector2(x, y);
        this.height = height;
        this.width = width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void move(float delta) {
        position.y += 100 * delta;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
