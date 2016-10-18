package com.ayp.arrowstormgame.game_manager;

import com.badlogic.gdx.Gdx;

/**
 * Created by Tanaphon on 10/15/2016.
 */
public class EnemyLevelManager {
    private static final String TAG = "EnemyLevelManager";
    private float gameTimer;
    private int currentEnemyLevel;

    public EnemyLevelManager() {
        gameTimer = 0;
        currentEnemyLevel = 0;
    }

    /**
     * Enemy level will increase by 1 every 12 seconds
     *
     * @param delta game time in 1 frame rendered
     */
    public void updateEnemyLevelByTime(float delta) {
        if (currentEnemyLevel < 40 && gameTimer >= 12) {
            currentEnemyLevel++;
            gameTimer = 0;
            Gdx.app.log("updateEnemyLevelByTime", "currentEnemyLevel" + currentEnemyLevel);
        }
        gameTimer += delta;
    }

    public int getCurrentEnemyLevel() {
        return currentEnemyLevel;
    }
}