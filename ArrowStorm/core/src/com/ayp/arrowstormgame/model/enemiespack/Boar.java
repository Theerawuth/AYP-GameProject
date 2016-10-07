package com.ayp.arrowstormgame.model.enemiespack;

import com.ayp.arrowstormgame.model.Enemy;

/**
 * Created by Tanaphon on 9/29/2016.
 */

public class Boar extends Enemy {
    private int hp = 100; // sample property of this enemy
    private static float BOAR_BOUND_RADIUS = 32;

    public Boar(float x, float y) {
        super(x, y, BOAR_BOUND_RADIUS);
    }

}
