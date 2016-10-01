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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.async.ThreadUtils;

import java.util.HashMap;
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
    private float arrowVelocity = 200;
    private long shootDelay = 500000000;
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

        game.spriteBatch.begin();
        drawArrow();
        drawEnemy();
        game.spriteBatch.end();

        handleTouchEvent();
        updateEnemy(delta);
        updateArrow(delta);

        elapseTime += delta;
        if (elapseTime > 3.0) {
            Gdx.app.log(TAG, "Enemy should create at elapseTime: " + elapseTime);
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
            game.spriteBatch.draw(
                    arrowSprite,
                    arrow.getPosition().x - arrow.getWidth() / 2,
                    arrow.getPosition().y,
                    arrow.getWidth() / 2,
                    arrow.getHeight() / 2,
                    arrow.getWidth(),
                    arrow.getHeight(),
                    scaleArrowX,
                    scaleArrowY,
                    arrow.getAngle()
            );
        }

    }

    private void updateArrow(float delta) {
        Iterator<Arrow> arrowIterator = arrows.iterator();
        while (arrowIterator.hasNext()) {
            Arrow arrow = arrowIterator.next();
            arrow.setPosition(arrow.getPosition().sub(arrow.getVelocity()));
            if (arrow.getPosition().x < 0
                    || arrow.getPosition().x > ArrowStormGame.GAME_WIDTH
                    || arrow.getPosition().y < 0) {
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

    private void fireArrow(float arrowAngle, float velocityX, float velocityY) {
        Arrow arrow = new Arrow(
                Player.SHOOTING_POINT_X,
                Player.SHOOTING_POINT_Y,
                velocityX,
                velocityY,
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

            float arrowDirectionDegree = arrowAngle + 90;
            Gdx.app.log(TAG, "arrow Dir: " + arrowDirectionDegree);


            Gdx.app.log(TAG, "angle: " + arrowAngle);
            float velocityX =
                    (float) (arrowVelocity * Math.cos(Math.toRadians(arrowDirectionDegree)))
                            * Gdx.graphics.getDeltaTime();
            float velocityY =
                    (float) (arrowVelocity * Math.sin(Math.toRadians(arrowDirectionDegree)))
                            * Gdx.graphics.getDeltaTime();

            Gdx.app.log(TAG, "veloX: " + velocityX + " , veloY: " + velocityY);
            if (lastArrow == PREPARE_SHOOT) {
                fireArrow(arrowAngle, velocityX, velocityY);
            } else if (TimeUtils.nanoTime() - lastArrow > shootDelay) {
                fireArrow(arrowAngle, velocityX, velocityY);
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
            if (enemy.getPosition().x - enemy.getWidth() < 0
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
}