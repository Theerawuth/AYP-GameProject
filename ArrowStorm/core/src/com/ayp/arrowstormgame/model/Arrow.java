package com.ayp.arrowstormgame.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class Arrow {
    private Vector2 position;
    private float width = 32;
    private float height = 64;
    private float angle;
    private Vector2 velocity;
    private Rectangle boundingRectangle;

    public Arrow(float x, float y, float velocityX, float velocityY, float angle) {
        position = new Vector2(x, y);
        velocity = new Vector2(velocityX, velocityY);
        boundingRectangle = new Rectangle();
        this.angle = angle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public void setBoundingRectangle(Rectangle boundingRectangle) {
        this.boundingRectangle = boundingRectangle;
    }
}