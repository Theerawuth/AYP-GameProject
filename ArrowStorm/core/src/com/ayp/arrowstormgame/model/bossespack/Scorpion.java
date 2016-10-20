package com.ayp.arrowstormgame.model.bossespack;

import com.ayp.arrowstormgame.model.Boss;

/**
 * Created by Tanaphon on 10/19/2016.
 */

public class Scorpion extends Boss {
    private static final float ATTACK_DAMAGE = 40;
    private static final float MOVEMENT_SPEED = 180;
    private static final float HEALTH_POINT = 1800;

    public Scorpion() {
        super(ATTACK_DAMAGE, MOVEMENT_SPEED, HEALTH_POINT);
    }
}
