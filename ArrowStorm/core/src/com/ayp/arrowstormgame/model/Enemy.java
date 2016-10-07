package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public abstract class Enemy {

    private Circle enemyBound;
    private Vector2 position;
    private float radius;

    public Enemy(float x, float y, float radius) {
        position = new Vector2(x, y);
        this.radius = radius;
        enemyBound = new Circle(position, radius);
    }

    public Vector2 getPosition() {
        return position;
    }


    public void move(float delta) {
        position.y += 100 * delta;
        enemyBound.setPosition(position);
    }

    public Circle getEnemyBound() {
        return enemyBound;
    }
}
