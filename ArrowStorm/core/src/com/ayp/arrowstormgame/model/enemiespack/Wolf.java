package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 10/13/2016.
 */

public class Wolf extends Enemy {
    // 0.4 * 1.1 = 0.44
    private static final float factorAttackDamage = 1.1f;
    // 0.4 * 0.8 = 0.32
    private static final float factorMovementSpeed = 0.8f;
    // 0.2 * 1.2 = 0.24
    private static final float factorHealthPoint = 1.2f;

    public static final String TYPE = "Wolf";

    public Wolf(float x, float y, int level) {
        super(x, y, factorAttackDamage, factorMovementSpeed, factorHealthPoint, level);
    }
}