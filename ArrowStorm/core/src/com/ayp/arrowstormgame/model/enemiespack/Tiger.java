package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 9/30/2016.
 */

public class Tiger extends Enemy {
    private static float TIGER_WIDTH = 128;
    private static float TIGER_HEIGHT = 128;
    public Tiger(float x, float y) {
        super(x, y, TIGER_WIDTH, TIGER_HEIGHT);
    }
}
