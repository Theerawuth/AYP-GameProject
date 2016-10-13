package com.ayp.arrowstormgame.game_manager;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.EnemyUniverse;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.model.enemiespack.Boar;
import com.ayp.arrowstormgame.model.enemiespack.Tiger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Random;

import static com.ayp.arrowstormgame.helper.ArrayListUtils.removeDuplicateIndex;

/**
 * Created by Tanaphon on 10/10/2016.
 */

public class GamePlayManager {
    private final ArrowStormGame game;
    private long lastArrow;
    private long lastTouched;
    private long shootDelay;
    private static int PREPARE_SHOOT = -1;
    private Vector3 touchPosition;
    private int score = 0;
    private Player player;

    public GamePlayManager(final ArrowStormGame game) {
        this.game = game;
        player = new Player();
        lastArrow = PREPARE_SHOOT;
        shootDelay = player.ATTACK_SPEED;
        lastTouched = TimeUtils.nanoTime() - shootDelay;
    }

    public void handleTouchEvent(Array<Arrow> arrows) {
        if (Gdx.input.isTouched()) {
            touchPosition = new Vector3();
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPosition);
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
            if (lastArrow == PREPARE_SHOOT) {
                shootArrow(arrowAngle, arrowDirectionAngle, arrows);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
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
        ArrayList<Integer> preparedRemovedEnemyIndexes = new ArrayList<Integer>();
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.move(delta);
            if (enemy.getPosition().x - enemy.getEnemyBound().radius < 0
                    || enemy.getPosition().x > game.GAME_WIDTH
                    || enemy.getPosition().y > game.GAME_HEIGHT - Player.PLAYER_HEIGHT) {
                preparedRemovedEnemyIndexes.add(i);
            }
        }
        ArrayList<Integer> removedEnemyIndexes = removeDuplicateIndex(preparedRemovedEnemyIndexes);
        int removedEnemyIndexesSize = removedEnemyIndexes.size();
        for (int i = removedEnemyIndexesSize; i > 0; i--) {
            enemies.removeIndex(removedEnemyIndexes.get(i - 1));
        }
    }

    public void updateCollision(float delta, Array<Enemy> enemies, Array<Arrow> arrows) {
        int enemiesSize = enemies.size;
        int arrowsSize = arrows.size;
        ArrayList<Integer> preparedRemovedArrowIndexes = new ArrayList<Integer>();
        ArrayList<Integer> preparedRemovedEnemyIndexes = new ArrayList<Integer>();
        for (int i = 0; i < enemiesSize; i++) {
            Enemy enemy = enemies.get(i);
            for (int j = 0; j < arrowsSize; j++) {
                Arrow arrow = arrows.get(j);
                if (arrow.getArrowBound().overlaps(enemy.getEnemyBound())) {
                    preparedRemovedEnemyIndexes.add(i);
                    preparedRemovedArrowIndexes.add(j);
                    score++;
                }
            }
        }

        ArrayList<Integer> removedEnemyIndexes = removeDuplicateIndex(preparedRemovedEnemyIndexes);
        ArrayList<Integer> removedArrowIndexes = removeDuplicateIndex(preparedRemovedArrowIndexes);
        int removedEnemyIndexesSize = removedEnemyIndexes.size();
        int removedArrowIndexesSize = removedArrowIndexes.size();

        for (int i = removedEnemyIndexesSize; i > 0; i--) {
            enemies.removeIndex(removedEnemyIndexes.get(i - 1));
        }

        for (int i = removedArrowIndexesSize; i > 0; i--) {
            arrows.removeIndex(removedArrowIndexes.get(i - 1));
        }
    }

    public void randomSpawnAnWithFixedTime(Array<Enemy> enemies) {
        Random random = new Random();
        // 64 now is temp value for enemy width
        float originX = random.nextFloat() * (game.GAME_WIDTH - 64);

        if (originX > game.GAME_WIDTH / 2) {
            spawnEnemy(originX, 0, EnemyUniverse.EnemyType.BOAR, enemies);
        } else {
            spawnEnemy(originX, 0, EnemyUniverse.EnemyType.TIGER, enemies);
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

    // Spawn enemies.
    public void spawnEnemy(
            float originX,
            float originY,
            EnemyUniverse.EnemyType enemyType,
            int number,
            float deltaX,
            float deltaY,
            long spawnDelay,
            Array<Enemy> enemies
    ) {
        spawnByType(originX, originY, enemyType, enemies);
    }


    public void spawnByType(
            float originX,
            float originY,
            EnemyUniverse.EnemyType enemyType,
            Array<Enemy> enemies
    ) {
        switch (enemyType) {
            case BOAR:
                Enemy enemyBoar = new Boar(originX, originY);
                enemies.add(enemyBoar);
                break;
            case TIGER:
                Enemy enemyTiger = new Tiger(originX, originY);
                enemies.add(enemyTiger);
                return;
            default:
                break;
        }
    }

    public String getScore() {
        return Integer.toString(score);
    }

}
