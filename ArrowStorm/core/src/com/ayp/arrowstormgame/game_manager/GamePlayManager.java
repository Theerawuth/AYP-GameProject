package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.screen.MainMenuScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import static com.ayp.arrowstormgame.helper.ArrayListUtils.removeDuplicateIndex;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class GamePlayManager {
    private static final String TAG = "GamePlayManager";

    private final ArrowStormGame game;
    private long lastArrow;
    private long lastTouched;
    private long shootDelay;
    private static final int PREPARE_SHOOT = -1;
    private Vector3 touchPosition;
    private int score = 0;
    // gold will be set from database
    private int gold = 0;
    private Player player;
    private EnemySpawnManager enemySpawnManager;
    private EnemyLevelManager enemyLevelManager;
    private int stage;
    private boolean isPause;
    private Music shootingMusic;
    private Music monsterDeadMusic;

    public GamePlayManager(final ArrowStormGame game) {
        this.game = game;
        player = new Player();
        enemyLevelManager = new EnemyLevelManager();
        enemySpawnManager = new EnemySpawnManager(enemyLevelManager);
        lastArrow = PREPARE_SHOOT;
        shootDelay = player.attackSpeed;
        lastTouched = TimeUtils.nanoTime() - shootDelay;
        stage = 1;
        isPause = false;
        shootingMusic = AssetsLoader.shootingMusic;
        monsterDeadMusic = AssetsLoader.monsterDeadMusic;
    }

    public boolean isPause() {
        return isPause;
    }

    public EnemyLevelManager getEnemyLevelManager() {
        return enemyLevelManager;
    }

    public void update() {
        if (enemyLevelManager.getCurrentEnemyLevel() > 15
                && enemyLevelManager.getCurrentEnemyLevel() <= 30) {
            stage = 2;
        } else if (enemyLevelManager.getCurrentEnemyLevel() > 30
                && enemyLevelManager.getCurrentEnemyLevel() <= 40) {
            stage = 3;
        }
    }

    public void spawnEnemy(float delta, Array<Enemy> enemies) {
        enemySpawnManager.spawnUnderStage(delta, stage, enemies);
    }


    public void handleTouchEvent(Array<Arrow> arrows) {
        if (Gdx.input.isTouched()) {
            if (!player.isAlive()){
                game.setScreen(new MainMenuScreen(game));
            }
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPosition);
            if (touchPosition.y >= 0
                    && touchPosition.y <= 64
                    && touchPosition.x >= 416
                    && touchPosition.x <= 480
                    ) {
                Gdx.app.log("PAUSE", "pause btn is pressed");
                isPause = true;
            }
            if (isPause()) {
                if (touchPosition.y >= 300
                        && touchPosition.y <= 396
                        && touchPosition.x >= 112
                        && touchPosition.x <= 368) {
                    isPause = false;
                    Gdx.app.log("PAUSE", "resume btn is pressed");
                } else if (touchPosition.y >= 500
                        && touchPosition.y <= 596
                        && touchPosition.x >= 112
                        && touchPosition.x <= 368){
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
                shootingMusic.play();
                shootArrow(arrowAngle, arrowDirectionAngle, arrows);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
                shootingMusic.play();
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
            if (arrow.getArrowPosition().x < 0
                    || arrow.getArrowPosition().x > game.GAME_WIDTH
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
        Arrow arrow = new Arrow(
                Player.SHOOTING_POINT_X,
                Player.SHOOTING_POINT_Y,
                arrowDirectionInDegree,
                arrowAngle
        );
        arrows.add(arrow);
        lastArrow = TimeUtils.nanoTime();
    }

    public void updateEnemy(float delta, Array<Enemy> enemies) {
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.move(delta);
            if (enemy.getPosition().y
                    > game.GAME_HEIGHT - 200) {
                enemies.removeIndex(i);
                Player.healthPoint -= enemy.getAttackDamage();
                if (Player.healthPoint < 1){
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
                    enemies.get(i).takeDamage(player.attackDamage);
                    arrows.removeIndex(j);
                    break;
                }
            }
            if (enemies.get(i).isDied()) {
                monsterDeadMusic.play();
                score += enemies.get(i).getScore();
                gold += enemies.get(i).getGold();
                enemies.removeIndex(i);
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