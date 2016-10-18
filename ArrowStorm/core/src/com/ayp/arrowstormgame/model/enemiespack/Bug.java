package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 9/29/2016.
 */

public class Bug extends Enemy {
    // 0.4 * 0.5 = 0.2
    private static final float factorAttackDamage = 0.5f;
    // 0.4 * 0.5 = 0.2
    private static final float factorMovementSpeed = 0.5f;
    // 0.2 * 2.0 = 0.4
    private static final float factorHp = 2.0f;

    public static final String TYPE = "Bug";

    public static int BOAR_SCORE = 3;

    public Bug(float x, float y, int level) {
        super(x, y, factorAttackDamage, factorMovementSpeed, factorHp, level);
    }
}