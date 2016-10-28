package com.ayp.arrowstormgame.model;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.badlogic.gdx.Gdx;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class Player {
    public static final float SHOOTING_POINT_X = ArrowStormGame.GAME_WIDTH / 2;
    public static final float SHOOTING_POINT_Y = ArrowStormGame.GAME_HEIGHT * 3 / 4;
    public static final float SCALE_X = 1f;
    public static final float SCALE_Y = 1f;
    public static final float POSITION_X = 0;
    public static final float POSITION_Y = ArrowStormGame.GAME_HEIGHT * 3 / 4;
    public static final float PLAYER_WIDTH = 96f;
    public static final float PLAYER_HEIGHT = 96f;
    // maximum attack speed is 0.22 second
    private static final long MAXIMUM_ATTACK_SPEED = 220000000;
    // minimum attack speed is 1.12 second
    private static final long MINIMUM_ATTACK_SPEED = 1120000000;
    private long attackSpeedRange = MAXIMUM_ATTACK_SPEED - MINIMUM_ATTACK_SPEED;
    // base attack speed is 1.12 second
    public static long BASE_ATTACK_SPEED = 1120000000;
    // attack speed will be set when create new instance player
    public static long attackSpeed;
    // level of attack speed skill 0 - 20

    private static final float MAXIMUM_HEALTH_POINT = 500;
    private static final float MINIMUM_HEALTH_POINT = 20;
    private static float BASE_HEALTH_POINT = 20;
    private float healthPointRange = MAXIMUM_HEALTH_POINT - MINIMUM_HEALTH_POINT;
    public static float healthPoint;

    private static final float MAXIMUM_ATTACK_DAMAGE = 240;
    private static final float MINIMUM_ATTACK_DAMAGE = 10;
    private static float BASE_ATTACK_DAMAGE = 10;
    private float attackDamageRange = MAXIMUM_ATTACK_DAMAGE - MINIMUM_ATTACK_DAMAGE;
    public static float attackDamage;
    public static final int MAX_LEVEL_SKILL = 40;

    public static float angle = 0;
    private static boolean isAlive;

    private int attackDamageSkillLevel;
    private int healthPointSkillLevel;
    private int attackSpeedSkillLevel;
    private int gold;
    private int highScore;

    public Player(int attackDamageSkillLevel, int attackSpeedSkillLevel,
                  int healthPointSkillLevel, int gold) {
        this.attackDamageSkillLevel = attackDamageSkillLevel;
        this.healthPointSkillLevel = healthPointSkillLevel;
        this.attackSpeedSkillLevel = attackSpeedSkillLevel;
        this.gold = gold;
        isAlive = true;
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

    public static String skillUpCostString(int level) {
        return String.valueOf(skillUpCostInt(level));
    }

    public static int skillUpCostInt(int level) {
        return (level + 1) * (level + 4) + level * 4 + ((level + 3) * 2);
    }

    public static String getHealthPoint() {
        return Integer.toString((int) healthPoint);
    }

    public static boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getAttackDamageSkillLevel() {
        return attackDamageSkillLevel;
    }

    public void setAttackDamageSkillLevel(int attackDamageSkillLevel) {
        this.attackDamageSkillLevel = attackDamageSkillLevel;
    }

    public int getHealthPointSkillLevel() {
        return healthPointSkillLevel;
    }

    public void setHealthPointSkillLevel(int healthPointSkillLevel) {
        this.healthPointSkillLevel = healthPointSkillLevel;
    }

    public int getAttackSpeedSkillLevel() {
        return attackSpeedSkillLevel;
    }

    public void setAttackSpeedSkillLevel(int attackSpeedSkillLevel) {
        this.attackSpeedSkillLevel = attackSpeedSkillLevel;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}