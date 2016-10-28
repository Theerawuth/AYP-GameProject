package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.GdxPreference;
import com.ayp.arrowstormgame.helper.SoundManager;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.model.bossespack.Kraken;
import com.ayp.arrowstormgame.screen.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.ayp.arrowstormgame.helper.ArrayListUtils.removeDuplicateIndex;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class GamePlayManager {
    private static final String TAG = "GamePlayManager";
    private final ArrowStormGame game;
    private static final int PREPARE_SHOOT = -1;
    private int score;
    private int gold;
    public static int stage;
    private int currentEnemyLevel;
    private long lastArrow;
    private long lastTouched;
    private long shootDelay;
    private boolean isPause;
    private Vector3 touchPosition;
    private Player player;
    private EnemySpawnManager enemySpawnManager;
    private EnemyLevelManager enemyLevelManager;
    private boolean bossOneSpawn;
    private boolean bossTwoSpawn;
    private boolean bossThreeSpawn;
    private boolean bossThreeDied;

    public GamePlayManager(final ArrowStormGame game, Player player) {
        this.game = game;
        this.player = player;
        enemyLevelManager = new EnemyLevelManager();
        enemySpawnManager = new EnemySpawnManager();
        isPause = false;
        bossOneSpawn = false;
        bossTwoSpawn = false;
        bossThreeSpawn = false;
        bossThreeDied = false;
        score = 0;
        gold = GdxPreference.getCurrentGold();
        lastArrow = PREPARE_SHOOT;
        shootDelay = player.attackSpeed;
        lastTouched = TimeUtils.nanoTime() - shootDelay;
    }

    public boolean isPause() {
        return isPause;
    }

    public EnemyLevelManager getEnemyLevelManager() {
        return enemyLevelManager;
    }

    public void update() {
        currentEnemyLevel = enemyLevelManager.getCurrentEnemyLevel();
        if (currentEnemyLevel <= 15) {
            stage = 1;
        } else if (currentEnemyLevel > 15 && currentEnemyLevel <= 30) {
            stage = 2;
        } else if (currentEnemyLevel > 30 && currentEnemyLevel <= 40) {
            stage = 3;
        }
        if (bossThreeDied) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    public void spawnEnemy(float delta, Array<Enemy> enemies) {
        if (currentEnemyLevel == 15 && !bossOneSpawn) {
            enemySpawnManager.spawnBoss(enemies);
            bossOneSpawn = true;
        } else if (currentEnemyLevel == 30 && !bossTwoSpawn) {
            enemySpawnManager.spawnBoss(enemies);
            bossTwoSpawn = true;
        } else if (currentEnemyLevel == 40 && !bossThreeSpawn) {
            enemySpawnManager.spawnBoss(enemies);
            bossThreeSpawn = true;
        }
        enemySpawnManager.spawnUnderStage(delta, stage, enemies, currentEnemyLevel);
    }


    public void handleTouchEvent(Array<Arrow> arrows) {
        if (Gdx.input.isTouched()) {
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPosition);
            if (touchPosition.y >= 0 && touchPosition.y <= 64 && touchPosition.x >= 416
                    && touchPosition.x <= 480) {
                isPause = true;
            }
            if (!player.isAlive() && touchPosition.x > 200 && touchPosition.x < 400
                    && touchPosition.y > 400 && touchPosition.y < 500) {
                game.setScreen(new MainMenuScreen(game));
            }

            if (!player.isAlive()) {
                return;
            }

            if (isPause()) {
                if (touchPosition.y >= 300 && touchPosition.y <= 396 && touchPosition.x >= 112
                        && touchPosition.x <= 368) {
                    isPause = false;
                } else if (touchPosition.y >= 500 && touchPosition.y <= 596
                        && touchPosition.x >= 112 && touchPosition.x <= 368) {
                    game.setScreen(new MainMenuScreen(game));
                }
            }

            if (touchPosition.y < Player.SHOOTING_POINT_Y) {
                // Cancelled touch outside of player zone
                return;
            }
            float angleDegree = (float) Math.toDegrees(Math.atan2(
                    touchPosition.y - Player.SHOOTING_POINT_Y,
                    touchPosition.x - Player.SHOOTING_POINT_X)
            );

            float arrowAngle = angleDegree - 90;
            float arrowDirectionAngle = arrowAngle + 90;

            player.angle = arrowAngle;
            if (lastArrow == PREPARE_SHOOT) {
                if (GdxPreference.getSoundSetting()) {
                    SoundManager.playShootingSound();
                }
                shootArrow(arrowAngle, arrowDirectionAngle, arrows);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
                if (GdxPreference.getSoundSetting()) {
                    SoundManager.playShootingSound();
                }
                shootArrow(arrowAngle, arrowDirectionAngle, arrows);
            }
        } else {
            if (TimeUtils.nanoTime() - lastTouched > shootDelay
                    && TimeUtils.nanoTime() - lastArrow > shootDelay) {
                lastArrow = PREPARE_SHOOT;
                lastTouched = TimeUtils.nanoTime();
            }
        }

    }

    public void updateArrow(float delta, Array<Arrow> arrows) {
        ArrayList<Integer> preparedRemovedArrowIndexes = new ArrayList<Integer>();
        for (int i = 0; i < arrows.size; i++) {
            Arrow arrow = arrows.get(i);
            arrow.move(delta);
            if (arrow.getArrowPosition().x < 0 || arrow.getArrowPosition().x > game.GAME_WIDTH
                    || arrow.getArrowPosition().y < 0) {
                preparedRemovedArrowIndexes.add(i);
            }
        }
        ArrayList<Integer> removedArrowIndexes = removeDuplicateIndex(preparedRemovedArrowIndexes);
        int removedArrowIndexesSize = removedArrowIndexes.size();
        for (int i = removedArrowIndexesSize; i > 0; i--) {
            arrows.removeIndex(removedArrowIndexes.get(i - 1));
        }
    }

    private void shootArrow(float arrowAngle, float arrowDirectionInDegree, Array<Arrow> arrows) {
        Arrow arrow = new Arrow(Player.SHOOTING_POINT_X, Player.SHOOTING_POINT_Y,
                arrowDirectionInDegree, arrowAngle);
        arrows.add(arrow);
        lastArrow = TimeUtils.nanoTime();
    }

    public void updateEnemy(float delta, Array<Enemy> enemies) {
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.move(delta);
            if (enemy.getDrawPosition().y > (game.GAME_HEIGHT - 200)
                    - enemy.getEnemyBound().radius * 2) {
                enemies.removeIndex(i);
                Player.healthPoint -= enemy.getAttackDamage();
                if (Player.healthPoint < 1) {
                    GdxPreference.putCurrentGold(gold);
                    GdxPreference.flushPreferences();
                    if (score > 9999999) {
                        score = 9999999;
                    }
                    if (score > GdxPreference.getHighScore()) {
                        GdxPreference.putHighScore(score);
                        GdxPreference.flushPreferences();
                        player.setHighScore(score);
                        // Add the highest score to leaderboard
                        game.playServices.submitScore(score);
                    }
                    player.setAlive(false);
                }
                break;
            }
        }
    }

    public void updateCollision(float delta, Array<Enemy> enemies, Array<Arrow> arrows) {
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);

            for (int j = 0; j < arrows.size; j++) {
                Arrow arrow = arrows.get(j);
                if (arrow.getArrowBound().overlaps(enemy.getEnemyBound())) {
                    // enemy is hit
                    enemy.setDrawPosition(new Vector2(enemy.getDrawPosition().x,
                            enemy.getDrawPosition().y - 4f));
                    enemy.setBoundPosition(new Vector2(enemy.getEnemyBound().x,
                            enemy.getEnemyBound().y - 4f));
                    enemy.takeDamage(player.attackDamage);
                    arrows.removeIndex(j);
                    break;
                }
            }
            if (enemy.isDied()) {
                if (GdxPreference.getSoundSetting()) {
                    SoundManager.playMonsterDead();
                }
                score += enemy.getScore();
                gold += enemy.getGold();

                if (enemy instanceof Kraken) {
                    bossThreeDied = true;
                }

//                if (enemy.getDeadAnimationRuntime() > enemy.getDeadAnimationDuration()) {
//                    Gdx.app.log(TAG,"runtime: " + enemy.getDeadAnimationRuntime() + ", duration"
//                            + enemy.getDeadAnimationDuration());
                enemies.removeIndex(i);
//                }
                break;
            }
        }
    }

    public String getScore() {
        return Integer.toString(score);
    }

    public String getGold() {
        return Integer.toString(gold);
    }
}