package com.ayp.arrowstormgame.model;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public class Boss extends Enemy{
    private boolean isAlive;
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
