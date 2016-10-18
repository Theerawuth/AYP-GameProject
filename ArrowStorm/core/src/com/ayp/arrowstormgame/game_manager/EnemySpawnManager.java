package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.EnemyUniverse;
import com.ayp.arrowstormgame.model.enemiespack.Bug;
import com.ayp.arrowstormgame.model.enemiespack.Guardian;
import com.ayp.arrowstormgame.model.enemiespack.Worm;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Tanaphon on 10/18/2016.
 */

public class EnemySpawnManager {

    private static final float FAST_SPAWN = 0.15f;
    private static final float NORMAL_SPAWN = 0.9f;
    private static final float SLOW_SPAWN = 1.0f;

    private static final float BASE_DELAY_FAST_SPAWN = 0.9f;
    private static final float BASE_DELAY_NORMAL_SPAWN = 1.5f;
    private static final float BASE_DELAY_SLOW_SPAWN = 2.5f;

    private static final float BIAS_DELAY_FAST_SPAWN = 0.6f;
    private static final float BIAS_DELAY_NORMAL_SPAWN = 1.0f;
    private static final float BIAS_DELAY_SLOW_SPAWN = 0.3f;

    private static final float HIGH_SPAWN_CHANCE = 0.5f;
    private static final float LOW_SPAWN_CHANCE = 0.25f;


    private float elapseTime;
    private int currentEnemyLevel;
    private EnemyLevelManager enemyLevelManager;
    private Random random;
    private float spawnTime;

    public EnemySpawnManager(EnemyLevelManager enemyLevelManager) {
        this.enemyLevelManager = enemyLevelManager;
        currentEnemyLevel = enemyLevelManager.getCurrentEnemyLevel();
        elapseTime = 0;
        random = new Random();
        spawnTime = generateSpawnTime();
    }

    public void spawn(
            Array<Enemy> enemies,
            float bugSpawnChance,
            float wormSpawnChance,
            float guardianSpawnChance
    ) {
        // 64 now is temp value for enemy width
        float randomSpawnValue = random.nextFloat();
        float originX = random.nextFloat() * (ArrowStormGame.GAME_WIDTH - Enemy.ENEMY_WIDTH);
        if (randomSpawnValue <= bugSpawnChance) {
            spawnEnemy(originX, 0, EnemyUniverse.EnemyType.BUG, enemies);
        } else if (randomSpawnValue > bugSpawnChance
                && randomSpawnValue <= bugSpawnChance + wormSpawnChance) {
            spawnEnemy(originX, 0, EnemyUniverse.EnemyType.WORM, enemies);
        } else if (randomSpawnValue > bugSpawnChance + wormSpawnChance
                && randomSpawnValue <= bugSpawnChance + randomSpawnValue + guardianSpawnChance) {
            spawnEnemy(originX, 0, EnemyUniverse.EnemyType.GUARDIAN, enemies);
        }
    }

    // Spawn an enemy.
    public void spawnEnemy(
            float originX,
            float originY,
            EnemyUniverse.EnemyType enemyType,
            Array<Enemy> enemies
    ) {
        spawnByType(originX, originY, enemyType, enemies);
    }

    private int getCurrentEnemyLevel() {
        return enemyLevelManager.getCurrentEnemyLevel();
    }

    public void spawnByType(
            float originX,
            float originY,
            EnemyUniverse.EnemyType enemyType,
            Array<Enemy> enemies
    ) {
        switch (enemyType) {
            case BUG:
                Enemy enemyBug = new Bug(originX, originY, getCurrentEnemyLevel());
                enemies.add(enemyBug);
                break;
            case WORM:
                Enemy enemyWorm = new Worm(originX, originY, getCurrentEnemyLevel());
                enemies.add(enemyWorm);
                return;
            case GUARDIAN:
                Enemy enemyGuardian = new Guardian(originX, originY, getCurrentEnemyLevel());
                enemies.add(enemyGuardian);
            default:
                break;
        }
    }

    private float generateSpawnTime() {
        // initialize spawnUnderStage time
        float spawnTime = 0;
        float spawnRate = random.nextFloat();
        float factorBiasSpawn = random.nextFloat();
        if (spawnRate <= FAST_SPAWN) {
            spawnTime = BASE_DELAY_FAST_SPAWN + BIAS_DELAY_FAST_SPAWN * factorBiasSpawn;
        } else if (spawnRate > FAST_SPAWN && spawnRate <= NORMAL_SPAWN) {
            spawnTime = BASE_DELAY_NORMAL_SPAWN + BIAS_DELAY_NORMAL_SPAWN * factorBiasSpawn;
        } else if (spawnRate > NORMAL_SPAWN && spawnRate <= SLOW_SPAWN) {
            spawnTime = BASE_DELAY_SLOW_SPAWN + BIAS_DELAY_SLOW_SPAWN * factorBiasSpawn;
        }
        return spawnTime;
    }

    public void spawnUnderStage(float delta, int stage, Array<Enemy> enemies) {
        if (elapseTime >= spawnTime) {
            // spawn enemy
            switch (stage) {
                case 1:
                    spawn(enemies, HIGH_SPAWN_CHANCE, LOW_SPAWN_CHANCE, LOW_SPAWN_CHANCE);
                    break;
                case 2:
                    spawn(enemies, LOW_SPAWN_CHANCE, HIGH_SPAWN_CHANCE, LOW_SPAWN_CHANCE);
                    break;
                case 3:
                    spawn(enemies, LOW_SPAWN_CHANCE, LOW_SPAWN_CHANCE, HIGH_SPAWN_CHANCE);
                    break;
                default:
            }
            Gdx.app.log("EnemySpawnManager", "elapseTime: " + elapseTime);
            spawnTime = generateSpawnTime();
            elapseTime = 0;
        }
        elapseTime += delta;
    }
}