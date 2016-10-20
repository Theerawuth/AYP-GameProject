package com.ayp.arrowstormgame.model;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public abstract class Boss extends Enemy{
    private boolean isAlive;
    public static final float WIDTH = 128f;
    public static final float HEIGHT = 128f;

    public Boss(float attackDamage, float movementSpeed, float healthPoint) {
        super(attackDamage, movementSpeed, healthPoint);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
