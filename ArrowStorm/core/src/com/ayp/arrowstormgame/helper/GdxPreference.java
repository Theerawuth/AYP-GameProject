package com.ayp.arrowstormgame.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Tanaphon on 10/20/2016.
 */

public class GdxPreference {
    private static final String PLAYER_PREFERENCES = "Player Preferences";
    private static Preferences playerPreferences = Gdx.app.getPreferences(PLAYER_PREFERENCES);
    private static final String GOLD_PREFERENCES = "gold";

    public static int getCurrentGold() {
        return playerPreferences.getInteger(GOLD_PREFERENCES);
    }

    private static void putCurrentGold(int gold) {
        playerPreferences.putInteger(GOLD_PREFERENCES, gold);
    }
}
