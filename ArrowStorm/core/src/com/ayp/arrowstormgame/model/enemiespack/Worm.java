package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 9/30/2016.
 */

public class Worm extends Enemy {
    // 0.4 * 1.1 = 0.44
    private static final float factorAttackDamage = 1.1f;
    // 0.4 * 1.4 = 0.42
    private static final float factorMovementSpeed = 1.4f;
    // 0.2 * 0.7 = 0.14
    private static final float factorHp = 0.7f;

    public static final String TYPE = "Worm";

    public Worm(float x, float y, int level) {
        super(x, y, factorAttackDamage, factorMovementSpeed, factorHp, level);
    }
}
