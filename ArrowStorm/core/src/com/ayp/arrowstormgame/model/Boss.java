package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.game_manager.GamePlayManager;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public abstract class Boss extends Enemy {
    private boolean isAlive;
    public static final float WIDTH = 128f;
    public static final float HEIGHT = 128f;

    public Boss(float attackDamage, float movementSpeed, float healthPoint) {
        super(attackDamage, movementSpeed, healthPoint);
        isAlive = true;

    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

}
