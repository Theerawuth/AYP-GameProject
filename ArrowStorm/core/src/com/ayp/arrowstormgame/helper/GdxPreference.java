package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Tanaphon on 10/20/2016.
 */

public class GdxPreference {
    private static final String PLAYER_PREFERENCES = "Player Preferences";
    private static Preferences playerPreferences = Gdx.app.getPreferences(PLAYER_PREFERENCES);
    private static final String GOLD_PREFERENCE = "Gold";
    private static final String ATTACK_DAMAGE_LEVEL_PREFERENCE = "Attack Damage";
    private static final String ATTACK_SPEED_LEVEL_PREFERENCE = "Attack Speed";
    private static final String HEALTH_POINT_LEVEL_PREFERENCE = "Health Point";

    public static int getCurrentGold() {
        return playerPreferences.getInteger(GOLD_PREFERENCE);
    }

    private static void putCurrentGold(int gold) {
        playerPreferences.putInteger(GOLD_PREFERENCE, gold);
    }

    public static int getCurrentAttackDamageLevel() {
        return playerPreferences.getInteger(ATTACK_DAMAGE_LEVEL_PREFERENCE);
    }

    private static void putCurrentAttackDamageLevel(int level) {
        playerPreferences.putInteger(ATTACK_DAMAGE_LEVEL_PREFERENCE, level);
    }

    public static int getCurrenAttackSpeedLevel() {
        return playerPreferences.getInteger(ATTACK_SPEED_LEVEL_PREFERENCE);
    }

    private static void putCurrentAttackSpeedLevel(int level) {
        playerPreferences.putInteger(ATTACK_SPEED_LEVEL_PREFERENCE, level);
    }

    public static int getCurrentHealthPointLevel() {
        return playerPreferences.getInteger(HEALTH_POINT_LEVEL_PREFERENCE);
    }

    private static void putCurrentHealthPointLevel(int level) {
        playerPreferences.putInteger(HEALTH_POINT_LEVEL_PREFERENCE, level);
    }
}
