package com.ayp.arrowstormgame.screen;

import com.ayp.arrowstormgame.ArrowStormGame;
import com.ayp.arrowstormgame.helper.AssetsLoader;
import com.ayp.arrowstormgame.model.Arrow;
import com.ayp.arrowstormgame.model.Enemy;
import com.ayp.arrowstormgame.model.Player;
import com.ayp.arrowstormgame.model.enemiespack.Boar;
import com.ayp.arrowstormgame.model.enemiespack.Tiger;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import static com.ayp.arrowstormgame.model.EnemyUniverse.*;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class PlayStateScreen implements Screen {

    private static int PREPARE_SHOOT = -1;
    public static String TAG = "PlayStateScreen";
    final private ArrowStormGame game;

    private Array<Arrow> arrows;
    private Array<Enemy> enemies;

    private int scaleArrowX;
    private int scaleArrowY;
    private long shootDelay = 100000000;
    private long lastArrow;
    private long lastTouched;
    private Sprite arrowSprite;
    private Vector3 touchPosition;
    private HashMap<String, Sprite> enemySprites;

    private float elapseTime;

    public PlayStateScreen(final ArrowStormGame game) {
        this.game = game;
        arrows = new Array<Arrow>();
        enemies = new Array<Enemy>();
        lastArrow = PREPARE_SHOOT;
        lastTouched = TimeUtils.nanoTime() - shootDelay;
        scaleArrowX = 1;
        scaleArrowY = 1;
        arrowSprite = AssetsLoader.arrowImageSprite;
        enemySprites = AssetsLoader.enemiesSprite;
        elapseTime = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawBackground();
        game.shapeRenderer.end();

        updateCollision(delta);
        handleTouchEvent();
        updateEnemy(delta);
        updateArrow(delta);

        game.spriteBatch.begin();
        game.spriteBatch.enableBlending();
        drawArrow();
        drawEnemy();
        game.spriteBatch.disableBlending();
        game.spriteBatch.end();


        elapseTime += delta;
        if (elapseTime > 0.2f) {
            randomSpawnAnEnemyEveryThreeSecond();
            elapseTime = 0;
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void drawArrow() {
        for (Arrow arrow : arrows) {
//            game.spriteBatch.draw(
//                    arrowSprite,
//                    arrow.getArrowPosition().x - arrow.getWidth() / 2,
//                    arrow.getArrowPosition().y,
//                    arrow.getWidth() / 2,
//                    arrow.getHeight() / 2,
//                    arrow.getWidth(),
//                    arrow.getHeight(),
//                    scaleArrowX,
//                    scaleArrowY,
//                    arrow.getArrowSpriteAngle()
//            );
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.shapeRenderer.setColor(0.4f, 0.4f, 0, 1);
            Circle circle = arrow.getArrowBound();
            game.shapeRenderer.circle(circle.x, circle.y, circle.radius);
            game.shapeRenderer.setColor(0.7f, 0.2f, 0.1f, 0.3f);
            game.shapeRenderer.rect(
                    arrow.getArrowPosition().x - arrow.getWidth() / 2,
                    arrow.getArrowPosition().y,
                    arrow.getWidth() / 2,
                    arrow.getHeight() / 2,
                    arrow.getWidth(),
                    arrow.getHeight(),
                    scaleArrowX,
                    scaleArrowY,
                    arrow.getArrowSpriteAngle()
            );

            game.shapeRenderer.end();
        }
    }

    private void updateArrow(float delta) {
        Iterator<Arrow> arrowIterator = arrows.iterator();
        while (arrowIterator.hasNext()) {
            Arrow arrow = arrowIterator.next();
            arrow.move(delta);
            if (arrow.getArrowPosition().x < 0
                    || arrow.getArrowPosition().x > ArrowStormGame.GAME_WIDTH
                    || arrow.getArrowPosition().y < 0) {
                arrowIterator.remove();
            }
        }
    }

    private void drawBackground() {
        game.shapeRenderer.setColor(1, 0, 0, 1);
        game.shapeRenderer.rect(
                Player.POSITION_X,
                Player.POSITION_Y,
                Player.PLAYER_WIDTH,
                Player.PLAYER_HEIGHT
        );
        game.shapeRenderer.setColor(1, 1, 0, 1);
        game.shapeRenderer.rect(
                Player.POSITION_X + Player.PLAYER_WIDTH / 2,
                Player.POSITION_Y,
                Player.PLAYER_WIDTH / 2,
                Player.PLAYER_HEIGHT
        );
    }

    private void fireArrow(float arrowAngle, float arrowDirectionInDegree) {
        Arrow arrow = new Arrow(
                Player.SHOOTING_POINT_X,
                Player.SHOOTING_POINT_Y,
                arrowDirectionInDegree,
                arrowAngle
        );
        arrows.add(arrow);
        lastArrow = TimeUtils.nanoTime();
    }

    private void handleTouchEvent() {
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
                fireArrow(arrowAngle, arrowDirectionAngle);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
                fireArrow(arrowAngle, arrowDirectionAngle);
            }
        } else {
            if (TimeUtils.nanoTime() - lastTouched > shootDelay && TimeUtils.nanoTime() - lastArrow > shootDelay) {
                lastArrow = PREPARE_SHOOT;
                lastTouched = TimeUtils.nanoTime();
            }
        }
    }

    // Spawn an enemy.
    private void spawnEnemy(float originX, float originY, EnemyType enemyType) {
        spawnByType(originX, originY, enemyType);
    }

    // Spawn enemies.
    private void spawnEnemy(float originX, float originY, EnemyType enemyType, int number,
                            float deltaX, float deltaY, long spawnDelay) {
        spawnByType(originX, originY, enemyType);
    }

    private void spawnByType(float originX, float originY, EnemyType enemyType) {
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

    private void drawEnemy() {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Boar) {
                game.spriteBatch.draw(enemySprites.get("Boar"), enemy.getPosition().x, enemy.getPosition().y);
                game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                game.shapeRenderer.setColor(1, 0.4f, 0.4f, 1);
                Circle circle = enemy.getEnemyBound();
                game.shapeRenderer.circle(enemy.getPosition().x, enemy.getPosition().y, 10);
                game.shapeRenderer.setColor(1, 0.4f, 1, 0.3f);
                game.shapeRenderer.circle(circle.x, circle.y, circle.radius);
                game.shapeRenderer.end();
            } else if (enemy instanceof Tiger) {
                game.spriteBatch.draw(enemySprites.get("Tiger"), enemy.getPosition().x, enemy.getPosition().y);
            }
        }
    }

    private void updateEnemy(float delta) {
        Iterator<Enemy> enemyIterator = enemies.iterator();
        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            enemy.move(delta);
            if (enemy.getPosition().x - enemy.getEnemyBound().radius < 0
                    || enemy.getPosition().x > ArrowStormGame.GAME_WIDTH
                    || enemy.getPosition().y > ArrowStormGame.GAME_HEIGHT - Player.PLAYER_HEIGHT) {
                enemyIterator.remove();
            }
        }
    }

    private void randomSpawnAnEnemyEveryThreeSecond() {
        Random random = new Random();

        // 64 now is temp value for enemy width
        float originX = random.nextFloat() * (ArrowStormGame.GAME_WIDTH - 64);
        spawnEnemy(originX, 0, EnemyType.BOAR);
    }

    private void updateCollision(float delta) {
        int enemiesSize = enemies.size;
        int arrowSize = arrows.size;
        ArrayList<Integer> preparedRemovedArrowIndexes = new ArrayList<Integer>();
        ArrayList<Integer> preparedRemovedEnemyIndexes = new ArrayList<Integer>();
        for (int i = 0; i < enemiesSize; i++) {
            Enemy enemy = enemies.get(i);
            for (int j = 0; j < arrowSize; j++) {
                Arrow arrow = arrows.get(j);
                if (arrow.getArrowBound().overlaps(enemy.getEnemyBound())) {
                    preparedRemovedEnemyIndexes.add(i);
                    preparedRemovedArrowIndexes.add(j);
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

    private ArrayList<Integer> removeDuplicateIndex(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<Integer>();
        for (Integer item : list) {
            if (!set.contains(item)){
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    private boolean isCollision(Enemy enemy) {
        Iterator<Arrow> arrowIterator = arrows.iterator();
        while (arrowIterator.hasNext()) {
            Arrow arrow = arrowIterator.next();
            return Intersector.overlaps(enemy.getEnemyBound(), arrow.getArrowBound());
        }
        return false;
    }
}