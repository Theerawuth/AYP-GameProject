package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.game_manager.GamePlayManager;
import com.ayp.arrowstormgame.model.enemiespack.Bug;
import com.ayp.arrowstormgame.model.enemiespack.Guardian;
import com.ayp.arrowstormgame.model.enemiespack.Worm;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */

public abstract class Enemy {
    public static final float WIDTH = 64;
    public static final float HEIGHT = 64;
    private static final float ENEMY_BOUNDING_RADIUS = 32;
    private static final float BOSS_BOUNDING_RADIUS = 64;
    private static final int MAX_LEVEL = 40;

    private static final class AttackDamage {
        private static final float BASE = 2;
        private static final float MINIMUM = 2;
        private static final float MAXIMUM = 37;
    }

    private static float ATTACK_DAMAGE_RANGE = AttackDamage.MAXIMUM - AttackDamage.MINIMUM;

    private static final class MovementSpeed {
        private static final float BASE = 70;
        private static final float MINIMUM = 70;
        private static final float MAXIMUM = 95;
    }

    private static float MOVEMENT_SPEED_RANGE = MovementSpeed.MAXIMUM - MovementSpeed.MINIMUM;

    private static final class HealthPoint {
        private static final float BASE = 8;
        private static final float MINIMUM = 8;
        private static final float MAXIMUM = 245;
    }

    private static float HEALTH_POINT_RANGE = HealthPoint.MAXIMUM - HealthPoint.MINIMUM;

    private Vector2 position;
    private Vector2 boundPosition;

    private Circle enemyBound;
    private String type;
    private int score;
    private int gold;

    private float attackDamage;
    private float movementSpeed;
    private float healthPoint;

    // Boss
    public Enemy(float attackDamage, float movementSpeed, float healthPoint) {
        float x = ArrowStormGame.GAME_WIDTH / 2 - BOSS_BOUNDING_RADIUS;
        float y = 0;
        position = new Vector2(x, y);
        this.attackDamage = attackDamage;
        this.movementSpeed = movementSpeed;
        this.healthPoint = healthPoint;
        boundPosition = new Vector2(x + BOSS_BOUNDING_RADIUS, y + BOSS_BOUNDING_RADIUS);
        enemyBound = new Circle(boundPosition, BOSS_BOUNDING_RADIUS);
        score = GamePlayManager.stage * 1000;
        gold = GamePlayManager.stage * 250;
    }

    // Enemy
    public Enemy(float x, float y, float factorAttackDamage, float factorMovementSpeed,
                 float factorHealthPoint, int level) {
        position = new Vector2(x, y);
        // to set origin point of bound at center of sprite
        // when it has been drawn
        boundPosition = new Vector2(x + ENEMY_BOUNDING_RADIUS, y + ENEMY_BOUNDING_RADIUS);
        enemyBound = new Circle(boundPosition, ENEMY_BOUNDING_RADIUS);

        score = (level + 1) * 5 + level;
        gold = (level + 1) + level * 1 / 3;

        attackDamage = AttackDamage.BASE + (level
                * (ATTACK_DAMAGE_RANGE / MAX_LEVEL));
        attackDamage *= factorAttackDamage;

        movementSpeed = MovementSpeed.BASE + (level
                * (MOVEMENT_SPEED_RANGE / MAX_LEVEL));
        movementSpeed *= factorMovementSpeed;

        healthPoint = HealthPoint.BASE + (level
                * (HEALTH_POINT_RANGE / MAX_LEVEL));
        healthPoint *= factorHealthPoint;
        switch (GamePlayManager.stage) {
            case 2:
                healthPoint *= 1.3;
                break;
            case 3:
                healthPoint *= 1.6;
                break;
            default:
        }
    }

    public boolean isDied() {
        return healthPoint <= 0;
    }

    public void takeDamage(float damage) {
        healthPoint -= damage;
    }

    public int getScore() {
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

    public int getGold() {
        return gold;
    }
}