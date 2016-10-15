package com.ayp.arrowstormgame.game_manager;

/**
 * Created by Tanaphon on 10/15/2016.
 */
public class EnemyLevelManager {
    private float gameTimer;
    private float currentEnemyLevel;

    public EnemyLevelManager() {
        gameTimer = 0;
        currentEnemyLevel = 0;
    }

    /**
     * Enemy level will increase by 1 every period depends on
     * current level of enemy at that time.
     * 6 seconds per level from level 0 to 20
     * 18 seconds per level from level 21 to 30
     * 24 seconds per level from level 31 to 40
     * @param delta game time in 1 frame rendered
     */
    public void updateEnemyLevelByTime(float delta) {
        if (currentEnemyLevel <= 20 && gameTimer >= 6) {
            currentEnemyLevel++;
        } else if (currentEnemyLevel <= 30 && gameTimer >= 18) {
            currentEnemyLevel++;
        } else if (currentEnemyLevel <= 40 && gameTimer >= 24) {
            currentEnemyLevel++;
        }
        gameTimer += delta;
    }

    public float getCurrentEnemyLevel() {
        return currentEnemyLevel;
    }
}