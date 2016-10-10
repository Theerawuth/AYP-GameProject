package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public abstract class Enemy {

    private Circle enemyBound;
    private Vector2 position;
    private Vector2 boundPosition;

    public Enemy(float x, float y, float radius) {
        position = new Vector2(x, y);
        // to set origin point of bound at center of sprite when it has been drawn
        boundPosition = new Vector2(x + radius, y + radius);
        enemyBound = new Circle(boundPosition, radius);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void move(float delta) {
        // Moving of square sprite
        position.y += 100 * delta;
        // Moving of circle bounding
        boundPosition.y += 100 * delta;
        enemyBound.setPosition(boundPosition);
    }

    public Circle getEnemyBound() {
        return enemyBound;
    }
}