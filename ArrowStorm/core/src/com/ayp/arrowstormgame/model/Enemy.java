package com.ayp.arrowstormgame.model;


import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */

public abstract class Enemy {

    public static final float ENEMY_SIZE = 64;
    private static final float RADIUS = 32;
    private float attackDamage;
    private float movementSpeed;
    private float healthPoint;
    private static final int BASE_ATTACK_DAMAGE = 2;
    private static final int MINIMUM_ATTACK_DAMAGE = 2;
    private static final int MAXIMUM_ATTACK_DAMAGE = 45;
    private int attackDamageRange = MAXIMUM_ATTACK_DAMAGE - MINIMUM_ATTACK_DAMAGE;

    private static final int BASE_MOVEMENT_SPEED = 80;
    private static final int MINIMUM_MOVEMENT_SPEED = 80;
    private static final int MAXIMUM_MOVEMENT_SPEED = 240;
    private int movementSpeedRange = MAXIMUM_MOVEMENT_SPEED - MINIMUM_MOVEMENT_SPEED;

    private static final int BASE_HEALTH_POINT = 8;
    private static final int MINIMUM_HEALTH_POINT = 8;
    private static final int MAXIMUM_HEALTH_POINT = 245;
    private int healthPointRange = MAXIMUM_HEALTH_POINT - MINIMUM_HEALTH_POINT;

    private int level = 1;
    private static final int MAX_LEVEL = 20;
    private Circle enemyBound;
    private Vector2 position;
    private Vector2 boundPosition;

    public Enemy(
            float x,
            float y,
            float factorAttackDamage,
            float factorMovementSpeed,
            float factorHealthPoint
    ) {
        position = new Vector2(x, y);
        // to set origin point of bound at center of sprite when it has been drawn
        boundPosition = new Vector2(x + RADIUS, y + RADIUS);
        enemyBound = new Circle(boundPosition, RADIUS);

        attackDamage = BASE_ATTACK_DAMAGE + (level * (attackDamageRange / MAX_LEVEL));
        attackDamage *= factorAttackDamage;

        movementSpeed = BASE_MOVEMENT_SPEED + (level * (movementSpeedRange / MAX_LEVEL));
        movementSpeed *= factorMovementSpeed;

        healthPoint = BASE_HEALTH_POINT + (level * (healthPointRange / MAX_LEVEL));
        healthPoint *= factorHealthPoint;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public float getHealthPoint() {
        return healthPoint;
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