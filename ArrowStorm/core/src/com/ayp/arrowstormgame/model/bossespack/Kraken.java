package com.ayp.arrowstormgame.model.bossespack;

import com.ayp.arrowstormgame.model.Boss;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public class Kraken extends Boss {
    private static final float ATTACK_DAMAGE = 70;
    private static final float MOVEMENT_SPEED = 90;
    private static final float HEALTH_POINT = 4000;

    public Kraken(float attackDamage, float movementSpeed, float healthPoint) {
        super(ATTACK_DAMAGE, MOVEMENT_SPEED, HEALTH_POINT);
    }
}
