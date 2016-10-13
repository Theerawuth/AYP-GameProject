package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Gdx;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class Player {
    public static final float SHOOTING_POINT_X = ArrowStormGame.GAME_WIDTH / 2;
    public static final float SHOOTING_POINT_Y = ArrowStormGame.GAME_HEIGHT * 3 / 4;
    public static final float POSITION_X = 0;
    public static final float POSITION_Y = ArrowStormGame.GAME_HEIGHT * 3 / 4;
    public static final float PLAYER_WIDTH = ArrowStormGame.GAME_WIDTH;
    public static final float PLAYER_HEIGHT = ArrowStormGame.GAME_HEIGHT / 4;
    // maximum attack speed is 0.33 second
    public static final long MAXIMUM_ATTACK_SPEED = 330000000;
    // minimum attack speed is 1.12 second
    public static final long MINIMUM_ATTACK_SPEED = 1120000000;
    // base attack speed is 1.12 second
    public static long BASE_ATTACK_SPEED = 1120000000;
    // attack speed will be set when create new instance player
    public static long ATTACK_SPEED;
    // level of attack speed skill 0 - 20
    private int attackSpeedLevel = 0;

    public Player() {
        long attackSpeedRange = MAXIMUM_ATTACK_SPEED - MINIMUM_ATTACK_SPEED;
        ATTACK_SPEED = BASE_ATTACK_SPEED + (attackSpeedLevel * ((attackSpeedRange) / 20));
    }
}