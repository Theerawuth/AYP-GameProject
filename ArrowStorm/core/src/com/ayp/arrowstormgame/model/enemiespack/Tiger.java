package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 9/30/2016.
 */

public class Tiger extends Enemy {
    private static float TIGER_BOUND_RADIUS = 64;
    public Tiger(float x, float y) {
        super(x, y, TIGER_BOUND_RADIUS);
    }
}
