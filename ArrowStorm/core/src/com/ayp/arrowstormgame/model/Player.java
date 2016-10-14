package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.ArrowStormGame;

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
    private static final long MAXIMUM_ATTACK_SPEED = 330000000;
    // minimum attack speed is 1.12 second
    private static final long MINIMUM_ATTACK_SPEED = 1120000000;
    private long attackSpeedRange = MAXIMUM_ATTACK_SPEED - MINIMUM_ATTACK_SPEED;
    // base attack speed is 1.12 second
    public static long BASE_ATTACK_SPEED = 1120000000;
    // attack speed will be set when create new instance player
    public static long attackSpeed;
    // level of attack speed skill 0 - 20
    private int attackSpeedSkillLevel = 0;

    private static final int MAXIMUM_HEALTH_POINT = 120;
    private static final int MINIMUM_HEALTH_POINT = 5;
    private static int BASE_HEALTH_POINT = 5;
    private int healthPointRange = MAXIMUM_HEALTH_POINT - MINIMUM_HEALTH_POINT;
    private int healthPointSkillLevel = 0;
    public static int healthPoint;

    private static final int MAXIMUM_ATTACK_DAMAGE = 100;
    private static final int MINIMUM_ATTACK_DAMAGE = 10;
    private static int BASE_ATTACK_DAMAGE = 10;
    private int attackDamageRange = MAXIMUM_ATTACK_DAMAGE - MINIMUM_ATTACK_DAMAGE;
    private int attackDamageSkillLevel = 0;
    public static int attackDamage;
    private static final int MAX_LEVEL_SKILL = 20;

    public Player() {
        attackSpeed =
                BASE_ATTACK_SPEED
                        + (attackSpeedSkillLevel * ((attackSpeedRange) / MAX_LEVEL_SKILL));
        healthPoint =
                BASE_HEALTH_POINT
                        + (healthPointSkillLevel * ((healthPointRange) / MAX_LEVEL_SKILL));
        attackDamage =
                BASE_ATTACK_DAMAGE
                        + (attackDamageSkillLevel * ((attackDamageRange) / MAX_LEVEL_SKILL));
    }
}