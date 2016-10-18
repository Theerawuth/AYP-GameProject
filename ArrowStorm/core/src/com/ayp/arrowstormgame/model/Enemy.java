package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.model.enemiespack.Bug;
import com.ayp.arrowstormgame.model.enemiespack.Guardian;
import com.ayp.arrowstormgame.model.enemiespack.Worm;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */

public abstract class Enemy {

    public static final float ENEMY_WIDTH = 64;
    public static final float ENEMY_HEIGHT = 64;
    private static final float RADIUS = 32;
    private float attackDamage;
    private float movementSpeed;
    private float healthPoint;
    private static final float BASE_ATTACK_DAMAGE = 2;
    private static final float MINIMUM_ATTACK_DAMAGE = 2;
    private static final float MAXIMUM_ATTACK_DAMAGE = 37;
    private float attackDamageRange = MAXIMUM_ATTACK_DAMAGE - MINIMUM_ATTACK_DAMAGE;

    private static final float BASE_MOVEMENT_SPEED = 70;
    private static final float MINIMUM_MOVEMENT_SPEED = 70;
    private static final float MAXIMUM_MOVEMENT_SPEED = 95;
    private float movementSpeedRange = MAXIMUM_MOVEMENT_SPEED - MINIMUM_MOVEMENT_SPEED;

    private static final float BASE_HEALTH_POINT = 8;
    private static final float MINIMUM_HEALTH_POINT = 8;
    private static final float MAXIMUM_HEALTH_POINT = 245;
    private float healthPointRange = MAXIMUM_HEALTH_POINT - MINIMUM_HEALTH_POINT;

    private static final int MAX_LEVEL = 20;
    private Circle enemyBound;
    private Vector2 position;
    private Vector2 boundPosition;

    private String type;
    private int score;

    public Enemy(
            float x,
            float y,
            float factorAttackDamage,
            float factorMovementSpeed,
            float factorHealthPoint,
            int level
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

    public boolean isDied() {
        return healthPoint <= 0;
    }

    public void takeDamage(float damage) {
        healthPoint -= damage;
    }

    public int getScore() {
        if (this instanceof Bug) {
            score = Bug.BOAR_SCORE;
        } else if (this instanceof Worm) {
            score = Worm.TIGER_SCORE;
        } else if (this instanceof Guardian) {
//            type = Guardian.TYPE;
        }
        return score;
    }

    public String getType() {
        if (this instanceof Bug) {
            type = Bug.TYPE;
        } else if (this instanceof Worm) {
            type = Worm.TYPE;
        } else if (this instanceof Guardian) {
            type = Guardian.TYPE;
        }
        return type;
    }

    public float getAttackDamage() {
        return attackDamage;
    }

    public float getHealthPoint() {
        return healthPoint;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void move(float delta) {
        // Moving of square sprite
        position.y += movementSpeed * delta;
        // Moving of circle bounding
        boundPosition.y += movementSpeed * delta;
        enemyBound.setPosition(boundPosition);
    }

    public Circle getEnemyBound() {
        return enemyBound;
    }
}