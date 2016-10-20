package com.ayp.arrowstormgame.model.bossespack;

import com.ayp.arrowstormgame.model.Boss;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public class GoldenBug extends Boss {
    private static final float ATTACK_DAMAGE = 20;
    private static final float MOVEMENT_SPEED = 120;
    private static final float HEALTH_POINT = 800;

    public GoldenBug(float attackDamage, float movementSpeed, float healthPoint) {
        super(ATTACK_DAMAGE, MOVEMENT_SPEED, HEALTH_POINT);
    }
}
