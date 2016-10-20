package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.model.Boss;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.EnemyUniverse;
import com.ayp.arrowstormgame.model.bossespack.GoldenBug;
import com.ayp.arrowstormgame.model.bossespack.Kraken;
import com.ayp.arrowstormgame.model.bossespack.Scorpion;
import com.ayp.arrowstormgame.model.enemiespack.Bug;
import com.ayp.arrowstormgame.model.enemiespack.Guardian;
import com.ayp.arrowstormgame.model.enemiespack.Worm;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by Tanaphon on 10/18/2016.
 */

public class EnemySpawnManager {

    private static final class SpawnPeriodProbability {
        private static final float FAST_PERCENTILE = 0.15f;
        private static final float NORMAL_PERCENTILE = 0.75f;
        private static final float SLOW_PERCENTILE = 0.10f;
        private static final float FAST = FAST_PERCENTILE;
        private static final float NORMAL = FAST_PERCENTILE + NORMAL_PERCENTILE;
        private static final float SLOW = FAST_PERCENTILE + NORMAL_PERCENTILE + SLOW_PERCENTILE;
    }

    private static final class BaseDelaySpawn {
        private static final float FAST = 0.9f;
        private static final float NORMAL = 1.5f;
        private static final float SLOW = 2.5f;
    }

    private static final class BiasDelaySpawn {
        private static final float FAST = 0.6f;
        private static final float NORMAL = 1.0f;
        private static final float SLOW = 0.3f;
    }

    private static final class SpawnChance {
        private static final float HIGH = 0.50f;
        private static final float LOW = 0.25f;
    }

    //    private EnemyLevelManager enemyLevelManager;
    private Random random;
    private float elapseTime;
    private float spawnTime;

    public EnemySpawnManager() {
//        this.enemyLevelManager = enemyLevelManager;
        elapseTime = 0;
        random = new Random();
        spawnTime = generateSpawnTime();
    }

    public void spawnBoss(Array<Enemy> enemies){
        switch (GamePlayManager.stage){
            case 1:
                Boss bossGoldenBug = new GoldenBug();
                enemies.add(bossGoldenBug);
                break;
            case 2:
                Boss bossScorpion = new Scorpion();
                enemies.add(bossScorpion);
                break;
            case 3:
                Boss bossKraken = new Kraken();
                enemies.add(bossKraken);
                break;
            default:
                break;
        }
    }

    public void spawnEnemy(Array<Enemy> enemies, float bugSpawnChance, float wormSpawnChance,
                           float guardianSpawnChance, int currentEnemyLevel) {
        float randomSpawnValue = random.nextFloat();
        float originX = random.nextFloat() * (ArrowStormGame.GAME_WIDTH - Enemy.WIDTH);
        if (randomSpawnValue <= bugSpawnChance) {
            spawn(originX, 0, EnemyUniverse.EnemyType.BUG, enemies, currentEnemyLevel);
        } else if (randomSpawnValue > bugSpawnChance
                && randomSpawnValue <= bugSpawnChance + wormSpawnChance) {
            spawn(originX, 0, EnemyUniverse.EnemyType.WORM, enemies, currentEnemyLevel);
        } else if (randomSpawnValue > bugSpawnChance + wormSpawnChance
                && randomSpawnValue <= bugSpawnChance + randomSpawnValue + guardianSpawnChance) {
            spawn(originX, 0, EnemyUniverse.EnemyType.GUARDIAN, enemies, currentEnemyLevel);
        }
    }

    // Spawn an enemy.
    public void spawn(float originX, float originY, EnemyUniverse.EnemyType enemyType,
                      Array<Enemy> enemies, int currentEnemyLevel) {
        spawnBoss(enemies);
        switch (enemyType) {
            case BUG:
                Enemy enemyBug = new Bug(originX, originY, currentEnemyLevel);
                enemies.add(enemyBug);
                break;
            case WORM:
                Enemy enemyWorm = new Worm(originX, originY, currentEnemyLevel);
                enemies.add(enemyWorm);
                return;
            case GUARDIAN:
                Enemy enemyGuardian = new Guardian(originX, originY, currentEnemyLevel);
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
        if (spawnRate <= SpawnPeriodProbability.FAST) {
            spawnTime = BaseDelaySpawn.FAST + BiasDelaySpawn.FAST * factorBiasSpawn;
        } else if (spawnRate > SpawnPeriodProbability.FAST
                && spawnRate <= SpawnPeriodProbability.NORMAL) {
            spawnTime = BaseDelaySpawn.NORMAL + BiasDelaySpawn.NORMAL * factorBiasSpawn;
        } else if (spawnRate > SpawnPeriodProbability.NORMAL
                && spawnRate <= SpawnPeriodProbability.SLOW) {
            spawnTime = BaseDelaySpawn.SLOW + BiasDelaySpawn.SLOW * factorBiasSpawn;
        }
        return spawnTime;
    }

    public void spawnUnderStage(float delta, int stage, Array<Enemy> enemies, int currentEnemyLevel) {
        if (elapseTime >= spawnTime) {
            // spawnEnemy enemy
            switch (stage) {
                case 1:
                    spawnEnemy(enemies, SpawnChance.HIGH, SpawnChance.LOW, SpawnChance.LOW, currentEnemyLevel);
                    break;
                case 2:
                    spawnEnemy(enemies, SpawnChance.LOW, SpawnChance.HIGH, SpawnChance.LOW, currentEnemyLevel);
                    break;
                case 3:
                    spawnEnemy(enemies, SpawnChance.LOW, SpawnChance.LOW, SpawnChance.HIGH, currentEnemyLevel);
                    break;
                default:
            }
            spawnTime = generateSpawnTime();
            elapseTime = 0;
        }
        elapseTime += delta;
    }
}