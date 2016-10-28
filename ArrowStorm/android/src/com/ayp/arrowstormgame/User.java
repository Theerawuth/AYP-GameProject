package com.ayp.arrowstormgame;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Tanaphon on 10/24/2016.
 */

@IgnoreExtraProperties
public class User {
    private String googlePlayId;
    private int highScore;
    private int attackDamageLevel;
    private int attackSpeedLevel;
    private int healthPointLevel;
    private int gold;

    public String getGooglePlayId() {
        return googlePlayId;
    }

    public void setGooglePlayId(String googlePlayId) {
        this.googlePlayId = googlePlayId;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getAttackDamageLevel() {
        return attackDamageLevel;
    }

    public void setAttackDamageLevel(int attackDamageLevel) {
        this.attackDamageLevel = attackDamageLevel;
    }

    public int getAttackSpeedLevel() {
        return attackSpeedLevel;
    }

    public void setAttackSpeedLevel(int attackSpeedLevel) {
        this.attackSpeedLevel = attackSpeedLevel;
    }

    public int getHealthPointLevel() {
        return healthPointLevel;
    }

    public void setHealthPointLevel(int healthPointLevel) {
        this.healthPointLevel = healthPointLevel;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
